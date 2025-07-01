package com.colegiodelnorte.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.colegiodelnorte.models.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {}
