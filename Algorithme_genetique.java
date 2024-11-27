package algorithmes;

import java.util.ArrayList;
import java.util.Random;

import operateurs.selection.Selection_aleatoire;
import operateurs.croisement.Croisement;
import operateurs.croisement.Croisement_1point;
import operateurs.selection.Selection;
import problemes.Probleme;
import representation.Gene;
import representation.Solution;

public class Algorithme_genetique {

	private Probleme probleme;
	private int taille_population; 
	private int nb_generations; 

	private double probaCroisement; 
	private double probaMutation;

	private int nb_variables_decision; 

	private ArrayList<Solution> population; 
	private ArrayList<Solution> populationFille; 

	private Solution bestSolution;


	public Algorithme_genetique(Probleme probleme, int taille_population, int nb_generations, double probaCroisement, double probaMutation) {
		this.probleme = probleme;
		this.taille_population = taille_population; 
		this.nb_generations = nb_generations;
		this.probaCroisement = probaCroisement; 
		this.probaMutation = probaMutation;

		this.population = new ArrayList<Solution>(taille_population);

		this.nb_variables_decision = this.probleme.getNb_variables_decision();
	}

	public Solution executer() {

		genererPopulationInitiale();

		for (int i = 0; i < taille_population; i++) {
			probleme.evaluer(population.get(i));
		}

		bestSolution = getBestSolution(population); 

		Selection selection; 

		//Mutation mutation = new BitFlip(probaMutation);
		
		for (int generation = 1; generation < nb_generations; generation++) {

	
			selection = new Selection_aleatoire(population); 


			populationFille = new ArrayList<Solution>();

			for (int i = 0; i < taille_population; i +=2) {

				Solution parent1 = selection.selectionner();
				Solution parent2 = selection.selectionner();

				Croisement croisement; 
				croisement = new Croisement_1point(parent1, parent2, probaCroisement);
				croisement.croiser();

				Solution enfant1 = croisement.getEnfant1();
				Solution enfant2 = croisement.getEnfant2();

				// Mutation
				//enfant1 = mutation.muter(enfant1);
				//enfant2 = mutation.muter(enfant2);
				//

				probleme.evaluer(enfant1);
				probleme.evaluer(enfant2);

				populationFille.add(enfant1);
				populationFille.add(enfant2);

			} 

			remplacer();

			Solution bestSolutionPopulationCourante = getBestSolution(population);

			if (bestSolutionPopulationCourante.getF() > bestSolution.getF()) {
				bestSolution = bestSolutionPopulationCourante;
			}

		}

		return bestSolution;


	}

	public void genererPopulationInitiale() {

		for (int i = 0; i < taille_population; i++) {

			Solution s = new Solution(probleme.getType(), nb_variables_decision); 

			Gene[] chromosome = new Gene[nb_variables_decision]; 

			for (int j = 0; j < nb_variables_decision; j++) {
				double value = Math.random()* (probleme.getMax()[j] - probleme.getMin()[j]);
				Gene g = new Gene(value);

				chromosome[j] = new Gene(value, probleme.getMin()[j], probleme.getMax()[j]);
			}

			s.setChromosome(chromosome);

			population.add(s);

		}

	}

	public void remplacer () {

		population.clear();

		for (int i = 0; i < populationFille.size(); i++) {
			population.add(populationFille.get(i));
		}
	}

	public void remplacer_roulette () {

		ArrayList<Solution> population_melangee= new ArrayList<Solution>(); 

		for(int i = 0; i < population.size(); i++) {
			population_melangee.add(population.get(i));
			population_melangee.add(populationFille.get(i));
		}

		population.clear();
		Selection selection = new Selection_aleatoire(population); ; 

		for (int i = 0; i < taille_population; i++) {
			population.add(selection.selectionner());
		}
	}

	public Solution getBestSolution(ArrayList<Solution> population) { 

		Solution best = population.get(0);

		if (probleme.isMinimisation()) {

			for (int i = 1; i < population.size(); i++) {
				if (population.get(i).getF() < best.getF()) {
					best = population.get(i);
				}
			}
		} else {
			for (int i = 1; i < population.size(); i++) {
				if (population.get(i).getF() > best.getF()) {
					best = population.get(i);
				}
			}
		}




public class SelectionRouletteBiaisee extends Selection {

    private Random random;

    public SelectionRouletteBiaisee(ArrayList<Solution> population) {
        super(population);
        this.random = new Random();
    }

    @Override
    public Solution selectionner() {
        // Calculer la somme des scores (fitness)
        double sommeFitness = population.stream()
                .mapToDouble(Solution::getFitness) // Supposons que Solution possède une méthode getFitness()
                .sum();

        // Générer une valeur aléatoire entre 0 et la somme des fitness
        double seuil = random.nextDouble() * sommeFitness;

        // Parcourir la population pour trouver l'élément correspondant
        double cumul = 0.0;
        for (Solution solution : population) {
            cumul += solution.getFitness(); // Ajouter la fitness courante
            if (cumul >= seuil) {
                return solution;
            }
        }

        // Si aucun n'est sélectionné (ce qui ne devrait pas arriver), retourner le dernier
        return population.get(population.size() - 1);
    }
}


    @Override
    public Solution selectionner() {
        // Calculer la somme totale des aptitudes (f)
        double sommeAptitudes = 0.0;
        for (Solution s : population) {
            sommeAptitudes += s.getF();
        }

        // Générer une valeur aléatoire dans [0, sommeAptitudes]
        double seuil = random.nextDouble() * sommeAptitudes;

        // Parcourir la population et trouver l'individu correspondant
        double cumul = 0.0;
        for (Solution s : population) {
            cumul += s.getF();
            if (cumul >= seuil) {
                return s;
            }
        }

        // Par sécurité, retourner le dernier individu
        return population.get(population.size() - 1);
    }
}


		return best;

	}

}