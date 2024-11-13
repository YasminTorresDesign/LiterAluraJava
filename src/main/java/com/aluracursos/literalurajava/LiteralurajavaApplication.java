package com.aluracursos.literalurajava;

import com.aluracursos.literalurajava.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteralurajavaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteralurajavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.muestraElMenu();
	}

}
