# Usa a imagem base do Java 21 versão JRE
FROM eclipse-temurin:21-jre 

# Cria e define a pasta de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR para dentro do container e renomeia para app.jar
COPY target/*.jar app.jar

# Indica que a aplicação usa a porta 8080
EXPOSE 8080

# Define os comandos executados ao iniciar o container
ENTRYPOINT [ "java", "-jar", "app.jar" ]