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
@Table(name = "cursos")
@Schema(description = "Entidad Curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nombre del curso", example = "Matemática 1°B")
    @Column(nullable = false)
    private String nombre;
    
    @Schema(description = "Nivel del Curso", example = "Basica o Media")
    private String nivel;

    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Docente docente;

    @ManyToOne
    @JoinColumn(name = "colegio_id")
    private Colegio colegio;

    @ManyToMany
    @JoinTable(
        name = "curso_alumno",
        joinColumns = @JoinColumn(name = "curso_id"),
        inverseJoinColumns = @JoinColumn(name = "alumno_id")
    )
    private List<Alumno> alumnos;

    @OneToMany(mappedBy = "curso")
    private List<Asistencia> asistencias;
}