package com.unicaes.edu.Practica01API.Service;

import com.unicaes.edu.Practica01API.Model.DTO.UsuarioDTO;
import com.unicaes.edu.Practica01API.Model.Entity.Usuario;

import java.util.List;

public interface IUsuarioService {

    List<Usuario> listAll();
    Usuario save(UsuarioDTO usuario);

    Usuario findById(Integer id);

    void delete(Usuario usuario);

    boolean existsById(Integer id);
}
