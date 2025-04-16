package com.oriental.models;

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


@Entity
@Table(name = "Clientes")
public class Cliente {
	
	@Id // PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Nombre", nullable = false )
	private String nombre;
	
	@Column(name = "Apellido", nullable = false )
	private String apellido;
	
	@Column(name = "RUT", nullable = false, unique = true )
	private int rut;
	
	@Column(name = "Celular", nullable = false, unique = true )
	private int celular;
	
	@Column(name = "Email")
	private String email;
	
	
	@ManyToMany(mappedBy = "clientes", fetch = FetchType.EAGER)
	private List<Producto> cursos = new ArrayList<>();
	
	
	private LocalDateTime createdAt;
	
	

	public Cliente(String nombre, String apellido, int rut, int celular, String email) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.rut = rut;
		this.celular = celular;
		this.email = email;
	}

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getRut() {
		return rut;
	}

	public void setRut(int rut) {
		this.rut = rut;
	}

	public int getCelular() {
		return celular;
	}

	public void setCelular(int celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", rut=" + rut + ", celular="
				+ celular + ", email=" + email + "]";
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	
	
	
	
}
	
	
	
	