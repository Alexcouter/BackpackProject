package backpack;

public class Objet {

	String nom;
	float poids,valeur;
	
	public Objet(String nom, float poids, float valeur){
		this.nom = nom;
		this.poids = poids;
		this.valeur = valeur;
	}

	public float getCout(){
		if(poids == 0)
			return valeur;
		else
			return (valeur/poids);
	}
	
	
	public Objet(float poids, float valeur) {
		super();
		this.poids = poids;
		this.valeur = valeur;
	}

	public int compareTo(Objet o){
		if(o.getCout() == this.getCout() )
			return 0;
		else if(o.getCout() < this.getCout())
			return 1;
		else
			return -1;
	}
}
