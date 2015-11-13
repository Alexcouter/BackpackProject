package backpack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Stack;

public class PileObjet implements Cloneable{

private Stack<Objet> pile;
	
	public PileObjet(LinkedList<Objet> list){
		Collections.sort(list,new Comparator<Objet>() {
	        @Override
	        public int compare(Objet  o1, Objet o2)
	        {

	            return  o1.compareTo(o2);
	        }
	    });
		if(list.size()>0){
			pile = new Stack<Objet>();
			for(Objet obj : list){
				pile.push(obj);
			}
		}
		
	}
	
	
	public void depileAll(){
		this.pile.removeAllElements();
	}
	
	
	public PileObjet(Stack<Objet> mapile){
		this.pile = new Stack<Objet>();
		this.pile = mapile;
		
	}
	
	public PileObjet clone(){
		PileObjet monClone = new PileObjet((Stack<Objet>) this.pile.clone());
		return monClone;
	}
	
	public Objet depiler(){
		return pile.pop();
	}
	
	public int estVide(){
		return pile.size();
	}
	
	public String toString() {
		String display = "";
		display += "Liste des objets : \n ";
		if(this.pile.size() > 0){	
			for(Objet obj : this.pile)
				display += obj.nom + " : " + obj.getCout() + " (" + obj.valeur + "/" + obj.poids + ") \n";
		}
		else
			display += "Aucun objet dans le sac";
		return display;
	}
	
	public Objet top(){
		return pile.peek();
	}

}
