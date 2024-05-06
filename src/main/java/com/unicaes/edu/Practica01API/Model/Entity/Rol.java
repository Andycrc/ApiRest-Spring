package com.unicaes.edu.Practica01API.Model.Entity;

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
@Table(name = "roles")
public class Rol implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;
}
