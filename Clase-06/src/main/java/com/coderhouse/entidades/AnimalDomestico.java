package com.coderhouse.entidades;

import com.coderhouse.interfaces.AccionesInterface;
import com.coderhouse.interfaces.AnimalInterface;

public class AnimalDomestico implements AnimalInterface, AccionesInterface {

	private String nombre;
	
	private String raza;
	
	private int edad;
	
	private String tamanio;
	
	
	
	public AnimalDomestico(String nombre, String raza, int edad, String tamanio) {
		super();
		this.nombre = nombre;
		this.raza = raza;
		this.edad = edad;
		this.tamanio = tamanio;
	}

	public AnimalDomestico() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void comer() {
		

	}

	@Override
	public void moverse() {

	}

	@Override
	public void emitirSonido() {

	}

	@Override
	public boolean estaVivo() {
		return false;
	}

	@Override
	public void dormir() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getTamanio() {
		return tamanio;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}

	@Override
	public String toString() {
		return "AnimalDomestico [nombre=" + nombre + ", raza=" + raza + ", edad=" + edad + ", tamanio=" + tamanio + "]";
	}

	@Override
	public void respirar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jugar() {
		// TODO Auto-generated method stub
		
	}

}
