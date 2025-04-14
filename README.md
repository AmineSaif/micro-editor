# Micro Editor

Micro Editor est une application Java orientée objet qui permet de créer, manipuler et gérer des formes géométriques simples (rectangles, cercles, lignes, etc.) via une interface graphique intuitive. Elle intègre des fonctionnalités avancées telles que l'annulation/rétablissement des actions (undo/redo), la sérialisation des formes, et une architecture modulaire basée sur des design patterns éprouvés.

## 🚀 Fonctionnalités principales

-Ajout, déplacement et suppression de formes graphiques
-Support du glisser-déposer pour une manipulation intuitive
-Fonctionnalités d'annulation et de rétablissement (undo/redo)
-Sauvegarde et chargement de l'état des formes via sérialisation
-Interface graphique construite avec Swing
-Architecture modulaire facilitant l'extension et la maintenance

## 🧱 Architecture et design patterns
Le projet est structuré autour de plusieurs design patterns pour assurer une architecture propre et maintenable:

- **Command Pattern**  Encapsulation des actions utilisateur (ajout, suppression, déplacement) pour faciliter l'annulation/rétablissemen.
- **Composite Pattern**  Gestion hiérarchique des formes, permettant de traiter des groupes de formes comme des entités unique.
- **Prototype Pattern**  Clonage des formes pour des duplications rapides et efficace.
- **Observer Pattern**  Mise à jour automatique de l'interface graphique en réponse aux modifications du modèle de donnée.

## 🗂️ Structure du proje


```bash
micro-editor/
├── src/
│   ├── editor/           # Composants de l'éditeur graphique
│   ├── commands/         # Implémentations des commandes (undo/redo)
│   ├── shapes/           # Définition des formes géométriques
│   ├── utils/            # Classes utilitaires (sérialisation, etc.)
│   └── Main.java         # Point d'entrée de l'application
├── toolbar.ser           # Fichier sérialisé pour la barre d'outils
├── pom.xml               # Configuration Maven
└── README.md             # Documentation du projet
``


## 🛠️ Prérequis
- Java 11 ou version ultériure
- Maven 3.6 ou version ultériure

## ⚙️ Compilation et exécution

1. **Cloner le dépôt :**

   ```bash
   git clone https://github.com/AmineSaif/micro-editor.git
   cd micro-editor
   ``


2. **Compiler le projet avec Maven :**

   ```bash
   mvn clean install
   ``


3. **Exécuter l'application :**

   ```bash
   mvn exec:java -Dexec.mainClass="Main"
   ``


## 🧪 Tets

Les tests unitaires sont situés dans le répertoire `src/test/jaa`. Pour exécuter les tets 


```bash
mvn test```


## 📄 Licnce

Ce projet est sous licence MIT. Voir le fichier [LICENSE](LICENSE) pour plus d'informaions.

## 🤝 Contribuer

Les contributions sont les bienveues ! Veuillez soumettre une *issue* ou une *pull request* pour proposer des améliorations ou signaler des prolèmes.
