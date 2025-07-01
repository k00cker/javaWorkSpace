package com.colegiodelnorte.repositories;

import com.colegiodelnorte.models.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocenteRepository extends JpaRepository<Docente, Long> {
    Docente findByLegajo(String legajo);
}