package com.colegiodelnorte.models;

import jakarta.persistence.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "asistencias")
@Schema(description = "Entidad Asistencia")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Fecha de la asistencia", example = "2025-06-26")
    @Column(nullable = false)
    private LocalDate fecha;

    @Schema(description = "¿El alumno asistió?", example = "true")
    @Column(nullable = false)
    private boolean presente;

    @ManyToOne
    @JoinColumn(name = "alumno_id", nullable = false)
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;
}