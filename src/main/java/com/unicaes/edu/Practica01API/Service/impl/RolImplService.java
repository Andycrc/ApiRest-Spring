package com.unicaes.edu.Practica01API.Service.impl;

import com.unicaes.edu.Practica01API.Model.DAO.RolDAO;
import com.unicaes.edu.Practica01API.Model.DTO.RolDTO;
import com.unicaes.edu.Practica01API.Model.Entity.Rol;
import com.unicaes.edu.Practica01API.Model.Entity.Usuario;
import com.unicaes.edu.Practica01API.Service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolImplService implements IRolService {
    @Autowired
    private RolDAO rolDoa;


    @Override
    public List<Rol> listAll() {
        return (List) rolDoa.findAll();
    }

    @Transactional
    @Override
    public Rol save(RolDTO rolDto) {
        Rol rol = Rol.builder()
                .id(rolDto.getId())
                .nombre(rolDto.getNombre())
                .build();
        return rolDoa.save(rol);
    }

    @Transactional(readOnly = true)
    @Override
    public Rol findById(Integer id) {
        return rolDoa.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Rol rol) {
        rolDoa.delete(rol);
    }

    @Override
    public boolean existsById(Integer id) {
        return rolDoa.existsById(id);
    }
}
