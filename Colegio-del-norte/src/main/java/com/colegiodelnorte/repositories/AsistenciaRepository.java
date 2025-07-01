package com.colegiodelnorte.repositories;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

import com.colegiodelnorte.models.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
	
	Optional<Asistencia> findByAlumnoIdAndCursoNombreAndFecha(Long alumnoId, String nombreCurso, LocalDate fecha);

    List<Asistencia> findByCursoNombreAndFecha(String nombreCurso, LocalDate fecha);
}