FROM openjdk:17-jdk-alpine

# Variáveis de ambiente
ENV SPRING_DATASOURCE_URL jdbc:postgresql://localhost:5432/mydb
ENV SPRING_DATASOURCE_USERNAME myuser
ENV SPRING_DATASOURCE_PASSWORD mypassword

# Copia o arquivo JAR gerado pelo build
COPY /target/*.jar app.jar

# Comando para executar a aplicação
CMD ["java", "-jar", "app.jar"]