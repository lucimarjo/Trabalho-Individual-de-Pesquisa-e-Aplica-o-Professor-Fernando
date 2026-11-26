# Trabalho Back-End Frameworks

## Teste Automatizado

<!-- ta separado por topicos mas é pra juntar tudo depois
é só pra ajudar meu pequeno e liso cerebro a saber o que fiz -->

### O que é Teste Automatizado

É o ato de fazer um teste de software de forma automatica, ou seja, sem a intervenção manual. Com isso, o tempo e o esforço é reduzido de forma drástica ao automatizar tarefas repetitivas, permitindo identificar os pontos falhos de forma clara e objetiva. Assim, ao usar testes automatizados em nossos projetos como desenvolvedores oferece ganhos em diversas etapas da construção.

Existem diversos tipos de teste, **testes unitários** para testar unidades, **testes de integração** para testar componentes, **testes de aceitação** para testar funcionalidades.

### Qual é a diferença entre teste manual e teste automatizado?

Teste manual são aqueles executados por profissionais especializados da área de QA (_Quality Assurance_), relatando os erros do sistema em situações convencionais e não convencionais, como o uso sem internet, ao dirigir, usabilidade, layout e entre outros.

Já o teste automatizado são realizados com o auxílio de ferramentas e _frameworks_ que geram um script com entrada e saída, definindo se o mesmo passou ou falhou.
Há duas categorias de automação: a de caixa-preta, onde o desenvolvedor não tem conhecimento a cerca da arquitetura do sistema que está sendo testado, e caixa-branca, em que há o pleno entendimento da arquitetura interna.

### O que são _Arrange_, _Act_ e _Assert_?

Arrange, Act e Assert forma o Triple A, um padrão de projeto, é uma maneira de estruturar os testes ditando etapas de operações. São elas:

1. **_Arrange_**: Preparar o teste. Fazendo as configurações para tal, aqui entra configuração de banco de dados, login em aplicativos, inicialização de variáveis, etc;
2. **_Act_**: Rodar o teste. É onde acontece o teste de fato, é chamada a função ou método a ser testado, acessar uma API, interagir com uma página, entre outros;
3. **_Assert_**: Verificar as asserções. Finalização do teste, tendo a verificação dos resultados, sendo válido ou não

### Qual é a função do JUnit?

É usado para executar testes automatizados para classes e funções em Java com Spring Boot, geralmente, ajudando na detecção de erros e na melhoria da qualidade do código.

### Qual é a função do Mockito?

Assim como o JUnit, é usado para testes, mas diferente dele, Mockito é usado para simular as dependências da classe a ser testada.

### O que é um Mock?

Mock é um termo utilizado para descrever objetos que simulam objetos reais. São geralmente usados para simular objetos mais complexos como objetos com valores variáveis, objetos com estado difíceis de serem criados ou reproduzidos, objetos lentos, que necessitam de informações e métodos adicionais, entre outros.

### Por que um teste unitário de Service não deveria depender diretamente do PostgreSQL?

Testes unitários são usados apenas para testar as menores unidades de código, não deveria depender de alguma outra funcionalidade. Para testar um banco um Mock é mais indicado.

### Qual é a diferença entre @Mock e @InjectMocks?

_@Mock_ é usado para criar um objeto mock. Já _@InjectMocks_ é usado para injetar o objeto mock na classe a ser testada.

### Para que servem when(...).thenReturn(...), verify(...) e assertThrows(...)?

Os comandos _.when_ e _.thenReturn_ são utilizados para ensinar ao objeto _Mock_ o que ele deve fazer. Sendo que o _.when_ determina qual método é esperado ser chamado e com quais parâmetros. _.thenReturn_ dita qual valor será devolvido quando o método _when_ for chamado.
_.verify_ serve para confirmar se uma interação aconteceu com um objeto simulado, garantindo que o método passou pelo que devia passar.
Por fim, o _.assertThrows_ valida se o código lança uma exceção quando algo dá errado.

### Qual é a diferença entre um teste de Service e um teste de Controller?

Testes no ambiente de Service são usados para testar as regras de negócio da aplicação, como manipulação de dados, cálculos, lançamento de exceções, etc. Enquanto testes de Controller são para validar as requests e responses da aplicação, testando as rotas, métodos HTTP, status, entre outros.

### Para que serve o MockMvc?

MockMvc dá suporte para testar aplicações MVC (_Model, View, Controller_ ou Modelo, Visão, Controle), usada primariamente na camada de Controller e sem o uso de um servidor.

### Por que 100% de cobertura não garante que o sistema esteja correto?

Ter alta cobertura de código (ou \*Code Coverage) é muito importante na engenharia de software, já que garante uma mitigação de risco e garante a qualidade do produto. Porém, 100% de Code Coverage não previne o código de:

- Ter baixa segurança;
- Ter bugs;
- Erros em comportamentos não pensados para os métodos.

## Exemplo Obrigatório de Teste Automatizado

O teste deve ter

- documentação
- preparação do cenário
- configuração de Mock
- execução do metodo
- pelo menos uma asserção
- verificação de interação com o Repository

```Java
@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repository;

    @InjectMocks
    private ProdutoService service;

    @Test
    void deveCadastrarProdutoValido() {
        // Arrange

        // Act

        // Assert
    }
}
```

## Docker

### O que significa realizar o build de uma aplicação?

### O que o comando mvn clean package executa?

Este comando é usado para construir o projeto. Ao executar, exclui o diretório _"target"_, compila o código, executa os testes e empacota o código compilador por ele mesmo em _JAR_, _WAR_ ou _EAR_. Após isso o artefato ainda é armazenado no diretório _"target"_.

### O que é um arquivo JAR?

É um formato de arquivo utilizado pelo Java, ele compila todos os arquivos da aplicação em um só arquivo .JAR.

### Como executar uma aplicação Spring Boot sem utilizar o IntelliJ?

### Por que senhas e segredos não devem ficar no código-fonte?

Porque esses dados podem ser vistos por pessoas não autorizadas, podendo entrar no sistema e ver informações sigilosas e comprometedoras para a empresa. Para isso foi criado as Variáveis de ambiente.

### Como variáveis de ambiente ajudam a configurar a aplicação?

Elas ajudam ao esconder informações sigilosas como senhas, links e keys. Sem elas essas informações sairiam facilmente para circulação e pessoas não autorizadas poderiam entrar em bancos de dados e, por exemplo, ver as senhas dos usuários.

### O que são profiles do Spring?

Profiles são anotações usadas para inicializar beans para que esses funcionem em perfis específicos, como dev, teste, prod, entre outros. O código ficaria:

```Java
@Component
@Profile("dev")
public class nomeDaClasse
```

Para declarar o perfil usado para definir quais serão ativados existem algumas formas:

- via WebApplicationInitializer:

```Java
@Configuration
public class MyWebApplicationInitializer
  implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        servletContext.setInitParameter(
          "spring.profiles.active", "dev");
    }
}
```

- com Variáveis de Ambiente

```json
export spring_profiles_active=dev
```

- @ActiveProfile em testes

```Java
@ActiveProfiles("dev")
```

### Qual é a diferença entre uma imagem Docker e um container?

As imagens servem como modelos para os contêineres, sendo usados somente para leitura, contendo o código e as dependências da aplicação. Enquanto os contêineres são instâncias das imagens

### Qual é a função de FROM, WORKDIR, COPY e ENTRYPOINT em um Dockerfile?

Todos são comandos para dar instruções de como criar uma imagem.

- _FROM_: Especifica a imagem base;
- _WORKDIR_: Define o diretório de trabalho dentro do contêiner;
- _COPY_: Copia os arquivos do diretório atual do host para um outro;
- _ENTRYPOINT_: Define o comando a ser executado quando iniciar o container.

### Para que serve o Docker Compose?

Docker Compose é uma ferramenta que ajuda a gerenciar vários contêineres, definindo um arquivo `docker-compose.yml` para gerenciar tudo num único comando.

Muito usado para projetos mais complexos onde há um container para back-end, banco de dados e front-end, por exemplo.

### Por que uma API em container não deve utilizar localhost para acessar outro container?

Ao apontar o localhost, o container vai direcionar para si mesmo, não para a máquina hospedada. Isso acontece por que o container cria seu próprio ambiente de rede, funcionando como uma máquina própria, e, ao chamar um localhost, aponta para nada.

Para isso que existe alternativas como o Docker Compose, ele cria uma rede interna que conecta os contêineres.

### Para que servem volumes do Docker?

Volumes servem para manter os dados para serem usados novamente em outra inicialização do container. Foi criado pois os dados dentro de um container são temporários, sendo removidos quando o o mesmo é parado.

### Qual é a função dos logs?

Logs servem para descrever os processos ocorridos em uma aplicação, fornecendo informações cruciais para tratar de erros e incidentes no funcionamento.

### Quais informações não devem aparecer nos logs?

Dados sensíveis, credenciais e informações pessoais não devem aparecer nos logs.

### Para que serve o Spring Boot Actuator?

_Actuator_ fornece informações operacionais da aplicação, podendo monitorar o aplicativo, coletar métricas e entender o tráfego ou o estado do banco de dados. É muito bom por possuir recursos para produção já prontos.

### O que representa a resposta do endpoint /actuator/health?

_`/actuator/health`_ é uma dos Endpoints pré-definidos do Actuator, retornando um JSON contendo um resumo da saúde da aplicação, mostrando
_`"status": "UP"`_ para quando está funcionando, ou
_`"status": "DOWN"`_ para quando está com alguma falha.

Ainda é possível ter um retorno detalhado ao configurar no _`application.properties`_ habilitando a opção \*`management.endpoint.health.group.custom.show-details=`**`always`\***. Com isso, o retorno para _health_ fica mais detalhado:

```JSON
{
  "status": "UP",
  "components": {
    "diskSpace": {
      "status": "UP",
      "details": {
        "total": 499963170816,
        "free": 91300069376,
        "threshold": 10485760
      }
    },
    "ping": {
      "status": "UP"
    }
  }
}
```

## Exemplo obrigatório

Criar um Dockerfile simples
Criar e explicar uma configuração com API e PostgreSQL no Docker Compose

```Java
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/aplicacao.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

## Referências

Capterra. **Teste automatizado**. Disponível em: https://www.capterra.com.br/glossary/1281/automated-testing-and-quality-management-distributed-and-mainfra. Acesso em: 18 nov. 2026.

Zup. **Teste manual vs teste automatizado: diferenças e desafios**. Disponível em: https://zup.com.br/blog/teste-manual-vs-teste-automatizado/. Acesso em: 18 nov. 2026.

Sofist. **Integrando Testes Manuais (ou Humanos) e Automatizados**. https://www.sofist.co/blog/testes-manuais-e-testes-automatizados. Acesso em: 18 nov. 2026.

Medium. **O Padrão Triple A (Arrange,Act,Assert)**. https://medium.com/@pablodarde/o-padrão-triple-a-arrange-act-assert-741e2a94cf88. Acesso em: 19 nov. 2026.

Automation Panda. **ARRANGE-ACT-ASSERT: A PATTERN FOR WRITING GOOD TESTS**. https://automationpanda.com/2020/07/07/arrange-act-assert-a-pattern-for-writing-good-tests/. Acesso em: 19 nov. 2026.

Geeks for geeks. **Introduction to JUnit**. https://www.geeksforgeeks.org/advance-java/introduction-of-junit/. Acesso em: 19 nov. 2026.

Beatriz Gracia. **How to use Junit and Mockito for unit testing in Java**. https://dev.to/whathebea/how-to-use-junit-and-mockito-for-unit-testing-in-java-4pjb. Acesso em: 19 nov. 2026.

DevMedia. **Mocks: Introdução a Automatização de Testes com Mock Object**. https://www.devmedia.com.br/mocks-introducao-a-automatizacao-de-testes-com-mock-object/30641. Acesso em: 19 nov. 2026.

DevMedia. **verify() method in Mockito example**. https://dev.to/realnamehidden1_61/verify-method-in-mockito-example-gio. Acesso em: 19 nov. 2026.

HowToDoInJava. **JUnit 5 assertThrows(): Check Exception Type, Message, Cause**. https://howtodoinjava.com/junit/assertthrows/. Acesso em: 19 nov. 2026.

Spring. **MockMvc**. https://docs.spring.io/spring-framework/reference/testing/mockmvc.html. Acesso em: 20 nov. 2026.

Code4IT. **Why reaching 100% Code Coverage must NOT be your testing goal (with examples in C#)**. https://www.code4it.dev/blog/code-coverage-must-not-be-the-target/. Acesso em: 20 nov. 2026.

datacamp. **Docker para iniciantes: Um guia prático para contêineres**. https://www.datacamp.com/pt/tutorial/docker-tutorial. Acesso em: 20 nov. 2026.

Medium. **Logs no Docker**. https://medium.com/@gomex/logs-no-docker-c6f3c7fa6ee4. Acesso em: 20 nov. 2026.

Baeldung. **Spring Boot Actuator**. https://www.baeldung.com/spring-boot-actuators. Acesso em: 20 nov. 2026.

DIO. **mvn clean install and mvn clean package, qual a diferença**. https://www.dio.me/articles/mvn-clean-install-and-mvn-clean-package-qual-a-diferenca. Acesso em: 20 nov. 2026.

IBM. **Arquivos Java JAR e de classe**. https://www.ibm.com/docs/pt-br/i/7.6.0?topic=platform-java-jar-class-files. Acesso em: 20 nov. 2026.

Baeldung. **Spring Profiles**. https://www.baeldung.com/spring-profiles. Acesso em: 20 nov. 2026.

Medium. **Resolvendo problema de comunicação entre containers Docker**. https://medium.com/@raylanefernandes/resolvendo-problema-de-comunicação-entre-containers-docker-d6b12b2a3c6b. Acesso em: 20 nov. 2026.
