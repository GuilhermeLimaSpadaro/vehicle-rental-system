🚗 Vehicle Rental System

Sistema backend de aluguel de veículos desenvolvido em Java puro utilizando JDBC, com arquitetura em camadas e modelagem relacional.

---

📌 Sobre o projeto

Este projeto simula um sistema de aluguel de veículos, permitindo o gerenciamento de clientes, veículos e contratos de locação (rentals), com persistência de dados em banco relacional utilizando JDBC.

O foco principal é praticar **boas práticas de backend**, incluindo organização de código, separação de responsabilidades e manipulação de banco de dados com SQL puro.

---

⚙️ Tecnologias utilizadas

* Java 17+
* JDBC
* MySQL (ou outro banco relacional)
* SQL
* Programação Orientada a Objetos (POO)

---

🧱 Arquitetura do projeto

O sistema segue uma arquitetura em camadas:

* **Domain** → Entidades e enums (Client, Vehicle, Rental)
* **Repository (DAO)** → Acesso ao banco de dados via JDBC
* **Exception** → Tratamento de erros personalizados

---

📦 Funcionalidades

👤 Clientes

* Cadastrar cliente
* Buscar cliente por ID
* Listar clientes
* Remover cliente

🚗 Veículos

* Cadastrar veículo
* Buscar veículo por ID
* Listar veículos
* Remover veículo

📄 Aluguéis (Rentals)

* Criar contrato de aluguel
* Buscar aluguel por ID
* Listar todos os contratos
* Remover aluguel

---

🧠 Conceitos aplicados

* JDBC puro
* JOIN entre tabelas
* Mapeamento manual de ResultSet para objetos
* Composição de entidades
* Padrão DAO (Repository)
* Tratamento de exceções customizadas
* Controle de instância de objetos (Identity Map)

---

🗂️ Estrutura básica

```
Domain
 ├── entities
 ├── enums

Repository
 ├── implementation (JDBC)
 ├── interfaces

Exception
```

---

🎯 Objetivo do projeto

Este projeto foi desenvolvido com foco em aprendizado prático de backend Java, simulação de regras de negócio reais e preparação para uso de frameworks como Spring Boot e JPA/Hibernate.

---

🚀 Próximos passos

* Migração para Spring Boot
* Implementação de API REST
* Uso de JPA/Hibernate
* Testes automatizados (JUnit)

---

👨‍💻 Autor

Desenvolvido por [Guilherme Lima Spadaro]

---

📌 Observação

Este projeto é focado em aprendizado e evolução em backend Java, simulando um sistema real de gerenciamento de aluguel de veículos.
