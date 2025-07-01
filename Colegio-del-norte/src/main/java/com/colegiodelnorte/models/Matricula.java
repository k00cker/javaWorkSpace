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
@Table(name = "matriculas")
@Schema(description = "Entidad Matricula")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Fecha de matrícula")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

	public Alumno getColegio() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setColegio(Colegio colegio) {
		// TODO Auto-generated method stub
		
	}

	public Object getFechaInscripcion() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFechaInscripcion(Object object) {
		// TODO Auto-generated method stub
		
	}

	public int getAnio() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setAnio(int anio) {
		// TODO Auto-generated method stub
		
	}
}