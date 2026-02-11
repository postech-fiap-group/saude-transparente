# Saúde Transparente

Sistema de avaliação e transparência do atendimento SUS, permitindo cadastrar pacientes e médicos, registrar consultas e publicar avaliações, além de estatísticas e ranking por especialidade.

## Índice
- Visão geral
- Stack e arquitetura
- Como rodar (Docker e local)
- Configuração
- Migrações de banco (Flyway)
- Documentação da API (Swagger)
- Principais recursos/endereços
- Desenvolvimento e testes
- Troubleshooting

## Visão geral
Este projeto é um backend Spring Boot (Java 21) com MySQL e Flyway. Ele expõe APIs REST para:
- Pacientes: cadastro e consulta
- Médicos: cadastro, consulta e estatísticas por especialidade
- Consultas: registro e consulta
- Avaliações: criação, alteração e listagem
- Ranking: ranking de especialidades e notas

## Stack e arquitetura
- Linguagem: Java 21
- Framework: Spring Boot 4.0.1 (Web MVC, Validation, Data JPA)
- Banco: MySQL 8
- Migrações: Flyway
- Documentação: springdoc-openapi (Swagger UI)
- Build: Maven
- Containers: Docker/Docker Compose

Estrutura em camadas (clean architecture simplificada):
- `domain`: entidades, casos de uso, regras de negócio
- `application`: controladores (REST), apresentação e DTOs
- `infrastructure`: modelos persistentes e repositórios JPA

## Como rodar

### 1) Com Docker (recomendado)
Requisitos: Docker e Docker Compose instalados.

Suba banco e app:
```powershell
# Dentro da pasta do projeto
docker compose up --build
```

- App: acessível em `http://localhost:8081` (mapeia a porta interna 8083)
- MySQL: acessível em `localhost:3307`

Para parar e remover containers/volumes:
```powershell
docker compose down -v
```

### 2) Localmente (sem Docker)
Requisitos: JDK 21 e Maven.

1. Suba um MySQL local em `localhost:3307` com base `appdb`, usuário `appuser`, senha `apppassword` (ou ajuste `src/main/resources/application.yaml`).
2. Rode a aplicação:
```powershell
# Compilar e rodar testes
./mvnw clean verify

# Executar a aplicação
./mvnw spring-boot:run
```
Aplicação iniciará em `http://localhost:8083`.

## Configuração
Arquivo: `src/main/resources/application.yaml`
Principais chaves:
- `spring.datasource.url`: `jdbc:mysql://localhost:3307/appdb?useSSL=false&allowPublicKeyRetrieval=true`
- `spring.datasource.username`: `appuser`
- `spring.datasource.password`: `apppassword`
- `server.port`: `8083`
- `springdoc.swagger-ui.path`: `/swagger-ui`

No Docker Compose (`docker-compose.yml`):
- App expõe `8081` (mapeia `8083` interno)
- Banco expõe `3307` (mapeia `3306` interno)

## Migrações de banco (Flyway)
Scripts em `src/main/resources/db/migration`:
- `V1.000__create_table_paciente.sql`
- `V2.000__create_table_medico.sql`
- `V3.000__create_table_consulta.sql`
- `V4.000__create_table_avaliacao.sql`

Configurações úteis (dev):
- `repair-on-migrate: true`
- `baseline-on-migrate: true`
- `validate-on-migrate: false`
- `clean-on-validation-error: true`

## Documentação da API (Swagger)
Após subir a aplicação:
- OpenAPI JSON: `http://localhost:8081/v3/api-docs` (Docker) ou `http://localhost:8083/v3/api-docs` (local)
- Swagger UI: `http://localhost:8081/swagger-ui` (Docker) ou `http://localhost:8083/swagger-ui`

## Principais recursos/endereços
Com base nos controladores em `application/controller`:

- Paciente (`PacienteController`)
  - Endpoints típicos: cadastro, atualização, listagem e busca por ID

- Médico (`MedicoController`)
  - Endpoints típicos: cadastro, atualização, listagem e busca por ID
  - Estatísticas por especialidade

- Consulta (`ConsultaController`)
  - Endpoints típicos: criação, listagem e busca por paciente/médico

- Avaliação (`AvaliacaoController`)
  - Endpoints típicos: criação, alteração, listagem

- Ranking (`RankingController`)
  - Endpoints: ranking por especialidade, notas e scores

Observação: os paths e modelos completos aparecem no Swagger UI.

## Desenvolvimento e testes

Rodar suíte completa:
```powershell
./mvnw clean verify
```
Executar apenas testes:
```powershell
./mvnw test
```

## Troubleshooting
- Porta 8081 ocupada (Docker): pare serviços usando a porta ou altere o mapeamento em `docker-compose.yml`.
- Banco não sobe: verifique logs do container `mysql_saude_app` e se a porta `3307` está livre.
- Erros de migração Flyway: em dev, as flags no `application.yaml` ajudam a reparar/baseline. Nunca use `clean-on-validation-error` em produção.
- Swagger não abre: confirme `springdoc.swagger-ui.enabled: true` e o path correto (`/swagger-ui`).

---
Projeto acadêmico FIAP — Saúde Transparente.
