package backpack;

public class Objet {

	String nom;
	float poids,valeur;
	
	public Objet(String nom, float poids, float valeur){
		this.nom = nom;
		this.poids = poids;
		this.valeur = valeur;
	}

	float getCout(){
		if(poids == 0)
			return valeur;
		else
			return (valeur/poids);
	}
}
