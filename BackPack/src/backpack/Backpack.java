package backpack;

import java.util.ArrayList;
import java.util.Collection;

public class Backpack {
	float poidsMax;
	Collection<Objet> objets;

	public Backpack(float poidsMax, Collection<Objet> objets) {
		super();
		this.poidsMax = poidsMax;
		this.objets = objets;
	}

	float getDiff(){
		return poidsMax - getPoids();
	}

	boolean tropPlein(){
		return (poidsMax >= getPoids());
	}

	@Override
	public String toString() {
		String display = "";
		display += "Liste des objets : \n ";
		if(this.objets.size() > 0){	
			for(Objet obj : this.objets)
				display += obj.nom + " : " + obj.getCout() + " (" + obj.valeur + "/" + obj.poids + ") \n";
		}
		else
			display += "Aucun objet dans le sac";
		return display;
	}

	float getValeur(){
		float valeur_totale = 0;
		for(Objet obj: objets)
			valeur_totale += (int)obj.valeur;
		return valeur_totale;
	}

	float getPoids(){
		float poids_total = 0;
		for(Objet obj: objets)
			poids_total += obj.poids;
		return poids_total;
	}

	float getCout(){
		float cout_total = 0;
		for(Objet obj: objets)
			cout_total += obj.getCout();
		return cout_total;
	}
	
	public float getPoidsMax(){
		return this.poidsMax;
	}
	
	public ArrayList<Objet> getObjets(){
		ArrayList<Objet> res = new ArrayList<Objet>();
		for(Objet obj: objets)
			res.add(obj);
		return res;
	}
	
	public void addObjet(Objet o){
		objets.add(o);
	}
}
