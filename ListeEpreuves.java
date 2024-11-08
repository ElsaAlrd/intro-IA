package tp1_vEtudiants.exercice1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class ListeEpreuves {

	private String nomFichier; 
	private ArrayList<Epreuve> liste; 

	public ListeEpreuves(String nomFichier) {
		this.nomFichier = nomFichier; 
		liste = new ArrayList<Epreuve>(); 

		lireFichierEpreuves(nomFichier);

	}

	// Methode permettant de lire fichier contenant la liste des epreuves. 
	// La lecture se fait ligne par ligne. 
	// Toute ligne commencant pas le caractere # est consideree comme un commentaire. Elle est donc ignoree. 
	public void lireFichierEpreuves(String fichier) {
		try {
			FileReader fileReader = new FileReader(fichier);

			BufferedReader reader = new BufferedReader(fileReader);

			String ligne = reader.readLine();

			while (ligne != null) {

				if (!ligne.startsWith("#")) {
					StringTokenizer st = new StringTokenizer(ligne);
					String nom = st.nextToken();
					String debut = st.nextToken();
					String fin = st.nextToken();

					Epreuve epreuve = new Epreuve(nom, debut, fin);
					liste.add(epreuve);
				}

				ligne = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void eliminerConflits(Epreuve e) {

    liste.removeIf(epreuve -> epreuve.getDebut().before(e.getFin()) && epreuve.getFin().after(e.getDebut()));
			}


	// Pour d'autres types de tri, vous pouvez modifier la methode compareTo(Object o) dans la classe Epreuve
	public void triParHeureFin() {
		Collections.sort((List<Epreuve>) liste);

	}

	public String getNomFichier() {
		return nomFichier;
	}

	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	public ArrayList<Epreuve> getListe() {
		return liste;
	}

	public void setListe(ArrayList<Epreuve> liste) {
		this.liste = liste;
	}

	// Pour faciliter l'acces a un element de la liste
	// On evitera de recuperer toute la liste puis d'acceder a l'element d'indice i
	public Epreuve get(int i) {
		return liste.get(i);
	}

	public void set(int i, Epreuve e) {
		liste.add(i, e);
	}

	public void replace(int i, Epreuve e) {
		liste.remove(i);
		liste.add(i, e);
	}

	public void remove(int i) {
		liste.remove(i);
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < liste.size(); i++) {
			s += liste.get(i).toString() + "\n";
		}
		return s; 
	}
}

