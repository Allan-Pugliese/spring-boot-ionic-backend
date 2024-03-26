package com.allan.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.allan.cursomc.domain.Categoria;
import com.allan.cursomc.repositeories.CategoriaRepository;

@SpringBootApplication
public class Cursomc1Application implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Cursomc1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informatica");
		
		Categoria cat2 = new Categoria(null, "Escritorio");
		

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));

	}

}
