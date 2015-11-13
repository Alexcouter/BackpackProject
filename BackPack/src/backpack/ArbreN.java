package backpack;


import java.util.LinkedList;
import java.util.ListIterator;

public class ArbreN {
	
	private Backpack sac;
	private LinkedList<ArbreN> arbres;
	private static int nbNoeuds = 0;
	//private boolean worth;
	private static float solution = 0;
	private static Backpack sacSolution;
	
	public int getNbNoeuds() {
		return nbNoeuds;
	}

	public ArbreN(Backpack monSac){
		ArbreN.nbNoeuds++;
		this.sac = monSac;
		this.arbres = new LinkedList<ArbreN>();
		//this.worth = true;
		
	}
	
	public void initSacSolution(){
		ArbreN.sacSolution= new Backpack(this.sac.getPoidsMax(),new LinkedList<Objet>() );
	}
	
	public void creationArbreN(PileObjet pile){
		
		if(pile.estVide() > 1){
			Backpack newSac = new Backpack(this.sac.getPoidsMax(), this.sac.getObjets());//crï¿½ation nouveau sac
			Objet o = pile.depiler();//on dï¿½pile un objet
			this.arbres.add(new ArbreN(newSac.clone()));//crï¿½ation nouveau fils vide
			while(newSac.getPoids() + o.getPoids() <= newSac.getPoidsMax()){//on crï¿½e un fils a chaque meme objet que l'on peu rajouter
				newSac.addObjet(o);
				this.arbres.add(new ArbreN(newSac.clone()));
			}
			for(ArbreN arb: arbres)//on rappelle la fonction sur les fils
				arb.creationArbreN(pile.clone());
		}
		else if(pile.estVide() == 1){ //si c'est le dernier objet de la pile, on en met autant que l'on peut sans crï¿½er de branche inutile
			Backpack newSac = new Backpack(this.sac.getPoidsMax(), this.sac.getObjets());
			Objet o = pile.depiler();
			while(newSac.getPoids() + o.getPoids() <= newSac.getPoidsMax()){
				newSac.addObjet(o);
			}
			this.arbres.add(new ArbreN(newSac.clone()));
			for(ArbreN arb: arbres)
				arb.creationArbreN(pile.clone());
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
	
	private static float calculEspérance(Objet o, ArbreN a){// valeurSacBranche + valeurObjetSuivant/PoidsObjetSuivant (poidsMaxduSac - poidsSacBranche)
		System.out.println(a.getSac().getValeur() + "+" + o.getCout()+"*("+a.getSac().getPoidsMax()+"-"+a.getSac().getPoids()+")");
		return a.getSac().getValeur() + (o.getCout())*(a.getSac().getPoidsMax()-a.getSac().getPoids());
	}
	
	public void solutionV2(PileObjet pile){
		
		
		if(arbres.size() == 1){
			pile.depiler();
			arbres.get(0).solutionV2(pile.clone());
		}
		else if(arbres.size()>1){
			pile.depiler();
			ListIterator<ArbreN> iterator = arbres.listIterator(arbres.size()); 
			while(iterator.hasPrevious()){ 
			   ArbreN item = iterator.previous();
			   float calcul = calculEspérance(pile.top(), item);
					  
			   if( calcul > solution+ 1){
				   item.solutionV2(pile.clone());
			   }
			} 
		}
		else{
//			System.out.println("test");
			if(solution < sac.getValeur()){
				solution = sac.getValeur();
				sacSolution = this.sac;
			}
				
		}
	}
	
	
	public void creationArbreN2(PileObjet pile){
		
		if(pile.estVide() > 1){
			Backpack newSac = new Backpack(this.sac.getPoidsMax(), this.sac.getObjets());//crï¿½ation nouveau sac
			Objet o = pile.depiler();//on dï¿½pile un objet
			if(newSac.getPoids() + o.getPoids() <= newSac.getPoidsMax()){
				this.arbres.add(new ArbreN(newSac));//crï¿½ation nouveau fils vide
				while(newSac.getPoids() + o.getPoids() <= newSac.getPoidsMax()){//on crï¿½e un fils a chaque meme objet que l'on peu rajouter
					newSac.addObjet(o);
					this.arbres.add(new ArbreN(newSac.clone()));
				}
				/*for(ArbreN arb: arbres)//on rappelle la fonction sur les fils
					arb.creationArbreN2(pile.clone());*/
				this.solutionV3(pile.clone(), o);
				//this.testCalcul(pile);
			}
			else{
				this.creationArbreN2(pile.clone());
			}
		}
		else if(pile.estVide() == 1){ //si c'est le dernier objet de la pile, on en met autant que l'on peut sans crï¿½er de branche inutile
			Backpack newSac = new Backpack(this.sac.getPoidsMax(), this.sac.getObjets());
			Objet o = pile.depiler();
			while(newSac.getPoids() + o.getPoids() <= newSac.getPoidsMax()){
				newSac.addObjet(o);
			}
			this.arbres.add(new ArbreN(newSac));
			/*for(ArbreN arb: arbres)
				arb.creationArbreN2(pile.clone());*/
			this.solutionV3(pile.clone(), o);
		}
		else if(pile.estVide() == 0){
			this.solutionV3(pile.clone(), null);
		}
	}
	
	

	public void solutionV3(PileObjet pile, Objet o){
	//	System.out.println("test : objet o : " + o.getCout() + " taille arbre " + arbres.size());
		//Objet current;
		System.out.println("test 123 : "+ sac.getValeur() + " taille pile " + pile.estVide() + " -best current solution : " + ArbreN.solution + " nb noeuds : " + ArbreN.nbNoeuds);
		if(this.arbres.size() == 1){
			//current = pile.depiler();
			this.arbres.get(0).creationArbreN2(pile);
			//arbres.get(0).solutionV3(pile.clone());
		}
		else if(arbres.size()>1){
			//current = pile.depiler();
			ListIterator<ArbreN> iterator = arbres.listIterator(arbres.size()); 
			/*while(iterator.hasPrevious()){ 
			   ArbreN item = iterator.previous();
			   float calcul = calculEspérance(pile.top(), item);
					  
			   if( calcul >= solution+ 1){
				   item.creationArbreN2(pile);
				   //item.solutionV3(pile.clone());
			   }
			}*/
			for(ArbreN arb : this.arbres){
				float calcul = calculEspérance(pile.top(), arb);
				  
				   if( calcul >= solution+ 1){
					   arb.creationArbreN2(pile);
					   //item.solutionV3(pile.clone());
				   }
			}
			
		}
		else{
			
			
			if(solution < sac.getValeur()){
				
				solution = sac.getValeur();
				sacSolution = this.sac;
			}
				
		}
	}
	
	private void testCalcul(PileObjet pile) {
		
		ListIterator<ArbreN> iterator = arbres.listIterator(arbres.size()); 
		while(iterator.hasPrevious()){ 
		   ArbreN item = iterator.previous();System.out.println("pile.top()" + pile.top().getPoids() + " " + pile.top().getCout()*pile.top().getPoids());
		   float calcul = calculEspérance(pile.top(), item);
			System.out.println(item.getSac().getPoids() + " calcul esperance : " + calcul);	  
		  /* if( calcul >= solution+ 1){
			   //item.creationArbreN2(pile);
			   //item.solutionV3(pile.clone());
		   }*/
		} 
	}
	
	
	/*
	 * test solution création arbre courcircuité total, + utilisation méthode solutionV2
	 */
	public void creationArbreN3(PileObjet pile){
		System.out.println("test 123 : "+ sac.getValeur() + " taille pile " + pile.estVide() + " -best current solution : " + ArbreN.solution + " nb noeuds : " + ArbreN.nbNoeuds);
		if(pile.estVide() > 1){
			Backpack newSac = new Backpack(this.sac.getPoidsMax(), this.sac.getObjets());//crï¿½ation nouveau sac
			Objet o = pile.depiler();//on dï¿½pile un objet
			if(newSac.getPoids() + o.getPoids() <= newSac.getPoidsMax()){
				this.arbres.add(new ArbreN(newSac.clone()));//crï¿½ation nouveau fils vide
				while(newSac.getPoids() + o.getPoids() <= newSac.getPoidsMax()){//on crï¿½e un fils a chaque meme objet que l'on peu rajouter
					newSac.addObjet(o);
					this.arbres.add(new ArbreN(newSac.clone()));
				}
				for(ArbreN arb: arbres)//on rappelle la fonction sur les fils
					arb.creationArbreN3(pile.clone());
			}
			else{
				this.creationArbreN3(pile.clone());
			}
		}
		else if(pile.estVide() == 1){ //si c'est le dernier objet de la pile, on en met autant que l'on peut sans crï¿½er de branche inutile
			Backpack newSac = new Backpack(this.sac.getPoidsMax(), this.sac.getObjets());
			Objet o = pile.depiler();
			while(newSac.getPoids() + o.getPoids() <= newSac.getPoidsMax()){
				newSac.addObjet(o);
			}
			this.arbres.add(new ArbreN(newSac.clone()));
			for(ArbreN arb: arbres)
				arb.creationArbreN3(pile.clone());
		}
	}
	
	public Backpack getSacSolution(){
		return ArbreN.sacSolution;
	}
	
	public LinkedList<ArbreN> getArbres(){
		return this.arbres;
	}
	
	public Backpack getSac(){
		return this.sac;
	}
	
	public float getSolution(){
		return ArbreN.solution;
	}
}
