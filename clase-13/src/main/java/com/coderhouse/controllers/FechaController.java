package com.coderhouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.dto.TimeResponseDTO;
import com.coderhouse.services.FechaService;

@RestController
@RequestMapping("/api/fecha")
public class FechaController {

	@Autowired
	private FechaService fs;

	private int contadorDeInvocaciones = 0;

	private String ultimaFechaMostrada = "N/A";

	@GetMapping
	public ResponseEntity<String> obtenerFechaActual() {
		contadorDeInvocaciones++;

		TimeResponseDTO fechaActual = fs.obtenerFechaYHoraActuales();

		String mensaje = String.format(
				"Fecha Actual: %s %s %d, %d\nHora: %s\nNúmero de Invocaciones: %d\nÚltima fecha mostrada: %s",
				fechaActual.getDayOfWeek(), 
				fechaActual.getMonth(), 
				fechaActual.getDay(), 
				fechaActual.getYear(),
				fechaActual.getTime(), 
				contadorDeInvocaciones, 
				ultimaFechaMostrada);

		ultimaFechaMostrada = String.format("%s %s %d, %d %s", 
				fechaActual.getDayOfWeek(), 
				fechaActual.getMonth(),
				fechaActual.getDay(), 
				fechaActual.getYear(), 
				fechaActual.getTime());

		return ResponseEntity.ok(mensaje);

	}

}