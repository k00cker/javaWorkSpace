package com.colegiodelnorte.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.colegiodelnorte.models.Colegio;

public interface ColegioRepository extends JpaRepository<Colegio, Long> {

    Colegio findByNombre(String nombre);
}