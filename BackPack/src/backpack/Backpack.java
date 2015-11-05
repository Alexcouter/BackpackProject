package backpack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

public class Backpack implements Cloneable{
	float poidsMax;
	ArrayList<Objet> objets;

	public Backpack(float poidsMax, ArrayList<Objet> objets) {
		super();
		this.poidsMax = poidsMax;
		this.objets = objets;
	}

	public float getDiff(){
		return poidsMax - getPoids();
	}

	public boolean tropPlein(){
		return (poidsMax >= getPoids());
	}
	
	public Backpack clone(){
		@SuppressWarnings("unchecked")
		Backpack monClone = new Backpack(this.poidsMax, (ArrayList<Objet>) this.objets.clone());
		return monClone;
	}
	@Override
	public String toString() {
		String display = "";
		display += "Liste des objets : value  "+this.getValeur()+"\n ";
		if(this.objets.size() > 0){	
			for(Objet obj : this.objets)
				display += obj.nom + " : " + obj.getCout() + " (" + obj.valeur + "/" + obj.poids + ") \n";
		}
		else
			display += "Aucun objet dans le sac";
		return display;
	}

	public float getValeur(){
		float valeur_totale = 0;
		for(Objet obj: objets)
			valeur_totale += (int)obj.valeur;
		return valeur_totale;
	}

	public float getPoids(){
		float poids_total = 0;
		for(Objet obj: objets)
			poids_total += obj.poids;
		return poids_total;
	}

	public float getCout(){
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

	
	public int compareTo(Object o) {
		Backpack comp = (Backpack) o;
		if(this.getValeur() < comp.getValeur())
			return 1;
		else if(this.getValeur() > comp.getValeur())
			return -1;
		else
			return 0;
	}
}
