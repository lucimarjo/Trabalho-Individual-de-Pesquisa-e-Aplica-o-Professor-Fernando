## 6. Estudo de caso

Considere a seguinte situação:

> Um aluno desenvolveu uma API de pedidos. O sistema funciona em seu computador, mas apresenta erros em outra máquina. As credenciais do banco estão no `application.properties`, não existem testes, a aplicação depende da IDE e ninguém consegue verificar automaticamente se ela está saudável.

O aluno deverá propor uma solução e aplicá-la, quando compatível, à API desenvolvida, contendo:

- estratégia de testes para Service e Controller;
- geração do JAR;
- configuração por variáveis de ambiente;
- Dockerfile;
- Docker Compose com PostgreSQL;
- armazenamento persistente do banco;
- logs adequados;
- Actuator com endpoint de health.

Para cada item, explicar:

1. qual problema ele resolve;
2. como seria aplicado;
3. quais cuidados seriam necessários.

# Solução

Para resolver o problema proposto o aluno deve estruturar a API usando boas práticas de programação, testes automatizados e containerização com Docker.

## 1. Estratégia de testes para Service e Controller

### 1. Qual problema ele resolve:

Não possuir testes faz com que erros passem despercebidos durante o desenvolvimento. Isso dificulta identificar problemas antes de publicar a aplicação.

### 2. Como seria aplicado:

- Service: Utilizaria JUnit e Mockito para testar isoladamente as regras de negócio;
- Controller: Utilizaria MockMvc para testar os endpoints HTTP, verificando respostas, códigos de status e integração com a camada de serviço.

### 3. Quais cuidados seriam necessários:

- Testar cenários de sucesso e de erro;
- Não depender de um banco de dados real nos testes unitários;
- Verificar se os mocks representam corretamente o comportamento esperado;
- Verificar o resultado final do teste.

## 2. Geração do JAR

### 1. Qual problema ele resolve:

A aplicação, por depender da IDE para funcionar, dificuta a execução em outras máquinas

### 2. Como seria aplicado:

O Maven permite compilar e empacotar a aplicação em um arquivo JAR, que possui a aplicação pronta para execução.

Deverá executar no terminal

```bash
mvn clean package
```

E agora com o arquivo JAR terá a aplicação completa para exportar.

### 3. Quais cuidados seriam necessários:

- Verificar se o projeto compila corretamente;
- Garantir que as dependências estejam configuradas no Maven;
- Utilizar uma versão do Java compatível com o projeto.

## 3. Configuração por variáveis de ambiente

### 1. Qual problema ele resolve:

Como as informações confidenciais estão no `application.properties`, elas serão expostas na publicação e ainda dificultará a configuração em um ambiente diferente.

### 2. Como seria aplicado:

Deverá substituir as credenciais por variáveis de ambiente.

### 3. Quais cuidados seriam necessários:

- Não publicar senhas no GitHub;
- Configurar corretamente as variáveis em cada ambiente;
- Não utilizar credenciais de produção em ambientes de desenvolvimento.

## Dockerfile

### 1. Qual problema ele resolve:

A aplicação funciona apenas no computador do desenvolvedor porque depende das configurações e ferramentas instaladas localmente.

### 2. Como seria aplicado:

Criar um arquivo chamado Dockerfile na raiz do projeto para construir uma imagem da aplicação.

### 3. Quais cuidados seriam necessários:

- Utilizar uma imagem compatível com a versão Java do projeto;
- Não incluir arquivos desnecessários na imagem.

## Docker Compose com PostgreSQL

### 1. Qual problema ele resolve:

Instalar e configurar manualmente o banco de dados em cada máquina gera diferenças de ambiente e dificulta a execução da aplicação.

### 2. Como seria aplicado:

Criar um arquivo compose.yaml para executar a API e o PostgreSQL juntos.

### 3. Quais cuidados seriam necessários:

- Não compartilhar o arquivo .env publicamente.
- Utilizar versões compatíveis das imagens.
- Utilizar o nome do serviço (postgres) como endereço do banco dentro do Docker Compose, e não localhost.

## Armazenamento persistente do banco

### 1. Qual problema ele resolve:

Quando um container do PostgreSQL é removido, os dados armazenados nele podem ser perdidos caso não exista armazenamento persistente.

### 2. Como seria aplicado:

Ao utilizar volumes para armazenar os dados, eles ficarão armazenados no volume gerenciado pelo Docker.

### 3. Quais cuidados seriam necessários:

- Criar backups periódicos do banco.
- Proteger os dados armazenados.

## Logs adequados

### 1. Qual problema ele resolve:

Sem logs, fica difícil identificar erros, acompanhar o funcionamento da aplicação e descobrir por que determinado recurso não está funcionando.

### 2. Como seria aplicado:

Utilizar o sistema de logs do Spring Boot.

### 3. Quais cuidados seriam necessários:

- Não registrar senhas, tokens ou dados sensíveis.
- Evitar gerar logs excessivos e desnecessários.

## Actuator com endpoint de health.

### 1. Qual problema ele resolve:

Não existe uma maneira automatizada de verificar se a aplicação está funcionando corretamente.

### 2. Como seria aplicado:

Adicionando o Actuator, após iniciar a aplicação e acessar `http://localhost:8080/actuator/health` terá a resposta se a API está funcionando corretamente.
Além de que a o Actuator permite verificar outros componentes da aplicação, como a conexão com o banco de dados.

### 3. Quais cuidados seriam necessários:

- Não expor informações internas desnecessariamente;
- Proteger endpoints administrativos;
- Configurar verificações adequadas.
