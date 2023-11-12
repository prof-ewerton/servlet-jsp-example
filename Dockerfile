FROM maven:3.8.1-jdk-11-openj9 AS builder

# Copie os arquivos de origem do projeto para o contêiner
COPY . /usr/src/app
WORKDIR /usr/src/app

# Compile o projeto Maven
RUN mvn clean install package

# Use a imagem base que possui suporte ao Java e ao Tomcat
FROM tomcat:9.0.74-jdk11

# Copie o arquivo WAR gerado para o diretório de implantação do Tomcat
COPY --from=builder /usr/src/app/target/ROOT.war /usr/local/tomcat/webapps/

# Exponha a porta em que o Tomcat estará em execução
EXPOSE 8080

# Comando para iniciar o Tomcat
CMD ["catalina.sh", "run"]
