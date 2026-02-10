FROM ubuntu:latest as build

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven
WORKDIR /logistico

COPY pom.xml .
RUN mvn dependency:go-offline

COPY . .

# CORREÇÃO 1: Adicionado -DskipTests para não validar banco de dados no build
RUN mvn clean package -Dmaven.test.skip=true

FROM eclipse-temurin:17-jre-focal

WORKDIR /portaria

# CORREÇÃO 2: Verifique se o nome do JAR no target está correto
COPY --from=build /logistico/target/logistico-0.0.1-SNAPSHOT.jar logistico.jar

EXPOSE 8080
ENV DATA_DIR=/var/lib/data

# CORREÇÃO 3: O nome do arquivo aqui deve ser o mesmo do COPY acima (logistico.jar)
CMD ["java","-jar","logistico.jar"]