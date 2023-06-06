## 💻 Sobre o projeto  api-rest-full-barbearia
___

## 🛠 Tecnologias

As seguintes tecnologias foram utilizadas no desenvolvimento da API Rest do projeto:

- **[Java 17](https://www.oracle.com/java)**
- **[Spring Boot 3](https://spring.io/projects/spring-boot)**
- **[Maven](https://maven.apache.org)**
- **[MySQL](https://www.mysql.com)**
- **[Hibernate](https://hibernate.org)**
- **[Flyway](https://flywaydb.org)**
- **[Lombok](https://projectlombok.org)**
___

## ⚙️ Funcionalidades

- [x] CRUD de Barbeiro;
- [x] CRUD de Cliente;
- [x] Agendamento dos cortes;
- [x] Cancelamento dos agendamentos.
___

## 📄 Documentação

Usei o padrão de projeto "Strategy".

Princípios do SOLID
Apliquei os principios do SOLID, de uma vez, três princípios do SOLID, que é uma lista com cinco princípios de boas práticas de programação orientada a objetos.

Aplicando os seguintes princípios do SOLID:

- Single Responsibility Principle (Princípio da responsabilidade única): porque cada classe de validação tem apenas uma responsabilidade.


- Open-Closed Principle (Princípio aberto-fechado): na classe service, porque ela está fechada para modificação, não precisamos mexer nela. Mas ela está aberta para extensão, conseguimos adicionar novos validadores apenas criando as classes implementando a interface.

 
- Dependency Inversion Principle (Princípio da inversão de dependência): porque nossa classe service depende de uma abstração, que é a interface, não depende dos validadores, das implementações especificamente. O módulo de alto nível, a service, não depende dos módulos de baixo nível, que são os validadores.

 
- Com isso tenho um código fácil de entender, fácil de dar manutenção, fácil de estender e de testar com testes automatizados.