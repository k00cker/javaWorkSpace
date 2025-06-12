package com.coderhouse.models;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Alumnos")
public class Alumno {
	
	@Id // PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // autoincremental
	private Long id;
	
	@Column(name = "Nombre", nullable = false )
	private String nombre;
	
	@Column(name = "Apellido", nullable = false )
	private String apellido;
	
	@Column(name = "DNI", nullable = false, unique = true )
	private int dni;
	
	@Column(name = "Legajo", nullable = false, unique = true )
	private String legajo;
	
	@Column(name = "Edad")
	private int edad;
	
	@ManyToMany(mappedBy = "alumnos", fetch = FetchType.EAGER)
	private List<Curso> cursos = new ArrayList<>();
	
	
	private LocalDateTime createdAt;
	
}


