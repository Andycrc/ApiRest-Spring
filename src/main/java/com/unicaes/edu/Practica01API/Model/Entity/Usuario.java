package com.unicaes.edu.Practica01API.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "usuarios")

public class Usuario implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "apellido")
  private String apellido;

  @Column(name = "edad")
  private Integer edad;

  @Column(name = "email")
  private String email;

  @Column(name = "fecha_registro")
  private Date fecha_registro;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "rol_id")
  private Rol rol;
}
