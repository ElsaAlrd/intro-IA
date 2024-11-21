package tp1_vEtudiants.exercice1;

import java.sql.Time;
import java.util.StringTokenizer;

public class Epreuve implements Comparable<Object>{
	
	private String intitule; 
	private Time debut; 
	private Time fin;
	
	
	// Les horaires de debut et de fin doivent etre fournis au format hh:mm
	public Epreuve(String intitule, String debut, String fin) {
		this.intitule = intitule; 
		
		StringTokenizer st = new StringTokenizer(debut);
		String hh = st.nextToken(":");
		String mm = st.nextToken(":");
		this.debut = new Time(Integer.parseInt(hh), Integer.parseInt(mm), 0);
		
		st = new StringTokenizer(fin);
		hh = st.nextToken(":");
		mm = st.nextToken(":");
		this.fin = new Time(Integer.parseInt(hh), Integer.parseInt(mm), 0);
		
		// Pour corriger d'eventuelles inversions entre horaire de debut et de fin
		if(this.debut.after(this.fin)) {
			Time tmpTime = this.debut; 
			this.debut = this.fin; 
			this.fin = tmpTime;
		}
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public Time getDebut() {
		return debut;
	}

	public void setDebut(Time debut) {
		this.debut = debut;
	}

	public Time getFin() {
		return fin;
	}

	public void setFin(Time fin) {
		this.fin = fin;
	}
	
	public String toString() {
		return "Epreuve de " + intitule + " (" + debut +" --> " + fin + ")"; 
	}
	
	// Cette methode servira a trier les epreuves selon les horaires de fin croissants.  
	// Elle pourra etre adaptee pour realiser des tris suivant d'autres criteres.
	public int compareTo(Object o) {
    	Epreuve e = (Epreuve) o;
    	
    	if (this.fin.before(e.fin))
    		return -1; 
    	else 
    		return 0;
	}

}
