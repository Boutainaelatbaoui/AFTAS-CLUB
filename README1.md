# Projet Aftas Club

Le projet Aftas Club vise à moderniser la gestion des compétitions sportives du club Aftas à Tiznit. L'application web responsive permet à l'administration du club et au jury de gérer efficacement les compétitions de chasse sous-marine.

## Contexte du Projet

Aftas Club propose une variété d'activités sportives telles que le surf, le tennis, le quad, la pêche sous-marine et le parapente. Les compétitions de chasse sous-marine sont organisées régulièrement dans différentes régions du Maroc.

## Fonctionnalités Principales

- **Adhérent :** Gestion des informations des adhérents, y compris numéro d’adhésion, nom, prénom, pièce d’identification, nationalité et date d'adhésion.
- **Compétition :** Enregistrement des compétitions avec un code unique, date, heure de début et de fin, nombre de participants et lieu.

## Exigences Techniques

- Utilisation de Spring Boot pour l'API backend.
- Architecture en couches pour une organisation claire du projet.
- Validation de données pour assurer la qualité des informations.
- Gestion centralisée des exceptions avec RestControllerAdvice.
- Mise en place de la pagination pour une navigation facile.
- Tests unitaires pour le service de calcul des résultats des compétitions.

## Exigences Fonctionnelles

- Ajout d'une nouvelle compétition.
- Liste des compétitions avec filtres (en cours, fermées).
- Inscription des adhérents aux compétitions.
- Enregistrement des résultats de la compétition du jour.
- Affichage du podium des trois premiers participants.

## Configuration et Exécution du Projet

Suivez ces étapes pour configurer et exécuter le projet localement :

### Prérequis

Avant de commencer, assurez-vous d'avoir les éléments suivants installés sur votre machine :

- [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html) (version recommandée : 11)
- [Maven](https://maven.apache.org/download.cgi)
- [Node.js](https://nodejs.org/) (pour Angular)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) ou votre IDE préféré.

### Clonage du Projet

1. Ouvrez votre terminal.

2. Utilisez la commande suivante pour cloner le projet depuis le dépôt Git :

    ```bash
    git clone https://github.com/votre-utilisateur/aftas-club.git
    ```

### Importation dans l'IDE

1. Ouvrez IntelliJ IDEA.

2. Dans le menu principal, sélectionnez "File" -> "Open" et choisissez le répertoire du projet cloné.

3. L'IDE détectera automatiquement qu'il s'agit d'un projet Maven et effectuera les configurations nécessaires.

### Configuration de l'API Backend (Spring Boot)

1. Ouvrez le dossier du projet dans votre IDE.

2. Naviguez vers le fichier `application.properties` ou `application.yml` dans le répertoire `src/main/resources`.

3. Configurez les paramètres de base de données et autres configurations selon vos besoins.

### Exécution de l'API Backend

1. Recherchez et exécutez la classe principale `Application.java` dans le package `src/main/java`.

2. L'application Spring Boot sera lancée et accessible à l'adresse `http://localhost:8080`.

### Configuration de l'Interface Utilisateur (Angular)

1. Ouvrez le terminal.

2. Naviguez vers le répertoire `frontend` dans le projet.

3. Exécutez la commande suivante pour installer les dépendances Node.js :

    ```bash
    npm install
    ```

### Exécution de l'Interface Utilisateur

1. Dans le même répertoire `frontend`, exécutez la commande suivante pour lancer l'application Angular :

    ```bash
    ng serve
    ```

2. L'interface utilisateur sera accessible à l'adresse `http://localhost:4200`.

---

**Note :** Ces instructions sont une base générale. Assurez-vous d'adapter les étapes en fonction de la structure spécifique de votre projet.


## Licence

Ce projet est sous licence [MIT License](LICENSE).
