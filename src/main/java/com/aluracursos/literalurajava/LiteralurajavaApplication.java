package com.aluracursos.literalurajava;

import com.aluracursos.literalurajava.principal.Principal;
import com.aluracursos.literalurajava.repositories.IAutorRepository;
import com.aluracursos.literalurajava.repositories.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteralurajavaApplication implements CommandLineRunner {

	@Autowired
	private ILibroRepository libroRepository;
	@Autowired
	private IAutorRepository autorRepository;
	public static void main(String[] args) {
		SpringApplication.run(LiteralurajavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepository, autorRepository);
		principal.muestraElMenu();
	}

}
