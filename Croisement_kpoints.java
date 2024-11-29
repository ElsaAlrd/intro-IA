package operateurs.croisement;

import java.util.Arrays;
import java.util.Random;
import representation.Solution;

public class Croisement_kpoints extends Croisement {

    private int k;

    public Croisement_kpoints(Solution parent1, Solution parent2, double proba, int k) {
        super(parent1, parent2, proba);
        this.k = k;
    }

    @Override
    public void croiser() {
        int nb_variables_decision = parent1.getNb_variables_decision();

        enfant1 = new Solution(parent1);
        enfant2 = new Solution(parent2);

        Random random = new Random();
        double aleatoire = random.nextDouble();

        if (aleatoire <= proba) {
            int[] points = new int[k];
            for (int i = 0; i < k; i++) {
                points[i] = random.nextInt(nb_variables_decision);
            }
            Arrays.sort(points);

            boolean swap = false;
            int start = 0;

            for (int point : points) {
                if (swap) {
                    for (int i = start; i < point; i++) {
                        enfant1.setVariable(i, parent2.getDoubleVariable(i));
                        enfant2.setVariable(i, parent1.getDoubleVariable(i));
                    }
                }
                swap = !swap;
                start = point;
            }

            if (swap) {
                for (int i = start; i < nb_variables_decision; i++) {
                    enfant1.setVariable(i, parent2.getDoubleVariable(i));
                    enfant2.setVariable(i, parent1.getDoubleVariable(i));
                }
            }
        }
    }
}
