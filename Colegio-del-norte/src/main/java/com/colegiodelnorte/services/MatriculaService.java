package com.colegiodelnorte.services;

import com.colegiodelnorte.interfaces.CrudInterface;
import com.colegiodelnorte.models.Alumno;
import com.colegiodelnorte.models.Colegio;
import com.colegiodelnorte.models.Curso;
import com.colegiodelnorte.models.Matricula;
import com.colegiodelnorte.repositories.AlumnoRepository;
import com.colegiodelnorte.repositories.ColegioRepository;
import com.colegiodelnorte.repositories.CursoRepository;
import com.colegiodelnorte.repositories.MatriculaRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MatriculaService implements CrudInterface<Matricula, Long> {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ColegioRepository colegioRepository;

    @Override
    public List<Matricula> findAll() {
        return matriculaRepository.findAll();
    }

    @Override
    public Matricula findById(Long id) {
        return matriculaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula no encontrada"));
    }

    @Override
    @Transactional
    public Matricula save(Matricula matricula) {
        Alumno alumno = alumnoRepository.findById(matricula.getAlumno().getId())
                .orElseThrow(() -> new IllegalArgumentException("Alumno no encontrado"));

        Curso curso = cursoRepository.findById(matricula.getCurso().getId())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        Colegio colegio = colegioRepository.findById(matricula.getColegio().getId())
                .orElseThrow(() -> new IllegalArgumentException("Colegio no encontrado"));

        matricula.setAlumno(alumno);
        matricula.setCurso(curso);
        matricula.setColegio(colegio);

        if (matricula.getFechaInscripcion() == null) {
            matricula.setFechaInscripcion(LocalDate.now());
        }

        return matriculaRepository.save(matricula);
    }

    @Override
    @Transactional
    public Matricula update(Long id, Matricula datos) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula no encontrada"));

        if (datos.getAnio() > 0) {
            matricula.setAnio(datos.getAnio());
        }

        if (datos.getFechaInscripcion() != null) {
            matricula.setFechaInscripcion(datos.getFechaInscripcion());
        }

        if (datos.getAlumno() != null) {
            Alumno alumno = alumnoRepository.findById(datos.getAlumno().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Alumno no encontrado"));
            matricula.setAlumno(alumno);
        }

        if (datos.getCurso() != null) {
            Curso curso = cursoRepository.findById(datos.getCurso().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));
            matricula.setCurso(curso);
        }

        if (datos.getColegio() != null) {
            Colegio colegio = colegioRepository.findById(datos.getColegio().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Colegio no encontrado"));
            matricula.setColegio(colegio);
        }

        return matriculaRepository.save(matricula);
    }

    @Override
    public void deleteById(Long id) {
        if (!matriculaRepository.existsById(id)) {
            throw new IllegalArgumentException("Matrícula no encontrada");
        }
        matriculaRepository.deleteById(id);
    }
}