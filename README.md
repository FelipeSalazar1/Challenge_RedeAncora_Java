# Challenge Rede Âncora - Java

Este projeto é parte de um desafio técnico para a Rede Âncora. Ele consiste em uma aplicação Java utilizando Spring Boot para exposição de uma API de usuários e **três desafios algorítmicos principais**.

## 🎯 O Que Este Projeto Demonstra (Do Q Se Trata)

Este projeto demonstra competências em:

1. **🚗 Challenge 1:** Algoritmo QuickSort para ordenação de montadoras
2. **📦 Challenge 2:** Estruturas de dados Heap (Min/Max) para encontrar produtos com menor/maior ID
3. **🔍 Challenge 3:** Binary Search Tree (BST) para sistema de autocomplete
4. **🌐 API REST:** Sistema completo de gerenciamento de usuários

> **Veja o arquivo [WHAT_IT_IS_ABOUT.md](WHAT_IT_IS_ABOUT.md) para descrição detalhada de cada challenge.**

## ✅ Pré-requisitos

Antes de iniciar a aplicação, certifique-se de ter instalado:

- Java 17 ou superior
- Git (para clonar o repositório, se necessário)
- Nenhuma instalação prévia do Gradle é necessária, pois o projeto utiliza o Gradle Wrapper (`gradlew`)

## ▶️ Como rodar o projeto

1. **Clone o repositório (caso ainda não tenha feito):**

   ```bash
   git clone https://github.com/FelipeSalazar1/Challenge_RedeAncora_Java.git
   cd Challenge_RedeAncora_Java
   ```

2. **Dê permissão de execução ao wrapper (Linux/macOS):**

   ```bash
   chmod +x gradlew
   ```

3. **Execute a aplicação (com challenges demonstrados):**

   ```bash
   ./gradlew bootRun --args='--spring.profiles.active=test'
   ```

   Ou no Windows:

   ```cmd
   gradlew.bat bootRun --args="--spring.profiles.active=test"
   ```

4. **Acesse a aplicação:**

   A aplicação estará rodando em: [http://localhost:8080](http://localhost:8080)

## 🧪 Testes dos Challenges

Execute os testes específicos dos challenges:

```bash
./gradlew test --tests="ChallengeTests"
```

## 🛠️ Tecnologias utilizadas

- Java 17
- Spring Boot
- Gradle
- H2 Database (para testes)
- Oracle Database (produção)

## 📋 API Endpoints

- **POST /users** - Criar usuário
- **GET /users** - Listar todos os usuários
- **GET /users/active** - Listar usuários ativos (paginado)
- **GET /users/inactive** - Listar usuários inativos (paginado)
- **PUT /users** - Atualizar usuário
- **DELETE /users/{id}** - Excluir usuário (soft delete)
