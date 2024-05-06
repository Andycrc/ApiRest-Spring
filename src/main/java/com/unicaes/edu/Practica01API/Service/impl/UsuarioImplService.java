package com.unicaes.edu.Practica01API.Service.impl;

import com.unicaes.edu.Practica01API.Model.DAO.RolDAO;
import com.unicaes.edu.Practica01API.Model.DAO.UsuarioDAO;
import com.unicaes.edu.Practica01API.Model.DTO.UsuarioDTO;
import com.unicaes.edu.Practica01API.Model.Entity.Rol;
import com.unicaes.edu.Practica01API.Model.Entity.Usuario;
import com.unicaes.edu.Practica01API.Service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //clase como servicio
public class UsuarioImplService implements IUsuarioService {

    @Autowired //Inyeccion de dependecia
    private UsuarioDAO usuarioDAO;

    @Autowired
    private RolDAO rolDAO;

    @Override
    public List<Usuario> listAll() {
        return (List) usuarioDAO.findAll();
    }

    @Transactional
    @Override
    public Usuario save(UsuarioDTO usuarioDto) {
        Usuario usuarioExistente = usuarioDAO.findById(usuarioDto.getId()).orElse(null);

        if (usuarioExistente != null) {
            // Actualizar los campos del usuario existente con los valores del DTO
            usuarioExistente.setNombre(usuarioDto.getNombre());
            usuarioExistente.setApellido(usuarioDto.getApellido());
            usuarioExistente.setEdad(usuarioDto.getEdad());
            usuarioExistente.setEmail(usuarioDto.getEmail());
            usuarioExistente.setFecha_registro(usuarioDto.getFecha_registro());

            // Obtener el rol por su ID del DTO del usuario
            Rol rol = rolDAO.findById(usuarioDto.getRol().getId()).orElse(null);
            usuarioExistente.setRol(rol);

            return usuarioDAO.save(usuarioExistente);
        } else {
            // Si el usuario no existe, se crea uno nuevo
            Rol rol = rolDAO.findById(usuarioDto.getRol().getId()).orElse(null);

            Usuario usuarioNuevo = Usuario.builder()
                    .nombre(usuarioDto.getNombre())
                    .apellido(usuarioDto.getApellido())
                    .edad(usuarioDto.getEdad())
                    .email(usuarioDto.getEmail())
                    .fecha_registro(usuarioDto.getFecha_registro())
                    .rol(rol)
                    .build();

            return usuarioDAO.save(usuarioNuevo);
        }
    }



    @Transactional(readOnly = true)
    @Override
    public Usuario findById(Integer id) {

        return usuarioDAO.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Usuario usuario) {
        usuarioDAO.delete(usuario);
    }

    @Override
    public boolean existsById(Integer id) {
        return usuarioDAO.existsById(id);
    }
}
