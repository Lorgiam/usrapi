package com.insoftar.usrapi.dto;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
@NonNull
public class UserPostDto {
    private String nombres;
    private String apellidos;
    private Integer cedula;
    private String correo;
    private Integer telefono;
}
