package com.colegiodelnorte.services;

import com.colegiodelnorte.interfaces.CrudInterface;
import com.colegiodelnorte.models.Alumno;
import com.colegiodelnorte.models.Asistencia;
import com.colegiodelnorte.models.Curso;
import com.colegiodelnorte.repositories.AlumnoRepository;
import com.colegiodelnorte.repositories.AsistenciaRepository;
import com.colegiodelnorte.repositories.CursoRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaService implements CrudInterface<Asistencia, Long> {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public List<Asistencia> findAll() {
        return asistenciaRepository.findAll();
    }

    @Override
    public Asistencia findById(Long id) {
        return asistenciaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Asistencia no encontrada"));
    }

    @Override
    @Transactional
    public Asistencia save(Asistencia asistencia) {
        Alumno alumno = alumnoRepository.findById(asistencia.getAlumno().getId())
                .orElseThrow(() -> new IllegalArgumentException("Alumno no encontrado"));

        Curso curso = cursoRepository.findById(asistencia.getCurso().getId())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        asistencia.setAlumno(alumno);
        asistencia.setCurso(curso);

        return asistenciaRepository.save(asistencia);
    }

    @Override
    @Transactional
    public Asistencia update(Long id, Asistencia nueva) {
        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Asistencia no encontrada"));

        asistencia.setFecha(nueva.getFecha());
        asistencia.setPresente(nueva.isPresente());

        if (nueva.getAlumno() != null) {
            Alumno alumno = alumnoRepository.findById(nueva.getAlumno().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Alumno no encontrado"));
            asistencia.setAlumno(alumno);
        }

        if (nueva.getCurso() != null) {
            Curso curso = cursoRepository.findById(nueva.getCurso().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));
            asistencia.setCurso(curso);
        }

        return asistenciaRepository.save(asistencia);
    }

    @Override
    public void deleteById(Long id) {
        if (!asistenciaRepository.existsById(id)) {
            throw new IllegalArgumentException("Asistencia no encontrada");
        }
        asistenciaRepository.deleteById(id);
    }
    
    public Optional<Asistencia> verificarAsistenciaPorCursoYFecha(Long alumnoId, String cursoNombre, LocalDate fecha) {
        return asistenciaRepository.findByAlumnoIdAndCursoNombreAndFecha(alumnoId, cursoNombre, fecha);
    }

}