package tp1_vEtudiants.exercice1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tp1_vEtudiants.exercice1.Epreuve;
import tp1_vEtudiants.exercice1.ListeEpreuves;

public class Main {

	public static void main(String[] args) {
		
		String fichierEpreuves = "C:\\Users\\elsa-\\OneDrive\\Documents\\esirem\\4A\\S1\\intro IA\\tp1\\ex1\\Epreuves.txt";
		
		ListeEpreuves listeEpreuves = new ListeEpreuves(fichierEpreuves);

		System.out.println("#################################################");
		System.out.println("# Toutes les epreuves inscrites dans le fichier #");
		System.out.println("#################################################");
		
		System.out.println(listeEpreuves);
		System.out.println();
		
		listeEpreuves.triParHeureFin();
		
		System.out.println("#################################################");
		System.out.println("# Epreuves triees par horaires de fin croissant #");
		System.out.println("#################################################");
		
		System.out.println(listeEpreuves);
		System.out.println();
		
		Epreuve e = listeEpreuves.get(4); //Choix de l'epreuve a eliminer les conflits selon l'odre dans la liste triée par ordre de fin croissant
		listeEpreuves.eliminerConflits(e); //Elimination des conflits de l'epreuve e
		
		//Affichage des epreuves apres suppression des conflits de l'epreuve e
		System.out.println("#################################################");
		System.out.println("#  Epreuves apres suppression des conflits de   #");
		System.out.println("#              l'epreuve de " +  e.getIntitule() + "             #");
		System.out.println("#################################################");
		
		//Affichage de toutes les epreuves
		System.out.println(listeEpreuves);
		System.out.println();
		
				
		ArrayList<Epreuve> planning = new ArrayList<Epreuve>();
		for (int j = 0; j < listeEpreuves.getListe().size(); j++){
		planning.add(listeEpreuves.get(j));
		listeEpreuves.eliminerConflits(listeEpreuves.get(j));}
		
		
		Collections.sort((List<Epreuve>) planning);
		
		System.out.println("#################################################");
		System.out.println("#             Planning des epreuves             #");
		System.out.println("#################################################");
		
		for (int i = 0; i < planning.size(); i++) {
			System.out.println(planning.get(i));
		}
	}


}
