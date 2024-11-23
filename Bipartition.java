package tp1_vEtudiants.exercice2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Bipartition {

    // Listes d'ensembles
    static ArrayList<Integer> E = new ArrayList<Integer>(Arrays.asList(2, 10, 3, 8, 5, 7, 9, 5, 3, 2));
    static ArrayList<Integer> F = new ArrayList<Integer>(
            Arrays.asList(771, 121, 281, 854, 885, 734, 486, 1003, 83, 62)
    );

    // Sous-listes pour la partition
    static ArrayList<Integer> sListe1 = new ArrayList<Integer>();
    static ArrayList<Integer> sListe2 = new ArrayList<Integer>();

    public static void main(String[] args) {
        ArrayList<Integer> liste = F; // Changez ici pour tester avec E ou F

        System.out.print("Liste initiale : ");
        Collections.sort((List<Integer>) liste, Collections.reverseOrder()); // Trier en ordre décroissant
        afficher(liste);

        System.out.println("\nMéthode gloutonne :");
        completerSousEnsembleLePlusPetit(liste);

        System.out.print("Sous-liste 1 : ");
        afficher(sListe1);

        System.out.print("Sous-liste 2 : ");
        afficher(sListe2);

        // Réinitialiser les sous-listes
        sListe1.clear();
        sListe2.clear();

        System.out.println("\nMéthode avec moitié de la somme comme repère :");
        utiliserMotieSommeCommeRepere(liste);

        System.out.print("Sous-liste 1 : ");
        afficher(sListe1);

        System.out.print("Sous-liste 2 : ");
        afficher(sListe2);
    }

    /**
     * Méthode 1 : Approche gloutonne
     * Trier l'ensemble par ordre décroissant
     * Parcourir l'ensemble trié et ajouter chaque élément au sous-ensemble dont la somme est la plus petite.
     */
    public static void completerSousEnsembleLePlusPetit(ArrayList<Integer> liste) {
        for (int e : liste) {
            if (somme(sListe1) <= somme(sListe2)) {
                sListe1.add(e);
            } else {
                sListe2.add(e);
            }
        }
    }

    /**
     * Méthode 2 : Utiliser la moitié de la somme comme repère
     * Calculer la somme des éléments de la liste initiale
     * Diviser cette somme par 2 pour avoir un repère
     * Trier la liste initiale par ordre décroissant
     * Remplir la première sous-liste avec des éléments de la liste principale, en veillant à ne pas dépasser somme/2
     */
    public static void utiliserMotieSommeCommeRepere(ArrayList<Integer> liste) {
        int total = somme(liste); // Calculer la somme totale
        int moitié = total / 2;

        for (int e : liste) {
            if (somme(sListe1) + e <= moitié) {
                sListe1.add(e);
            } else {
                sListe2.add(e);
            }
        }
    }

    /**
     * Calculer la somme des éléments d'une liste
     */
    public static int somme(ArrayList<Integer> liste) {
        int n = 0;
        for (int i : liste) {
            n += i;
        }
        return n;
    }

    /**
     * Afficher une liste et sa somme
     */
    public static void afficher(ArrayList<Integer> liste) {
        for (int i = 0; i < liste.size(); i++) {
            System.out.print(liste.get(i));
            if (i != liste.size() - 1)
                System.out.print(", ");
        }
        System.out.println(" (" + somme(liste) + ")");
    }
}
