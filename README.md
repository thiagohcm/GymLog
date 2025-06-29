# GymLog - Gerenciador de Exercícios

## Descrição
GymLog é um aplicativo Android desenvolvido em Kotlin que permite aos usuários gerenciar seus exercícios de academia de forma intuitiva. O aplicativo implementa uma arquitetura MVVM com Room para persistência de dados e segue as melhores práticas de desenvolvimento Android moderno.

## Funcionalidades Principais

1. **Lista de Exercícios**: Visualização organizada de todos os exercícios cadastrados, com suporte à busca por nome, categoria ou observações.

2. **Gerenciamento de Exercícios**: Interface intuitiva para adicionar novos exercícios e editar existentes, incluindo nome, categoria, séries, repetições e observações.

3. **Sistema de Favoritos**: Funcionalidade para marcar/desmarcar exercícios como favoritos, com exibição prioritária na listagem.

4. **Pesquisa Avançada**: Sistema de busca em tempo real que filtra exercícios por nome, categoria ou observações.

5. **Gestão de Dados**: Sistema completo de CRUD (Create, Read, Update, Delete) para exercícios com persistência local.

## Tecnologias Utilizadas

### Core
- **Kotlin**: Linguagem principal
- **Android SDK**: API 24+ (Android 7.0+)
- **Gradle**: Sistema de build

### Bibliotecas Android
- **AndroidX**: Componentes do Android Jetpack
- **Room**: Abstração do SQLite para persistência
- **ViewModel**: Gerenciamento do ciclo de vida
- **ViewBinding**: Vinculação de views
- **Navigation Component**: Navegação entre telas
- **Coroutines**: Programação assíncrona
- **Dagger Hilt**: Injeção de dependências
- **Material Design Components**: UI/UX

## Requisitos de Sistema

- Android Studio Meerkat | 2024.3.1 ou superior
- JDK 11
- Android SDK com API level 24+
- Dispositivo/Emulador com Android 7.0 ou superior

## Instalação e Execução

1. Clone o repositório:
```bash
git clone https://github.com/thiagohcm/GymLog.git