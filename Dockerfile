FROM openjdk:17-jdk-alpine

# Copia o arquivo JAR gerado pelo build
ADD /target/*.jar app.jar

# Expõe a porta 8080
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]