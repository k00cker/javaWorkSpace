package com.oriental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.oriental.dao.DaoFactory;
import com.oriental.models.Cliente;
import com.oriental.models.Producto;


@SpringBootApplication
public class OrientalApplication implements CommandLineRunner {
	
	@Autowired
	private DaoFactory dao;

	public static void main(String[] args) {
		SpringApplication.run(OrientalApplication.class, args);
	}
		
	@Override
	public void run(String... args) throws Exception {
	
		try {
			
			Producto producto1 = new Producto("Celular");
			Producto producto2 = new Producto("Televisor");
			Producto producto3 = new Producto("Xbox");
			Producto producto4 = new Producto("Impresora");
			Producto producto5 = new Producto("Camara");
			
			
			Cliente cliente1 = new Cliente("Ramon","Valdes",123334568,956565656,"ramonval@gmail.com");
			Cliente cliente2 = new Cliente("Ruben","Aguirre",113335674,945454545,"ruaguirre2@gmail.com");
			Cliente cliente3 = new Cliente("Maria Antonieta","De Las Nieves",14343452,946237855,"mnieves23@gmail.com");
			Cliente cliente4 = new Cliente("Roberto","Gomez",83452347,967676788,"chavo8@gmail.com");
			
			
			
			dao.persistirProducto(producto1);
			dao.persistirProducto(producto2);
			dao.persistirProducto(producto3);
			dao.persistirProducto(producto4);
			dao.persistirProducto(producto5);
			
			
			dao.persistirCliente(cliente1);
			dao.persistirCliente(cliente2);
			dao.persistirCliente(cliente3);
			dao.persistirCliente(cliente4);
			
			
		}catch(Exception err) {
			err.printStackTrace(System.err);
		}
		
	}
	
	}
	

	
	

