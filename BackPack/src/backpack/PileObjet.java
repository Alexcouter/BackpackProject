package backpack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

public class PileObjet {

private Stack<Objet> pile;
	
	public PileObjet(ArrayList<Objet> list){
		Collections.sort(list,new Comparator<Objet>() {
	        @Override
	        public int compare(Objet  o1, Objet o2)
	        {

	            return  o1.compareTo(o2);
	        }
	    });
		for(Objet obj : list){
			pile.push(obj);
		}
	}
	
	public Objet depiler(){
		return pile.pop();
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

}
