package backpack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class PileObjet implements Cloneable{

private Stack<Objet> pile;
	
	public PileObjet(LinkedList<Objet> list){
		
		LinkedList<Objet> listFinal = new LinkedList<Objet>();
		if(list.size()>0){
			Objet objFinal;
			int putted = 0, deleted = 0;
			for(Objet obj : list){
				if(listFinal.size()>0){
					Iterator<Objet> it = listFinal.iterator();
					while(it.hasNext()){
						objFinal = it.next();
					
						if(obj.getPoids() == objFinal.getPoids()){
							putted = 1;
							//System.out.println(obj.getValeur() + " " + objFinal.getValeur());
							if(obj.getValeur() > objFinal.getValeur()){
								listFinal.set(listFinal.indexOf(objFinal), obj);
							}
						}
					}
				}
				if(putted == 0)
					listFinal.add(obj);
				putted = 0;
			}
		}
		
		Collections.sort(listFinal,new Comparator<Objet>() {
	        @Override
	        public int compare(Objet  o1, Objet o2)
	        {

	            return  o1.compareTo(o2);
	        }
	    });
		
		System.out.println();
		if(listFinal.size()>0){
			pile = new Stack<Objet>();
			for(Objet obj2 : listFinal){
				System.out.println(obj2.getValeur() + " " + obj2.getPoids());
				pile.push(obj2);
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
