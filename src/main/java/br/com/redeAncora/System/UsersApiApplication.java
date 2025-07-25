package br.com.redeAncora.System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.redeAncora.System.utilities.SortUtils;
import br.com.redeAncora.System.utilities.AutocompleteUtils;
import br.com.redeAncora.System.services.SortMontadoraService;
import br.com.redeAncora.System.services.HeapProdutoService;
import br.com.redeAncora.System.validators.MontadoraResponse;
import java.util.List;

@SpringBootApplication
public class UsersApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApiApplication.class, args);
		
		System.out.println("=== CHALLENGE REDE ÂNCORA - DEMONSTRAÇÃO DAS FUNCIONALIDADES ===\n");

		// Challenge 1: Ordenação de Montadoras usando QuickSort
		System.out.println("🚗 CHALLENGE 1: Ordenação de Montadoras (QuickSort)");
		try {
			SortUtils sortUtils = new SortUtils();
			SortMontadoraService montadoraService = new SortMontadoraService();
			MontadoraResponse response = montadoraService.converterJson(montadoraService.getData());
			
			if (response != null && response.data() != null) {
				List<MontadoraResponse.Montadora> montadoras = response.data();
				System.out.println("Montadoras ordenadas por ID: " + sortUtils.sortById(montadoras));
			} else {
				System.out.println("Não foi possível obter dados das montadoras (API offline)");
			}
		} catch (Exception e) {
			System.out.println("Erro no Challenge 1: " + e.getMessage());
		}
		System.out.println();

		// Challenge 2: Operações com Heap para encontrar produtos com menor/maior ID
		System.out.println("📦 CHALLENGE 2: Heap - Produtos com Menor/Maior ID");
		try {
			HeapProdutoService heapProdutoService = new HeapProdutoService();
			heapProdutoService.getKProdutosComMenorId();
			heapProdutoService.getKProdutosComMaiorId();
		} catch (Exception e) {
			System.out.println("Erro no Challenge 2: " + e.getMessage());
		}
		System.out.println();

		// Challenge 3: Sistema de Autocomplete usando BST
		System.out.println("🔍 CHALLENGE 3: Autocomplete com BST");
		try {
			AutocompleteUtils autocompleteUtils = new AutocompleteUtils();
			String prefix = "a";
			System.out.println("Sugestões para '" + prefix + "': " + autocompleteUtils.suggest(prefix));
		} catch (Exception e) {
			System.out.println("Erro no Challenge 3: " + e.getMessage());
		}
		System.out.println();
		
		System.out.println("=== APLICAÇÃO INICIADA - API DISPONÍVEL EM http://localhost:8080 ===");
	}

}
