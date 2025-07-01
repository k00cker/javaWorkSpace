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
@Table(name = "docentes")
@Schema(description = "Entidad Docente")
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Nombre del Docente", example = "Marcela")
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(unique = true, nullable = false)
    private String legajo;

    @ManyToOne
    @JoinColumn(name = "colegio_id")
    private Colegio colegio;

    @OneToMany(mappedBy = "docente")
    private List<Curso> cursos;
}