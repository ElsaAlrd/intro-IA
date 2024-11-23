package tp1_vEtudiants.exercice1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// Classe représentant une liste d'épreuves
public class ListeEpreuves {

    // Attribut pour stocker le nom du fichier contenant la liste des épreuves.
    private String nomFichier; 

    // Liste d'épreuves gérée par cette classe.
    private ArrayList<Epreuve> liste; 

    // Constructeur prenant en paramètre le nom du fichier.
    // Initialise la liste des épreuves et lit les données du fichier.
    public ListeEpreuves(String nomFichier) {
        this.nomFichier = nomFichier; 
        liste = new ArrayList<Epreuve>(); 

        // Appelle la méthode pour lire et charger les épreuves depuis le fichier.
        lireFichierEpreuves(nomFichier);
    }

    /**
     * Méthode pour lire le fichier contenant la liste des épreuves.
     */
    public void lireFichierEpreuves(String fichier) {
        try {
            FileReader fileReader = new FileReader(fichier); // Ouvre le fichier pour lecture.
            BufferedReader reader = new BufferedReader(fileReader); // Buffer pour lire les lignes.

            String ligne = reader.readLine(); // Lit la première ligne.

            // Boucle pour lire tout le fichier ligne par ligne.
            while (ligne != null) {

                if (!ligne.startsWith("#")) {
                    // Tokenize la ligne pour extraire les informations de l'épreuve.
                    StringTokenizer st = new StringTokenizer(ligne);
                    String nom = st.nextToken(); // Nom de l'épreuve.
                    String debut = st.nextToken(); // Date/heure de début.
                    String fin = st.nextToken(); // Date/heure de fin.

                    // Crée une instance d'épreuve et l'ajoute à la liste.
                    Epreuve epreuve = new Epreuve(nom, debut, fin);
                    liste.add(epreuve);
                }

                // Passe à la ligne suivante.
                ligne = reader.readLine();
            }
            reader.close(); // Ferme le fichier après lecture.
        } catch (IOException e) {
            e.printStackTrace(); // Affiche une erreur si un problème survient pendant la lecture.
        }
    }

    /**
     * Méthode pour supprimer toutes les épreuves qui sont en conflit avec une épreuve donnée.
     * Deux épreuves sont en conflit si leurs horaires se chevauchent.
     */
    public void eliminerConflits(Epreuve e) {
        // Parcourt la liste et supprime les épreuves en conflit avec l'épreuve donnée.
        liste.removeIf(epreuve -> 
            epreuve != e && // Ne pas considérer l'épreuve elle-même.
            epreuve.getDebut().before(e.getFin()) && // Conflit si début < fin de l'épreuve donnée.
            epreuve.getFin().after(e.getDebut()) // Et fin > début de l'épreuve donnée.
        );
    }

    /**
     * Méthode pour trier les épreuves par heure de fin croissante.
     * Utilise `Collections.sort` qui repose sur la méthode `compareTo` de la classe Epreuve.
     */
    public void triParHeureFin() {
        Collections.sort((List<Epreuve>) liste); // Trie la liste en place.
    }

    // Getter pour obtenir le nom du fichier associé à cette liste.
    public String getNomFichier() {
        return nomFichier;
    }

    // Setter pour modifier le nom du fichier.
    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    // Getter pour récupérer la liste des épreuves.
    public ArrayList<Epreuve> getListe() {
        return liste;
    }

    // Setter pour remplacer la liste des épreuves par une nouvelle liste.
    public void setListe(ArrayList<Epreuve> liste) {
        this.liste = liste;
    }

    /**
     * Méthode pour accéder à une épreuve par son indice dans la liste.
     * Permet un accès direct sans récupérer toute la liste.
     */
    public Epreuve get(int i) {
        return liste.get(i);
    }

    /**
     * Méthode pour ajouter une épreuve à une position spécifique dans la liste.
     * Si une épreuve existe déjà à cet indice, elle est décalée.
     */
    public void set(int i, Epreuve e) {
        liste.add(i, e);
    }

    /**
     * Méthode pour remplacer une épreuve à une position donnée.
     * L'épreuve existante est d'abord supprimée, puis la nouvelle est ajoutée.
     */
    public void replace(int i, Epreuve e) {
        liste.remove(i); // Supprime l'épreuve à l'indice donné.
        liste.add(i, e); // Ajoute la nouvelle épreuve à la même position.
    }

    // Méthode pour supprimer une épreuve à une position donnée dans la liste.
    public void remove(int i) {
        liste.remove(i);
    }

    /**
     * Méthode pour obtenir une représentation en chaîne de caractères de la liste des épreuves.
     * Chaque épreuve est convertie en texte et ajoutée à une chaîne avec un saut de ligne.
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < liste.size(); i++) {
            s += liste.get(i).toString() + "\n"; // Concatène chaque épreuve sous forme de texte.
        }
        return s; // Retourne la chaîne complète.
    }
}
