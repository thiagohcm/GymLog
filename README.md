# GymLog - Gerenciador de Exercícios

## Descrição
GymLog é um aplicativo Android desenvolvido em Kotlin para gerenciar exercícios de academia. Permite cadastrar, editar, buscar, favoritar e excluir exercícios, com persistência local e interface moderna baseada em Material Design.

## Funcionalidades

1. **Listagem de Exercícios**: Visualize todos os exercícios cadastrados, ordenados por favoritos e nome.
2. **Cadastro e Edição**: Adicione novos exercícios ou edite existentes, informando nome, categoria, séries, repetições e observações.
3. **Favoritar Exercícios**: Marque/desmarque exercícios como favoritos, exibindo-os prioritariamente.
4. **Busca Avançada**: Pesquise exercícios por nome, categoria ou observações em tempo real.
5. **Exclusão Segura**: Remova exercícios com confirmação de exclusão.
6. **Gestão de Dados**: Sistema completo de CRUD (Create, Read, Update, Delete) para exercícios com persistência local.

## Tecnologias e Bibliotecas

- **Kotlin**
- **AndroidX** (Jetpack)
- **Room** (persistência local)
- **ViewModel** e **StateFlow**
- **ViewBinding**
- **Navigation Component** (Safe Args)
- **Coroutines**
- **Dagger Hilt** (injeção de dependências)
- **Material Design Components**
- **RecyclerView**

## Requisitos

- Android Studio Meerkat | 2024.3.1 ou superior
- JDK 11
- Android SDK API 24+ (Android 7.0+)
- Dispositivo ou emulador com Android 7.0+

## Instalação e Execução

1. Clone o repositório:
   ```bash
   git clone https://github.com/thiagohcm/GymLog.git