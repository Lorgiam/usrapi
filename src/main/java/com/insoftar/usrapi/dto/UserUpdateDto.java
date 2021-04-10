package com.insoftar.usrapi.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NonNull
public class UserUpdateDto {
    private String id;
    private String nombres;
    private String apellidos;
    private Integer cedula;
    private String correo;
    private Long telefono;
    private String genero;
}
