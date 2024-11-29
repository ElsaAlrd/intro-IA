package operateurs.selection;

import java.util.ArrayList;
import java.util.Random;
import representation.Solution;

public class Selection_tournoi extends Selection {

    private int tailleTournoi;

    public Selection_tournoi(ArrayList<Solution> population, int tailleTournoi) {
        super(population);
        this.tailleTournoi = tailleTournoi;
    }

    @Override
    public Solution selectionner() {
        Random random = new Random();
        ArrayList<Solution> tournoi = new ArrayList<>();

        for (int i = 0; i < tailleTournoi; i++) {
            int index = random.nextInt(population.size());
            tournoi.add(population.get(index));
        }

        Solution meilleurIndividu = tournoi.get(0);
        for (Solution individu : tournoi) {
            if (individu.getF() > meilleurIndividu.getF()) {
                meilleurIndividu = individu;
            }
        }

        return meilleurIndividu;
    }
}
