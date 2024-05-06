package com.unicaes.edu.Practica01API.Controller;

import com.unicaes.edu.Practica01API.Model.DTO.RolDTO;
import com.unicaes.edu.Practica01API.Model.Entity.Rol;
import com.unicaes.edu.Practica01API.Model.PayLoad.MensajeResponse;
import com.unicaes.edu.Practica01API.Service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RolController {

    @Autowired
    private IRolService rolService;

    @GetMapping("/roles")
    public ResponseEntity<?> showAll() {
        List<Rol> roles = rolService.listAll();
        if (roles.isEmpty()) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay roles registrados")
                            .object(null)
                            .build(),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("Consulta exitosa")
                        .object(roles)
                        .build(),
                HttpStatus.OK);
    }

    @PostMapping("/rol")
    public ResponseEntity<?> create(@RequestBody RolDTO rolDto) {
        Rol rolSave = null;
        try {
            rolSave = rolService.save(rolDto);
            RolDTO rolResponse = mapRolToDTO(rolSave);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Rol guardado correctamente")
                    .object(rolResponse)
                    .build(),
                    HttpStatus.CREATED);
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/rol/{id}")
    public ResponseEntity<?> update(@RequestBody RolDTO rolDto, @PathVariable Integer id) {
        Rol rolUpdate = null;
        try {
            if (rolService.existsById(id)) {
                rolDto.setId(id);
                rolUpdate = rolService.save(rolDto);
                RolDTO rolResponse = mapRolToDTO(rolUpdate);
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Rol actualizado correctamente")
                        .object(rolResponse)
                        .build(),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("El rol no se encuentra")
                                .object(null)
                                .build(),
                        HttpStatus.NOT_FOUND);
            }

        } catch (DataAccessException ex) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("/rol/{id}")
    public ResponseEntity<?> deleteRol(@PathVariable Integer id) {
        try {
            Rol rolDelete = rolService.findById(id);
            rolService.delete(rolDelete);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Rol eliminado correctamente")
                    .object(null)
                    .build(),
                    HttpStatus.NO_CONTENT);
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("/rol/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Rol rol = rolService.findById(id);
        if (rol == null) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("El rol solicitado no existe")
                            .object(null)
                            .build(),
                    HttpStatus.NOT_FOUND);
        }
        RolDTO rolResponse = mapRolToDTO(rol);
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("Consulta exitosa")
                        .object(rolResponse)
                        .build(),
                HttpStatus.OK);
    }

    private RolDTO mapRolToDTO(Rol rol) {
        return RolDTO.builder()
                .id(rol.getId())
                .nombre(rol.getNombre())
                .build();
    }
}
