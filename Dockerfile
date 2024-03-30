# Usei a imagem base do OpenJDK para Java 17
FROM amazoncorretto:17.0.10-al2023-headless

# Defini o diretório de trabalho dentro do contêiner para
WORKDIR /app

# Copiei o arquivo JAR da aplicação para o contêiner
COPY target/contador-vendas-0.0.1-SNAPSHOT.jar /app

# Comando para executar a aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "contador-vendas-0.0.1-SNAPSHOT.jar"]


