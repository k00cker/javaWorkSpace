package com.colegiodelnorte.services;

import com.colegiodelnorte.interfaces.CrudInterface;
import com.colegiodelnorte.models.Colegio;
import com.colegiodelnorte.models.Curso;
import com.colegiodelnorte.models.Docente;
import com.colegiodelnorte.repositories.ColegioRepository;
import com.colegiodelnorte.repositories.CursoRepository;
import com.colegiodelnorte.repositories.DocenteRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService implements CrudInterface<Curso, Long> {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private ColegioRepository colegioRepository;

    @Override
    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    @Override
    public Curso findById(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));
    }

    @Override
    @Transactional
    public Curso save(Curso curso) {
        if (curso.getDocente() != null) {
            Docente docente = docenteRepository.findById(curso.getDocente().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Docente no encontrado"));
            curso.setDocente(docente);
        }

        if (curso.getColegio() != null) {
            Colegio colegio = colegioRepository.findById(curso.getColegio().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Colegio no encontrado"));
            curso.setColegio(colegio);
        }

        return cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public Curso update(Long id, Curso actualizado) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        if (actualizado.getNombre() != null && !actualizado.getNombre().isEmpty()) {
            curso.setNombre(actualizado.getNombre());
        }

        if (actualizado.getNivel() != null) {
            curso.setNivel(actualizado.getNivel());
        }

        if (actualizado.getNivel() != null && !actualizado.getNivel().isEmpty()) {
            curso.setNivel(actualizado.getNivel());
        }

        if (actualizado.getDocente() != null) {
            Docente docente = docenteRepository.findById(actualizado.getDocente().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Docente no encontrado"));
            curso.setDocente(docente);
        }

        if (actualizado.getColegio() != null) {
            Colegio colegio = colegioRepository.findById(actualizado.getColegio().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Colegio no encontrado"));
            curso.setColegio(colegio);
        }

        return cursoRepository.save(curso);
    }

    @Override
    public void deleteById(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new IllegalArgumentException("Curso no encontrado");
        }
        cursoRepository.deleteById(id);
    }
}