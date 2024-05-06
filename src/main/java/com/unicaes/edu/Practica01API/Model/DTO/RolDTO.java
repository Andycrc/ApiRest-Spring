package com.unicaes.edu.Practica01API.Model.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class RolDTO implements Serializable {
    private Integer id;
    private String nombre;
}
