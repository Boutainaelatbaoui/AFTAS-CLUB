# Documentation pour la Configuration et l'Exécution des Applications dans Docker

Cette section fournit un guide détaillé sur la configuration et l'exécution des applications backend Spring Boot et frontend Angular dans un environnement Docker. Suivez attentivement ces étapes pour assurer une intégration réussie.

## Configuration du Backend (Spring Boot) :

### Étape 1: Vérification du Dockerfile

Assurez-vous que le fichier `Dockerfile` dans le répertoire du backend est correctement configuré. Le fichier doit ressembler à ceci :

```Dockerfile
FROM  maven:3.9.6-eclipse-temurin-21-jammy AS build

WORKDIR /app

COPY ./pom.xml ./pom.xml

COPY ./src ./src

RUN mvn clean package  -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/AFTASClub-backend-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]
 ```

### Étape 2: Construction de l'image Docker pour le Backend

Exécutez la commande suivante dans le terminal, situé à la racine du projet backend :

```bash
docker build -t backend-image ./AFTAS-backend
```

## Configuration du Frontend (Angular)

### Étape 1: Vérification du Dockerfile
Vérifiez que le fichier Dockerfile dans le répertoire du frontend est correctement configuré. Voici un exemple :

```
FROM node:18.13.0 as build

WORKDIR /app

COPY package*.json ./

RUN npm install

RUN npm install -g @angular/cli

COPY . .

RUN ng build --configuration=production

FROM nginx:latest

COPY --from=build app/dist/aftas-frontend /usr/share/nginx/html

EXPOSE 80
```

### Étape 2: Construction de l'image Docker
Exécutez la commande suivante dans le terminal, situé à la racine du projet frontend :

````
docker build -t nom-du-frontend chemin-vers-le-frontend
````

## Configuration de Docker Compose
### Étape 1: Vérification du fichier docker-compose.yml
Si vous utilisez Docker Compose, assurez-vous que le fichier docker-compose.yml est correctement configuré. Voici un exemple :

````
version: '3'

services:
  mysql:
    image: mysql:latest
    environment:
      MYSQL_ALLOW_EMPTY_PASSWORD: "yes"
      MYSQL_DATABASE: aftas_club
    ports:
      - "3306:3306"

  spring-app:
    build:
      context: ./AFTAS-backend
      dockerfile: Dockerfile
    ports:
      - "8081:8081"
    depends_on:
      - mysql
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/aftas_club?createDatabaseIfNotExist=true
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: ""

  aftas-frontend:
    build:
      context: ./AFTAS-frontend/aftas-frontend
      dockerfile: Dockerfile
    ports:
      - "80:80"
    depends_on:
      - spring-app

````

### Étape 2: Création et Démarrage des Conteneurs
Exécutez la commande suivante dans le terminal :

````docker-compose up --build ````

## Vérification
Ouvrez votre navigateur et accédez à l'URL spécifiée dans votre application frontend (par défaut, http://localhost:4200).
Confirmez que votre application fonctionne correctement dans l'environnement Docker.


