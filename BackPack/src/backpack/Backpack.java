package backpack;

import java.util.ArrayList;
import java.util.LinkedList;

public class Backpack implements Cloneable{
	float poidsMax;
	LinkedList<Objet> objets;
	
	public Backpack(float poidsMax, LinkedList<Objet> objets) {
		super();
		this.objets = objets;
	}
	
	public Backpack(float poidsMax) {
		super();
		this.poidsMax = poidsMax;
		this.objets = new LinkedList<Objet>();
	}

	public float getDiff(){
		return poidsMax - getPoids();
	}

	public boolean tropPlein(){
		return (poidsMax >= getPoids());
	}
	
	public Backpack clone(){
		@SuppressWarnings("unchecked")
		Backpack monClone = new Backpack(this.poidsMax, (LinkedList<Objet>) this.objets.clone());
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
	
	public LinkedList<Objet> getObjets(){
		LinkedList<Objet> res = new LinkedList<Objet>();
		for(Objet obj: objets)
			res.add(obj);
		return res;
	}
	
	public void addObjet(Objet o){
		objets.add(o);
	}

	void restructurer(LinkedList<Objet> objetsAAjouter){
		LinkedList<Objet> objsTemp = new LinkedList<Objet>();
		LinkedList<Objet> objsARetirer = new LinkedList<Objet>();
		int indexObj;
		
		for(Objet obj: this.objets){
			if(objsTemp.contains(obj))
				objsARetirer.add(obj);
			else
				objsTemp.add(obj);
		}
		
		for(Objet obj: objsARetirer){
			indexObj = this.objets.indexOf(obj);
			this.objets.remove(indexObj);
			
			do{
				this.objets.add(indexObj, objetsAAjouter.getFirst());
				objetsAAjouter.removeFirst();
			}
			while(!this.tropPlein());
			
		}
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
