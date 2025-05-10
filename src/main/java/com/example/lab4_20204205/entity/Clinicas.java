package com.example.lab4_20204205.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "clinic")
@Getter
@Setter
public class Clinicas {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(nullable = false)
    @Size(min=5,max = 100,message = "Solo se soportan de 5 a 100 caractéres")
    @NotBlank
    private String name;


    @Column(nullable = false)
    @Size(min=10,message = "Solo se soportan  min 10")
    @NotBlank
    private String address;

    @Column(nullable = false)
    @Size(min=9,max = 9,message = "Solo se soportan de 9")
    @NotBlank
    private String phone_number;


}
