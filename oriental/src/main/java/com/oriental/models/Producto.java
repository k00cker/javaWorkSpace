package com.oriental.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "Productos")
public class Producto {
	
	
	@Id // PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Nombre", nullable = false, unique = true)
	private String nombre;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "producto_cliente",
			joinColumns = @JoinColumn(name = "producto_id"),
			inverseJoinColumns = @JoinColumn(name = "cliente_id")
			)
	private List<Cliente> clientes = new ArrayList<>();

	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Producto(String nombre) {
		super();
		this.nombre = nombre;
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
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
	
}
	
	
	