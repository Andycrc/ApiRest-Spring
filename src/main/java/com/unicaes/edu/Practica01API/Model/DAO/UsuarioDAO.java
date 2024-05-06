package com.unicaes.edu.Practica01API.Model.DAO;

import com.unicaes.edu.Practica01API.Model.Entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDAO extends CrudRepository<Usuario,Integer> {



}
