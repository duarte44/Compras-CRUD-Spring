package com.compras;

import com.compras.entities.Cliente;
import com.compras.entities.Compras;
import com.compras.repositories.ClienteRepository;
import com.compras.repositories.ComprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class ComprasApplication implements CommandLineRunner {


	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ComprasRepository comprasRepository;

	public static void main(String[] args) {
		SpringApplication.run(ComprasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		Cliente cli1 = new Cliente(null, "Allan", "11111");
		Cliente cli2 = new Cliente(null, "Marly", "2222222");


		Compras c1 = new Compras(null, "PC", 200.00, 3,  cli1);
		Compras c2 = new Compras(null, "Notebook", 100.00, 2,cli1);
		Compras c3 = new Compras(null, "Notebook", 100.00, 5, cli2);

		cli1.getCompras().addAll(Arrays.asList(c1, c2));
		cli2.getCompras().addAll(Arrays.asList(c3));

		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		comprasRepository.saveAll(Arrays.asList(c1, c2, c3));

	}
}
