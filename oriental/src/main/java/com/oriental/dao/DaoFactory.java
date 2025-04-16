package com.oriental.dao;

import org.springframework.stereotype.Service;

import com.oriental.models.Cliente;
import com.oriental.models.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class DaoFactory {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void persistirCliente(Cliente cliente) {
		em.persist(cliente);
	}
	@Transactional	
	public void persistirProducto(Producto producto) {
		em.persist(producto);
	}
	
	

}
