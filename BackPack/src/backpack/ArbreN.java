package backpack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class ArbreN {
	
	private Backpack sac;
	private LinkedList<ArbreN> arbres;
	private static int nbNoeuds = 0;
	private boolean worth;
	private static float solution = 0;
	private static Backpack sacSolution;
	
	public int getNbNoeuds() {
		return nbNoeuds;
	}

	public ArbreN(Backpack monSac){
		this.nbNoeuds++;
		this.sac = monSac;
		this.arbres = new LinkedList<ArbreN>();
		this.worth = true;
	}
	
	public void creationArbreN(PileObjet pile){
		
		if(pile.estVide() > 1){
			Backpack newSac = new Backpack(this.sac.getPoidsMax(), this.sac.getObjets());//cr�ation nouveau sac
			Objet o = pile.depiler();//on d�pile un objet
			this.arbres.add(new ArbreN(newSac.clone()));//cr�ation nouveau fils vide
			while(newSac.getPoids() + o.getPoids() <= newSac.getPoidsMax()){//on cr�e un fils a chaque meme objet que l'on peu rajouter
				newSac.addObjet(o);
				this.arbres.add(new ArbreN(newSac.clone()));
			}
			for(ArbreN arb: arbres)//on rappelle la fonction sur les fils
				arb.creationArbreN(pile.clone());
		}
		else if(pile.estVide() == 1){ //si c'est le dernier objet de la pile, on en met autant que l'on peut sans cr�er de branche inutile
			Backpack newSac = new Backpack(this.sac.getPoidsMax(), this.sac.getObjets());
			Objet o = pile.depiler();
			while(newSac.getPoids() + o.getPoids() <= newSac.getPoidsMax()){
				newSac.addObjet(o);
			}
			this.arbres.add(new ArbreN(newSac.clone()));
			for(ArbreN arb: arbres)
				arb.creationArbreN(pile.clone());
			this.sacSolution= new Backpack(this.sac.getPoidsMax(),new LinkedList<Objet>() );
		}
	}
	
	public Backpack solutionV1(){
		
		Backpack res = new Backpack(this.sac.getPoidsMax(), new LinkedList<Objet>());
		Backpack res2;
		if(arbres.size()>0){
			res = this.arbres.get(0).getSac();
			for(ArbreN arb: arbres){
				res2 = arb.solutionV1();
				if(res.compareTo(res2) == 1){
					res = res2;
				}
			}
		}
		return res;
	}
	
	public void solutionV2(PileObjet pile){
		
		Objet current;
		if(arbres.size() == 1){
			current = pile.depiler();
			arbres.get(0).solutionV2(pile.clone());
		}
		else if(arbres.size()>1){
			current = pile.depiler();
			ListIterator<ArbreN> iterator = arbres.listIterator(arbres.size()); 
			while(iterator.hasPrevious()){ 
			   ArbreN item = iterator.previous();
			   float calcul = item.getSac().getValeur() + (pile.top().getCout())*(item.getSac().getPoidsMax()-item.getSac().getPoids());
			   if( calcul > solution+ 1){
				   //solution = (int) calcul;
				   item.solutionV2(pile.clone());
			   }
			} 
		}
		else{
			System.out.println("test");
			if(solution < sac.getValeur()){
				solution = sac.getValeur();
				sacSolution = this.sac;
			}
				
		}
	}
	
	public Backpack getSacSolution(){
		return this.sacSolution;
	}
	
	public LinkedList<ArbreN> getArbres(){
		return this.arbres;
	}
	
	public Backpack getSac(){
		return this.sac;
	}
	
	public float getSolution(){
		return this.solution;
	}
}
