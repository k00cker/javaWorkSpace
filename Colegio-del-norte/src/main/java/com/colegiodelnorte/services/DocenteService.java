package com.colegiodelnorte.services;

import com.colegiodelnorte.interfaces.CrudInterface;
import com.colegiodelnorte.models.Colegio;
import com.colegiodelnorte.models.Docente;
import com.colegiodelnorte.repositories.ColegioRepository;
import com.colegiodelnorte.repositories.DocenteRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteService implements CrudInterface<Docente, Long> {

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private ColegioRepository colegioRepository;

    @Override
    public List<Docente> findAll() {
        return docenteRepository.findAll();
    }

    @Override
    public Docente findById(Long id) {
        return docenteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Docente no encontrado"));
    }

    @Override
    @Transactional
    public Docente save(Docente docente) {
        return docenteRepository.save(docente);
    }

    @Override
    @Transactional
    public Docente update(Long id, Docente actualizado) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Docente no encontrado"));

        if (actualizado.getNombre() != null && !actualizado.getNombre().isEmpty()) {
            docente.setNombre(actualizado.getNombre());
        }

        if (actualizado.getApellido() != null && !actualizado.getApellido().isEmpty()) {
            docente.setApellido(actualizado.getApellido());
        }

        if (actualizado.getLegajo() != null && !actualizado.getLegajo().isEmpty()) {
            docente.setLegajo(actualizado.getLegajo());
        }

        if (actualizado.getColegio() != null) {
            Colegio colegio = colegioRepository.findById(actualizado.getColegio().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Colegio no encontrado para el docente"));
            docente.setColegio(colegio);
        }

        return docenteRepository.save(docente);
    }

    @Override
    public void deleteById(Long id) {
        if (!docenteRepository.existsById(id)) {
            throw new IllegalArgumentException("Docente no encontrado");
        }
        docenteRepository.deleteById(id);
    }
}