package com.unicaes.edu.Practica01API.Service;

import com.unicaes.edu.Practica01API.Model.DTO.RolDTO;
import com.unicaes.edu.Practica01API.Model.DTO.UsuarioDTO;
import com.unicaes.edu.Practica01API.Model.Entity.Rol;
import com.unicaes.edu.Practica01API.Model.Entity.Usuario;

import java.util.List;

public interface IRolService {
    List<Rol> listAll();
    Rol save(RolDTO rol);

    Rol findById(Integer id);

    void delete(Rol rol);

    boolean existsById(Integer id);
}
