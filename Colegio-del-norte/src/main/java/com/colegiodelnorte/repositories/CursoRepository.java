package com.colegiodelnorte.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.colegiodelnorte.models.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
