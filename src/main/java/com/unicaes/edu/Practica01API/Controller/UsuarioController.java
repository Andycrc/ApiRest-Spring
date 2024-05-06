package com.unicaes.edu.Practica01API.Controller;

import com.unicaes.edu.Practica01API.Model.DTO.RolDTO;
import com.unicaes.edu.Practica01API.Model.DTO.UsuarioDTO;
import com.unicaes.edu.Practica01API.Model.Entity.Usuario;
import com.unicaes.edu.Practica01API.Model.PayLoad.MensajeResponse;
import com.unicaes.edu.Practica01API.Service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("usuarios")
    public ResponseEntity<?> showAll(){

        List<Usuario> getlist =  usuarioService.listAll();

        if(getlist == null){
            return  new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay registros")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("Consulta exitosa")
                        .object(getlist)
                        .build()
                , HttpStatus.OK);
    }

    @PostMapping("usuario")
    public ResponseEntity<?> create(@RequestBody UsuarioDTO usuarioDto){
        Usuario usuarioSave = null;
        try {
            usuarioSave = usuarioService.save(usuarioDto);
            UsuarioDTO usuarioResponse = mapUsuarioToDTO(usuarioSave);
            return  new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(usuarioResponse)
                    .build()
                    ,HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            return  new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("usuario/{id}")
    public ResponseEntity<?> update(@RequestBody UsuarioDTO usuarioDto, @PathVariable Integer id){
        Usuario usuarioUpdate = null;
        try {
            if (usuarioService.existsById(id)){
                usuarioDto.setId(id);
                usuarioUpdate = usuarioService.save(usuarioDto);
                UsuarioDTO usuarioResponse = mapUsuarioToDTO(usuarioUpdate);
                return  new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Actualizado correctamente")
                        .object(usuarioResponse)
                        .build()
                        ,HttpStatus.CREATED);
            }else{
                return  new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("El registro no se encuentra")
                                .object(null)
                                .build()
                        , HttpStatus.METHOD_NOT_ALLOWED);
            }

        } catch (DataAccessException exDt) {
            return  new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Integer id) {
        try {
            Usuario usuarioDelete = usuarioService.findById(id);
            usuarioService.delete(usuarioDelete);
            return new ResponseEntity<>(usuarioDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
           return  new ResponseEntity<>(
                   MensajeResponse.builder()
                       .mensaje(exDt.getMessage())
                       .object(null)
                       .build()
                   , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("usuario/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id){

        Usuario usuario = usuarioService.findById(id);

        if(usuario == null){
            return  new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("El usuario que intenta buscar, no existe")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }

        UsuarioDTO usuarioResponse = mapUsuarioToDTO(usuario);

        return  new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("Consulta exitosa")
                        .object(usuarioResponse)
                        .build()
                , HttpStatus.OK);
    }

    private UsuarioDTO mapUsuarioToDTO(Usuario usuario) {
        RolDTO rolDTO = null;
        if (usuario.getRol() != null) {
            rolDTO = RolDTO.builder()
                    .id(usuario.getRol().getId())
                    .nombre(usuario.getRol().getNombre())
                    .build();
        }
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .edad(usuario.getEdad())
                .email(usuario.getEmail())
                .fecha_registro(usuario.getFecha_registro())
                .rol(rolDTO)
                .build();
    }
}
