package backpack;

public class Objet {

	String nom;
	float poids,valeur;

	float getCout(){
		if(poids == 0)
			return valeur;
		else
			return (valeur/poids);
	}
}
