import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Epreuve implements Comparable<Epreuve> {
    String intitule;
    int debut;
    int fin;

    public Epreuve(String intitule, int debut, int fin) {
        this.intitule = intitule;
        this.debut = debut;
        this.fin = fin;
    }

    public boolean conflict(Epreuve other) {
        return this.fin > other.debut && this.debut < other.fin;
    }

    @Override
    public int compareTo(Epreuve other) {
        return Integer.compare(this.fin, other.fin);  // Tri par horaire de fin
    }
}

class ListeEpreuves {
    ArrayList<Epreuve> epreuves = new ArrayList<>();

    public void eliminerConflits(Epreuve e) {
        epreuves.removeIf(ep -> ep.conflict(e));
    }

    public ArrayList<Epreuve> planifierEpreuves() {
        Collections.sort(epreuves);
        ArrayList<Epreuve> planification = new ArrayList<>();
        int dernierFin = -1;
        for (Epreuve e : epreuves) {
            if (e.debut >= dernierFin) {
                planification.add(e);
                dernierFin = e.fin;
            }
        }
        return planification;
    }
}

public class Main {
    public static void main(String[] args) {
        ListeEpreuves liste = new ListeEpreuves();
        liste.epreuves.add(new Epreuve("Maths", 8, 10));
        liste.epreuves.add(new Epreuve("Physique", 10, 12));
        liste.epreuves.add(new Epreuve("Informatique", 9, 11));
        liste.epreuves.add(new Epreuve("Espagnol", 12, 14));

        // Test élimination des conflits
        System.out.println("Liste avant élimination des conflits avec Physique:");
        liste.eliminerConflits(new Epreuve("Physique", 10, 12));
        System.out.println(liste.epreuves);

        // Test planification des épreuves
        ArrayList<Epreuve> planification = liste.planifierEpreuves();
        System.out.println("Epreuves planifiées : " + planification);
    }
}
