package com.colegiodelnorte.models;

import jakarta.persistence.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "colegios")
@Schema(description = "Entidad Colegio")
public class Colegio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nombre del Colegio", example = "Colegio San Martín")
    @Column(nullable = false)
    private String nombre;

    @Schema(description = "Dirección del Colegio", example = "Av. Libertador 1234")
    private String direccion;

    @OneToMany(mappedBy = "colegio", cascade = CascadeType.ALL)
    private List<Docente> docentes;

    @OneToMany(mappedBy = "colegio", cascade = CascadeType.ALL)
    private List<Curso> cursos;
}