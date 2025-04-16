package com.coderhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coderhouse.dao.DaoFactory;
import com.coderhouse.models.Alumno;
import com.coderhouse.models.Curso;

@SpringBootApplication
public class Clase08Application implements CommandLineRunner{
	
	@Autowired
	private DaoFactory dao;

	public static void main(String[] args) {
		SpringApplication.run(Clase08Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		try {
			
			Curso curso1 = new Curso("Java");
			Curso curso2 = new Curso("JavaScript");
			Curso curso3 = new Curso("React");
			Curso curso4 = new Curso("Angular");
			Curso curso5 = new Curso("SQL");
			
			Alumno alumno1 = new Alumno("Pedro", "Urbina", 183435559,"L183435559", 20);
			Alumno alumno2 = new Alumno("Francisco", "Turbina", 165564326,"L165564326", 30);
			Alumno alumno3 = new Alumno("Felipe", "Menendez", 15335679,"L15335679", 19);
			Alumno alumno4 = new Alumno("Kimnberly", "Gomez", 186543267,"L186543267", 35);
			Alumno alumno5 = new Alumno("Tomar", "Valdivieso", 125678990,"L125678990", 23);
			
			dao.persistirCurso(curso1);
			dao.persistirCurso(curso2);
			dao.persistirCurso(curso3);
			dao.persistirCurso(curso4);
			dao.persistirCurso(curso5);
			
			dao.persistirAlumno(alumno1);
			dao.persistirAlumno(alumno2);
			dao.persistirAlumno(alumno3);
			dao.persistirAlumno(alumno4);
			dao.persistirAlumno(alumno5);
			
		}catch(Exception err) {
			err.printStackTrace(System.err);
		}
		
	}

	
	
}
