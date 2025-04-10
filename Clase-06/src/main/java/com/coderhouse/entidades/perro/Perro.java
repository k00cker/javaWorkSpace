package com.coderhouse.entidades.perro;

import com.coderhouse.entidades.AnimalDomestico;
import com.coderhouse.interfaces.PerroInterface;

public class Perro extends AnimalDomestico implements PerroInterface {

	
	@Override
	public void comer() {
		System.out.println("Mi Perro " + getNombre() + " esta comiedo.! ");
	}

	@Override
	public void moverse() {
		System.out.println("Mi Perro " + getNombre() + " se esta moviendo.! ");

	}

	@Override
	public void emitirSonido() {
		System.out.println("Mi Perro " + getNombre() + " esta ladrando Guau Guau.! ");
	}

	@Override
	public boolean estaVivo() {
		return true;
	}

	@Override
	public void dormir() {
		System.out.println("Mi Perro " + getNombre() + " esta durmiendo.! ");
	}

	@Override
	public void olfatear() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String toString() {
		return "Perro [nombre=" + getNombre() + ", raza=" + getRaza() + ", edad=" + getEdad() + ", tamanio=" + getTamanio() + "]";
	}
	
}
