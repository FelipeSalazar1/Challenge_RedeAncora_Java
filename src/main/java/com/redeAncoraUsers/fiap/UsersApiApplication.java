package com.redeAncoraUsers.fiap;

import com.redeAncoraUsers.fiap.services.HeapProdutoService;
import com.redeAncoraUsers.fiap.utils.AutocompleteUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsersApiApplication {

	public static void main(String[] args) {
//		SpringApplication.run(UsersApiApplication.class, args);
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
		HeapProdutoService heapProdutoService = new HeapProdutoService();
		heapProdutoService.getKProdutosComMenorId();
		heapProdutoService.getKProdutosComMaiorId();

//		3
		AutocompleteUtils autocompleteUtils = new AutocompleteUtils();
		String prefix = "Fil";
		System.out.println(autocompleteUtils.suggest(prefix));
	}

}
