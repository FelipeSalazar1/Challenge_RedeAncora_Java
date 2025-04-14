package com.redeAncoraUsers.fiap;

import com.redeAncoraUsers.fiap.Utils.SortUtils;
import com.redeAncoraUsers.fiap.services.MontadoraService;
import com.redeAncoraUsers.fiap.validators.MontadoraResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class UsersApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApiApplication.class, args);

		SortUtils sortUtils = new SortUtils();

		MontadoraService montadora = new MontadoraService();
		MontadoraResponse response = montadora.converterJson(montadora.getData());

		List<MontadoraResponse.Montadora> montadoras = response.data();

		System.out.println(sortUtils.sortById(montadoras));
	}

}
