# 📦 Smart Stock System

Uma **API RESTful** desenvolvida em **Java com Spring Boot** para controle automatizado de estoque, leitura de relatórios em CSV, tomada de decisão de recompra e integração com um setor de compras externo, com persistência dos resultados em **MongoDB**.

---

## 📋 Visão Geral

O **Stock Control System** simula um processo real de controle de estoque corporativo. A aplicação recebe um relatório CSV com informações de produtos, avalia regras de negócio para reposição automática e, quando necessário:

* Calcula a quantidade ideal de recompra
* Autentica em uma API externa (setor de compras)
* Envia solicitações de compra
* Persiste o resultado da operação no banco de dados

---

## 🚀 Tecnologias Utilizadas

* **Java 17**
* **Spring Boot**
* **Spring Web**
* **Spring Data MongoDB**
* **Spring Cloud OpenFeign** (integração entre serviços)
* **OpenCSV** (leitura de arquivos CSV)
* **Docker & Docker Compose**
* **MongoDB**
* **Mockoon**

---

## 🏗️ Arquitetura e Conceitos Aplicados

* Arquitetura em camadas (Controller, Service, Repository)
* Integração com API externa via **Feign Client**
* Autenticação com **Client Credentials**
* Processamento assíncrono com `CompletableFuture`
* Persistência orientada a documentos (MongoDB)
* Separação clara de responsabilidades
* Regras de negócio aplicadas no domínio

---

## ✨ Funcionalidades

### 📄 Leitura de Relatório de Estoque

* Importação de arquivo CSV contendo itens de estoque
* Mapeamento automático usando OpenCSV

### 📊 Análise de Estoque

* Verificação de quantidade disponível vs. limite mínimo
* Cálculo automático da quantidade de recompra (+20% do limite)

### 🛒 Integração com Setor de Compras

* Autenticação automática via API externa
* Envio de solicitações de compra usando Feign Client
* Tratamento de respostas de sucesso ou falha

### 🗄️ Persistência no MongoDB

* Registro completo de cada tentativa de recompra:

  * Dados do item
  * Quantidade em estoque
  * Quantidade comprada
  * Sucesso ou falha da operação
  * Data e hora da tentativa

### ⚙️ Execução Assíncrona

* Processamento iniciado via endpoint REST
* Execução em background sem bloquear a requisição HTTP

---

## 📚 Endpoints Disponíveis

### Iniciar Processamento de Estoque

```http
POST /start
```

**Request Body:**

```json
{
  "path": "/caminho/para/arquivo.csv"
}
```

**Resposta:**

* `202 Accepted` — processamento iniciado com sucesso

---

## 🛠️ Configuração do Ambiente

### Pré-requisitos

* Java 17+
* Maven 3.8+
* Docker e Docker Compose

### Subindo o MongoDB

```bash
docker-compose up -d
```

### Variáveis de Ambiente (opcional)

```bash
APP_CLIENT_ID=ABC
APP_CLIENT_SECRET=DEF
```

---

## ▶️ Executando a Aplicação

```bash
# Clonar o repositório
git clone https://github.com/jhops10/stock-control-system.git
cd stock-control-system

# Executar a aplicação
mvn spring-boot:run
```

---

## 📦 Estrutura do Projeto

```text
src/main/java/com/jhops10/stockcontrolsystem
├── client
├── config
├── controller
├── domain
├── entity
├── exception
├── repository
└── service
```

---


> Última atualização: Fevereiro 2026
