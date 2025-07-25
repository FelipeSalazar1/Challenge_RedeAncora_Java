# Do Q Se Trata (What It's About) - Challenge Rede Âncora

Este projeto demonstra **três desafios algorítmicos principais** implementados em Java com Spring Boot:

## 🚗 Challenge 1: Ordenação com QuickSort
**Objetivo:** Ordenar dados de montadoras de veículos usando o algoritmo QuickSort.

**Funcionalidade:**
- Busca dados de montadoras via API externa
- Aplica algoritmo QuickSort personalizado para ordenação por ID
- Demonstra implementação eficiente de ordenação com complexidade O(n log n)

**Arquivo:** `SortUtils.java` e `SortMontadoraService.java`

## 📦 Challenge 2: Estruturas de Dados Heap
**Objetivo:** Encontrar produtos com menor e maior ID usando estruturas de dados Heap.

**Funcionalidade:**
- Busca dados de produtos via API externa
- Implementa Min-Heap e Max-Heap para encontrar os K produtos com menor/maior ID
- Demonstra estruturas de dados avançadas para otimização de busca

**Arquivos:** `HeapProdutoService.java`, `MinHeapUtils.java`, `MaxHeapUtils.java`

## 🔍 Challenge 3: Sistema de Autocomplete com BST
**Objetivo:** Implementar sistema de autocomplete usando Árvore Binária de Busca (BST).

**Funcionalidade:**
- Constrói uma BST com nomes de peças automotivas
- Oferece sugestões de autocomplete baseadas em prefixo
- Demonstra algoritmos de busca eficientes em árvores

**Arquivos:** `AutocompleteUtils.java`, `Bst.java`

## 📋 Como Executar

1. **Executar a aplicação completa:**
   ```bash
   ./gradlew bootRun --args='--spring.profiles.active=test'
   ```

2. **Executar apenas os testes dos challenges:**
   ```bash
   ./gradlew test --tests="ChallengeTests"
   ```

3. **Acessar a API REST:**
   - Aplicação disponível em: http://localhost:8080
   - Endpoints: `/users`, `/users/active`, `/users/inactive`

## 🎯 O Que Este Projeto Demonstra

- **Algoritmos de Ordenação:** QuickSort implementado do zero
- **Estruturas de Dados:** Heaps (Min e Max) e Binary Search Tree
- **Integração com APIs:** Consumo de APIs externas com tratamento de erros
- **Desenvolvimento Full-Stack:** API REST completa com Spring Boot
- **Boas Práticas:** Tratamento de exceções, testes unitários, código limpo

## 🚀 Funcionalidades da API

Além dos challenges algorítmicos, o projeto inclui uma API completa para gerenciamento de usuários:

- **POST /users** - Criar usuário
- **GET /users** - Listar todos os usuários
- **GET /users/active** - Listar usuários ativos (paginado)
- **GET /users/inactive** - Listar usuários inativos (paginado)
- **PUT /users** - Atualizar usuário
- **DELETE /users/{id}** - Excluir usuário (soft delete)

---

*Este projeto demonstra competências em algoritmos, estruturas de dados e desenvolvimento de APIs REST com Java e Spring Boot.*