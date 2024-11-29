package operateurs.croisement;

import representation.Solution;

public class Croisement_2points extends Croisement {

    public Croisement_2points(Solution parent1, Solution parent2, double proba) {
        super(parent1, parent2, proba);
    }

    @Override
    public void croiser() {

        int nb_variables_decision = parent1.getNb_variables_decision();

        enfant1 = new Solution(parent1);
        enfant2 = new Solution(parent2);

        double aleatoire = Math.random();

        if (aleatoire <= proba) {

            int point1 = (int) (Math.random() * nb_variables_decision);
            int point2 = (int) (Math.random() * nb_variables_decision);

            if (point1 > point2) {
                int temp = point1;
                point1 = point2;
                point2 = temp;
            }

            for (int i = 0; i < point1; i++) {
                enfant1.setVariable(i, parent1.getDoubleVariable(i));
                enfant2.setVariable(i, parent2.getDoubleVariable(i));
            }

            for (int i = point1; i < point2; i++) {
                enfant1.setVariable(i, parent2.getDoubleVariable(i));
                enfant2.setVariable(i, parent1.getDoubleVariable(i));
            }

            for (int i = point2; i < nb_variables_decision; i++) {
                enfant1.setVariable(i, parent1.getDoubleVariable(i));
                enfant2.setVariable(i, parent2.getDoubleVariable(i));
            }

        }
    }

}
