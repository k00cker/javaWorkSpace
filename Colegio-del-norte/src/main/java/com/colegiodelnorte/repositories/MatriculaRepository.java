package com.colegiodelnorte.repositories;

import com.colegiodelnorte.models.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
}