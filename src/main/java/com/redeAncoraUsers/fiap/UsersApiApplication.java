package com.redeAncoraUsers.fiap;

import com.redeAncoraUsers.fiap.Utils.SortUtils;
import com.redeAncoraUsers.fiap.services.AutocompleteService;
import com.redeAncoraUsers.fiap.services.HeapProdutoService;
import com.redeAncoraUsers.fiap.services.MontadoraService;
import com.redeAncoraUsers.fiap.validators.MontadoraResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class UsersApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApiApplication.class, args);



//1
//		SortUtils sortUtils = new SortUtils();
//
//		MontadoraService montadora = new MontadoraService();
//		MontadoraResponse response = montadora.converterJson(montadora.getData());
//
//		List<MontadoraResponse.Montadora> montadoras = response.data();
//
//		System.out.println(sortUtils.sortById(montadoras));

//		2
//		HeapProdutoService heapProdutoService = new HeapProdutoService();
//		heapProdutoService.getKProdutosComMenorId();
//		heapProdutoService.getKProdutosComMaiorId();

//		3
		AutocompleteService autocompleteService = new AutocompleteService();
		String prefix = "Fil";
		autocompleteService.suggest(prefix);
	}

}
