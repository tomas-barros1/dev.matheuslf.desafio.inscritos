# 🚀 Desafio de Vaga – SIS Innov & Tech

## 🏢 Sobre a Empresa

Há mais de 20 anos no mercado, **a SIS Innov & Tech** é uma consultoria estratégica de **Inovação e Transformação Digital**.  
Nossa especialidade é **impulsionar as demandas de nossos clientes**, integrando processos, pessoas e tecnologia de alta performance.

---

## 💼 Sobre o Cargo: Desenvolvedor Java

### 🧩 Requisitos Obrigatórios
- ☁️ Desenvolvimento para **nuvem**
- 💻 **Backend Java**
- 🔗 Desenvolvimento de **API REST**
- 🧪 **Testes Unitários**, Integrados, API e Front
- 🐳 **Docker** e **Kubernetes**
- 🧮 Banco de dados Relacional e SQL (**DB2**, **Oracle**)
- 📊 Observabilidade e Monitoração

### 🌟 Requisitos Desejáveis
- 💡 Experiência com **Angular 6+**, **JavaScript**

### ⚙️ Responsabilidades
- Traduzir requisitos de negócio em soluções técnicas eficientes e escaláveis.  
- Trabalhar com bancos de dados garantindo eficiência e segurança no armazenamento de dados.  
- Realizar consultas complexas para análise e manipulação de dados.  
- Participar ativamente de cerimônias ágeis (sprint planning, retrospectivas, etc).

---

## 🏠 Modelo de Trabalho
**100% Remoto**

### 📃 Contratação CLT
**Benefícios:**
- 🍛 Vale Refeição (Cartão Swile)  
- 🩺 Plano de Saúde  
- 😁 Plano Odontológico  
- 💻 Auxílio Home Office  
- 🎓 Acesso à **Alura**  
- 🎂 Crédito Aniversário (Cartão Swile)

### 💼 Contratação PJ
**Benefício:** 30 dias de descanso remunerado ao ano.

---

## 🧠 Desafio Técnico – Sistema de Gestão de Projetos e Demandas

### 📘 Contexto
Sua missão é desenvolver uma **API RESTful em Java com Spring Boot** para gerenciar **projetos e tarefas (demandas)** de uma empresa.  
O sistema será utilizado por um time de desenvolvimento para organizar suas entregas, acompanhar o status das tarefas e realizar análises simples.

---

## 🎯 Requisitos Técnicos

### 🧱 1. Modelagem de Domínio

#### `Project`
| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | UUID/Long | Identificador |
| `name` | String (3–100) | **Obrigatório** |
| `description` | String | Opcional |
| `startDate` | Date | Início do projeto |
| `endDate` | Date | Opcional |

#### `Task`
| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | UUID/Long | Identificador |
| `title` | String (5–150) | **Obrigatório** |
| `description` | String | Detalhes da tarefa |
| `status` | Enum | TODO / DOING / DONE |
| `priority` | Enum | LOW / MEDIUM / HIGH |
| `dueDate` | Date | Data limite |
| `projectId` | FK(Project) | Relacionamento |

---

### 🌐 2. Endpoints REST

| Método | Endpoint | Descrição |
|---------|-----------|-----------|
| **POST** | `/projects` | Criar novo projeto (`name` obrigatório) |
| **GET** | `/projects` | Listar todos os projetos (paginação opcional) |
| **POST** | `/tasks` | Criar nova tarefa vinculada a um projeto |
| **GET** | `/tasks?status=&priority=&projectId=` | Buscar tarefas com filtros opcionais |
| **PUT** | `/tasks/{id}/status` | Atualizar apenas o status da tarefa |
| **DELETE** | `/tasks/{id}` | Remover tarefa |

---

## ✅ Requisitos Obrigatórios
- 🧑‍💻 **Java 17+** e **Spring Boot 3+**  
- 🧠 **Spring Data JPA**  
- 🗄️ Banco Relacional (**PostgreSQL**, **Oracle** ou **H2**)  
- ✔️ **Bean Validation**  
- 🧪 **Testes Automatizados**  
  - Unitários (Services mockados)  
  - Integração (Controllers com MockMvc ou Testcontainers)  
- ⚠️ Tratamento de erros com `@ControllerAdvice`  
- 📦 Uso de **DTOs** (`record` ou classes simples)  
- 📘 **README** explicando como rodar o projeto

---

## 🏅 Diferenciais (Pontos Extras)
- 🧭 Documentação **Swagger / OpenAPI**  
- 🔐 Autenticação simples com **JWT** ou Basic Auth  
- 🐳 Configuração de **Docker** / **docker-compose**  
- ⚡ Uso de **MapStruct** para mapeamento de DTOs  
- 🔍 Testes de API com **RestAssured**

---

## 🛠️ Tags
`#Java` `#SpringBoot` `#Backend` `#DesafioTecnico`  
`#API` `#RestAPI` `#Docker` `#Kubernetes`  
`#PostgreSQL` `#Oracle` `#JPA` `#Swagger`  
`#RestAssured` `#CleanCode` `#SoftwareEngineering`

---

### 💡 Dica
> Foque em **organização, boas práticas e clareza do código**.  
> Um bom README e commits bem descritos também serão avaliados. 😉

---

### 🧾 Licença
Este projeto foi desenvolvido exclusivamente para o **processo seletivo SIS Innov & Tech** e não deve ser utilizado para fins comerciais.

---
