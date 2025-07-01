package com.colegiodelnorte.services;

import com.colegiodelnorte.interfaces.CrudInterface;
import com.colegiodelnorte.models.Colegio;
import com.colegiodelnorte.repositories.ColegioRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColegioService implements CrudInterface<Colegio, Long> {

    @Autowired
    private ColegioRepository colegioRepository;

    @Override
    public List<Colegio> findAll() {
        return colegioRepository.findAll();
    }

    @Override
    public Colegio findById(Long id) {
        return colegioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Colegio no encontrado"));
    }

    @Override
    @Transactional
    public Colegio save(Colegio colegio) {
        return colegioRepository.save(colegio);
    }

    @Override
    @Transactional
    public Colegio update(Long id, Colegio colegioActualizado) {
        Colegio colegio = colegioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Colegio no encontrado"));

        if (colegioActualizado.getNombre() != null && !colegioActualizado.getNombre().isEmpty()) {
            colegio.setNombre(colegioActualizado.getNombre());
        }

        if (colegioActualizado.getDireccion() != null && !colegioActualizado.getDireccion().isEmpty()) {
            colegio.setDireccion(colegioActualizado.getDireccion());
        }

        return colegioRepository.save(colegio);
    }

    @Override
    public void deleteById(Long id) {
        if (!colegioRepository.existsById(id)) {
            throw new IllegalArgumentException("Colegio no encontrado");
        }
        colegioRepository.deleteById(id);
    }
}