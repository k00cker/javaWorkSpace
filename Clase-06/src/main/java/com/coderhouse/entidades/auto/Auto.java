package com.coderhouse.entidades.auto;

import com.coderhouse.abstractas.Vehiculo;

public class Auto extends Vehiculo {

	@Override
	protected void encender() {
		System.out.println("Mi auto enciende!");

	}

	@Override
	protected void apagar() {
		System.out.println("Mi auto se apaga!");
	}

	@Override
	protected void mover() {
		System.out.println("Mi auto se mueve!");
	}
	
	public String toString() {
		return "Auto [marca=" + marca + ", modelo=" + modelo + ", Color=" + color + ", ruedas=" + ruedas + "]";
	}

}
