# 🚗 Vehicle Rental System

Sistema de aluguel de veículos desenvolvido em Java, com arquitetura em camadas e armazenamento em memória.

## 📋 Funcionalidades

### Clientes
- Cadastrar, remover, buscar e listar clientes

### Veículos
- Cadastrar, remover, buscar e listar veículos
- Categorias: Carro, Moto, Caminhão
- Disponibilidade: Disponível, Alugado, Manutenção

### Aluguéis
- Registrar, remover, buscar e listar contratos de aluguel
- Cálculo automático do valor total
- Registro de devolução com cálculo de multa por atraso (10% ao dia)

## 🏗️ Arquitetura

```
src/
├── controller/       # Camada de interface com o usuário
├── service/          # Regras de negócio
├── repository/       # Persistência em memória
├── domain/
│   ├── model/        # Entidades do sistema
│   ├── enums/        # Categorias e disponibilidade
│   └── exception/    # Exceções de domínio e repositório
└── utils/            # Utilitários de entrada
```

## ✅ Conceitos Aplicados

- Separação de responsabilidades (Controller → Service → Repository)
- Interfaces para repositórios
- Exceções checked customizadas (`DomainException`, `RepositoryException`)
- Validações no construtor das entidades
- Enums para tipos e disponibilidade
- Generics com `Set`, `TreeSet` e `LinkedHashSet`
- `Comparable` para ordenação das entidades
- `equals` e `hashCode` nas entidades

## 🔧 Validações

| Entidade | Campo | Validação |
|---------|-------|-----------|
| Cliente | ID | Maior que zero |
| Cliente | Nome | Não nulo e não vazio |
| Cliente | CPF | Exatamente 11 caracteres |
| Cliente | Telefone | Exatamente 11 caracteres |
| Veículo | ID | Maior que zero |
| Veículo | Modelo/Marca | Não nulo e não vazio |
| Veículo | Placa | Formato antigo (AAA-0000) ou Mercosul (AAA0A00) |
| Veículo | Preço/Dia | Não nulo e maior que zero |
| Aluguel | ID | Maior que zero |
| Aluguel | Data devolução | Deve ser posterior à data de início |

## ▶️ Como Executar

1. Clone o repositório
2. Abra em sua IDE favorita (IntelliJ, Eclipse)
3. Execute a classe `Main.java`
4. Navegue pelo menu interativo no terminal

## 🧾 Exemplo de Uso

```
=========================
       MENU PRINCIPAL
=========================
1.  Cadastrar cliente.
2.  Remover cliente.
...
9.  Alugar veiculo.
13. Registrar devolucao.
0.  Sair.
```

## 🛠️ Tecnologias

- Java 17+
- Armazenamento em memória (InMemory)

## 📌 Observações

- Dados são perdidos ao encerrar o programa (sem persistência em banco)
- Projeto acadêmico com foco em POO e boas práticas de design
