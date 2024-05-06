package com.unicaes.edu.Practica01API.Service.impl;

import com.unicaes.edu.Practica01API.Model.DAO.UsuarioDAO;
import com.unicaes.edu.Practica01API.Model.DTO.UsuarioDTO;
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

    @Override
    public List<Usuario> listAll() {
        return (List) usuarioDAO.findAll();
    }

    @Transactional
    @Override
    public Usuario save(UsuarioDTO usuarioDto) {
        Usuario usuario = Usuario.builder()
                .id(usuarioDto.getId())
                .nombre(usuarioDto.getNombre())
                .apellido(usuarioDto.getApellido())
                .edad(usuarioDto.getEdad())
                .email(usuarioDto.getEmail())
                .fecha_registro(usuarioDto.getFecha_registro())
                .build();
        return usuarioDAO.save(usuario);
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
