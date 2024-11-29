package operateurs.selection;

import java.util.ArrayList;
import java.util.Random;

import representation.Solution;

public class Selection_roulette extends Selection {

    private Random random;

    public Selection_roulette(ArrayList<Solution> population) {
        super(population);
        this.random = new Random();
    }

    @Override
    public Solution selectionner() {
        double totalFitness = population.stream()
                                        .mapToDouble(Solution::getF)
                                        .sum();
        double seuil = random.nextDouble() * totalFitness;

        double cumul = 0.0;
        for (Solution solution : population) {
            cumul += solution.getF();
            if (cumul >= seuil) {
                return solution;
            }
        }
        return population.get(population.size() - 1);
    }
}
