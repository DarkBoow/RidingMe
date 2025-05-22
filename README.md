# RidingMe

**RidingMe** est un plugin Spigot pour la version 1.16.5 qui inverse le comportement de monte classique dans Minecraft. Lorsqu’un joueur (ou toute entité) tente de monter une autre entité, c’est finalement l’entité ciblée qui se retrouve sur le dos du joueur. Le plugin permet ainsi de créer des « piles » d’entités et offre un moyen rapide de les dissocier en se baissant.

## Fonctionnement
- **Inversion de la monte** : à chaque `EntityMountEvent`, l’événement est annulé et l’entité d’origine devient le passager de celle qui essayait de la monter. Cette logique est gérée dans la classe `Events`.
- **Tâche régulière** : la classe `Task` s’exécute à chaque tick tant qu’il existe des inversions en attente. Elle ajoute effectivement les passagers et nettoie la liste des actions à traiter.
- **Désassemblage par furtivité** : lorsqu’un joueur s’accroupit (`PlayerToggleSneakEvent`), le dernier élément de la pile de passagers est retiré de son véhicule, permettant une libération rapide.

## Fichiers de configuration
### plugin.yml
Fichier principal de déclaration du plugin :

```yml
name: RidingMe
author: DarkBow_
version: 1.0
main: fr.darkbow_.ridingme.RidingMe
api-version: 1.16
```

Il définit le nom du plugin, son auteur, la classe principale et la version de l’API Spigot ciblée.

## Compilation / Installation
1. Assurez-vous de disposer de **Maven** et d’une version de Java 8 compatible avec Spigot.
2. Compilez le plugin :
   ```bash
   mvn package
   ```
3. Le fichier JAR généré se trouve dans `target/RidingMe-1.0.jar`.
4. Copiez ce JAR dans le dossier `plugins/` de votre serveur Spigot puis redémarrez celui-ci.

## Fichiers importants
- `src/main/java/fr/darkbow_/ridingme/RidingMe.java` : point d’entrée du plugin, gère l’initialisation et le stockage des inversions de monte.
- `src/main/java/fr/darkbow_/ridingme/Events.java` : écoute les événements de monte et de furtivité pour appliquer la logique d’inversion ou de désassemblage.
- `src/main/java/fr/darkbow_/ridingme/Task.java` : tâche planifiée qui ajoute les passagers au fil du temps et stoppe son exécution lorsque plus aucune inversion n’est en cours.
- `src/main/resources/plugin.yml` : descripteur du plugin pour Spigot.

## Utilisation rapide
- Installez le plugin puis lancez le serveur.
- Montez normalement une entité : elle se placera automatiquement sur vos épaules au lieu de l’inverse.
- Accroupissez-vous pour faire descendre le dernier passager de la pile.

## Licence et contributions
Aucune licence ou directive de contribution n’est fournie dans ce dépôt. Consultez l’auteur si vous souhaitez utiliser ou modifier le projet.

