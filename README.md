# AwesomePasswordChecker

Une bibliothèque Java avancée pour évaluer la force des mots de passe en utilisant une approche basée sur le clustering hiérarchique (HAC).

## Description

`AwesomePasswordChecker` est un vérificateur de mots de passe qui utilise l'apprentissage automatique pour évaluer la robustesse des mots de passe. Au lieu de simplement vérifier la longueur ou la présence de caractères spéciaux, cette bibliothèque analyse les patterns des mots de passe et les compare à des centres de clusters prédéfinis pour déterminer leur force.

## Fonctionnalités

- **Évaluation intelligente** : Utilise une approche basée sur le clustering pour analyser les patterns de mots de passe
- **Analyse de distance** : Calcule la distance euclidienne entre le mot de passe et les centres de clusters
- **Masquage de caractères** : Génère un masque représentant les caractéristiques du mot de passe
- **Hash MD5** : Implémentation personnalisée du hash MD5
- **Pattern Singleton** : Instance unique pour optimiser les performances
- **Configuration flexible** : Permet d'utiliser des fichiers de clusters personnalisés

## Installation

### Prérequis

- Java 11 ou supérieur
- Maven 3.6 ou supérieur

### Compilation

```bash
mvn clean install
```

### Ajout à votre projet Maven

```xml
<dependency>
    <groupId>org.example</groupId>
    <artifactId>TP1</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

## Usage

### Exemple basique

```java
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Obtenir l'instance du vérificateur
            AwesomePasswordChecker checker = AwesomePasswordChecker.getInstance();
            
            // Évaluer la force d'un mot de passe
            String password = "MyP@ssw0rd123";
            double distance = checker.getDistance(password);
            
            System.out.println("Distance: " + distance);
            
            // Plus la distance est élevée, plus le mot de passe est fort
            if (distance > 100) {
                System.out.println("Mot de passe fort ✓");
            } else if (distance > 50) {
                System.out.println("Mot de passe moyen");
            } else {
                System.out.println("Mot de passe faible ✗");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Utilisation avec un fichier de clusters personnalisé

```java
import java.io.File;
import java.io.IOException;

public class CustomClusterExample {
    public static void main(String[] args) {
        try {
            // Utiliser un fichier de clusters personnalisé
            File customClusterFile = new File("path/to/custom_clusters.csv");
            AwesomePasswordChecker checker = AwesomePasswordChecker.getInstance(customClusterFile);
            
            double distance = checker.getDistance("SecurePassword2024!");
            System.out.println("Distance: " + distance);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Génération de masque

```java
import java.io.IOException;

public class MaskExample {
    public static void main(String[] args) {
        try {
            AwesomePasswordChecker checker = AwesomePasswordChecker.getInstance();
            
            // Générer un masque pour analyser les patterns
            int[] mask = checker.maskAff("Password123!");
            
            System.out.println("Masque du mot de passe:");
            for (int i = 0; i < mask.length; i++) {
                System.out.print(mask[i] + " ");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Calcul du hash MD5

```java
public class MD5Example {
    public static void main(String[] args) {
        String input = "MonMotDePasse";
        String md5Hash = AwesomePasswordChecker.computeMd5(input);
        
        System.out.println("Hash MD5: " + md5Hash);
    }
}
```

## Comment ça marche

### 1. Masquage des caractères

Chaque caractère du mot de passe est encodé selon son type :
- **1** : Lettres minuscules communes (e, s, a, i, t, n, r, u, o, l)
- **2** : Autres lettres minuscules
- **3** : Lettres majuscules communes (E, S, A, I, T, N, R, U, O, L)
- **4** : Autres lettres majuscules
- **5** : Chiffres
- **6** : Caractères spéciaux (>, <, -, ?, ., /, !, %, @, &)
- **7** : Autres caractères

### 2. Calcul de distance

La bibliothèque calcule la distance euclidienne entre le masque du mot de passe et les centres de clusters prédéfinis. Une distance plus élevée indique un mot de passe plus unique et potentiellement plus fort.

### 3. Évaluation

La distance minimale aux centres de clusters est utilisée pour évaluer la force du mot de passe. Les mots de passe faibles ont tendance à être proches des patterns communs.

## Sécurité

**Important** : Cette bibliothèque est conçue pour évaluer la force des mots de passe, mais ne doit pas être utilisée seule pour la sécurité. Voir [Security.md](SECURITY.md)

## Licence

Voir le fichier [LICENSE](LICENSE) pour plus de détails.

## Contact

Pour toute question ou suggestion, n'hésitez pas à ouvrir une issue sur GitHub.

---

**Note** : Cette bibliothèque est fournie à des fins éducatives et de recherche.

