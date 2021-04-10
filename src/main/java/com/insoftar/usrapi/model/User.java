package com.insoftar.usrapi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String nombres;
    private String apellidos;
    private Integer cedula;
    private String correo;
    private String genero;
    private Long telefono;

}
