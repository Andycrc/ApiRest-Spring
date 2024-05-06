package com.unicaes.edu.Practica01API.Model.DTO;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class UsuarioDTO implements Serializable {

  private Integer id;
  private String nombre;
  private String apellido;
  private Integer edad;
  private String email;
  private Date fecha_registro;
  private RolDTO rol;


}
