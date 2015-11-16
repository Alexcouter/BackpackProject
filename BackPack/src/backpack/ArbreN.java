package backpack;


import java.text.DecimalFormat;
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
	
	private static float calculEsperance(Objet o, ArbreN a){// valeurSacBranche + valeurObjetSuivant/PoidsObjetSuivant (poidsMaxduSac - poidsSacBranche)
		DecimalFormat df = new DecimalFormat("########.00");
		
		
		float d;
		float cout;
		cout = Float.parseFloat(df.format(o.getCout()).replace(',', '.'));
		d = a.getSac().getValeur() + (cout)*(a.getSac().getPoidsMax()-a.getSac().getPoids());
		System.out.println(a.getSac().getValeur() + "+" + o.getCout()+"*("+a.getSac().getPoidsMax()+"-"+a.getSac().getPoids()+")" + " = " + (a.getSac().getValeur() + (o.getCout())*(a.getSac().getPoidsMax()-a.getSac().getPoids())) );
		return d;
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
			   float calcul = calculEsperance(pile.top(), item);
					  
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
	
	/*
	 * param : pile 	pile d'objets que l'on peut mettre dans le sac
	 */
	public void creationArbreN2(PileObjet pile){
		
		if(pile.estVide() > 1){
			Backpack newSac = new Backpack(this.sac.getPoidsMax(), this.sac.getObjets());//création nouveau sac
			Objet o = pile.depiler();//on dépile un objet
			if(newSac.getPoids() + o.getPoids() <= newSac.getPoidsMax()+2){//s'il est possible de rajouter au moins une fois l'objet courant dans le sac
				this.arbres.add(new ArbreN(newSac));//création nouveau fils sans rajout au sac
				while(newSac.getPoids() + o.getPoids() <= newSac.getPoidsMax()){//on crée un fils a chaque meme objet que l'on peu rajouter
					newSac.addObjet(o);
					this.arbres.add(new ArbreN(newSac.clone()));
				}
				this.solutionV3(pile.clone());//on évalue tous les fils de l'arbre courant
			}
			else{//si on ne peut pas rajouter d'objet dans le sac, on ne crée pas de noeud inutile et on passe directement a l'objet suivant
				this.creationArbreN2(pile.clone());
			}
		}
		else if(pile.estVide() == 1){ //si c'est le dernier objet de la pile, on met autant d'objet que l'on peut sans créer de branche inutile
			Backpack newSac = new Backpack(this.sac.getPoidsMax(), this.sac.getObjets());
			Objet o = pile.depiler();
			while(newSac.getPoids() + o.getPoids() <= newSac.getPoidsMax()+2){
				newSac.addObjet(o);
			}
			this.arbres.add(new ArbreN(newSac));
			this.solutionV3(pile.clone());
		}
		else if(pile.estVide() == 0){//si la pile est vide on appelle la fonction d'évaluation qui rajoute ou non la solution si c'ets la meilleur
			this.solutionV3(pile.clone());
		}
	}
	
	
	/*
	 * param : pile 	pile d'objets que l'on peut mettre dans le sac
	 */
	public void solutionV3(PileObjet pile){
	
		
		if(this.arbres.size() == 1){//s'il n'y a qu'un seul fils, il n'y a rien a évaluer donc on passe a la suite
			this.arbres.get(0).creationArbreN2(pile);
		}
		else if(arbres.size()>1){//si le noeud courant a plusieurs fils
			ListIterator<ArbreN> iterator = arbres.listIterator(arbres.size()); //on parcours les fils en partant du dernier
			while(iterator.hasPrevious()){ 
			   ArbreN item = iterator.previous();
			   float calcul = calculEsperance(pile.top(), item);//on calcul l'espérance du sous ensemble
			   System.out.println("valeur du sac : "+ item.getSac().getValeur() + " taille pile " + pile.estVide() + " -best current solution : " + ArbreN.solution + " nb noeuds : " + ArbreN.nbNoeuds + "Espérance " + calcul);
			   if( calcul >= solution+ 1){//si ça vaut le coup on continue l'exploration du sous ensemble
				   item.creationArbreN2(pile.clone());
			   }
			}
		}
		else{//si on arrive a une feuille
			if(solution < sac.getValeur()){//on change la valeur de la solution si elle est supérieure a celle actuelle
				solution = sac.getValeur();
				System.out.println("valeur du sac : "+ sac.getValeur() + " taille pile " + pile.estVide() + " -best current solution : " + ArbreN.solution + " nb noeuds : " + ArbreN.nbNoeuds);
				 
				sacSolution = this.sac;
			}		
		}
	}
	
	private void testCalcul(PileObjet pile) {
		
		ListIterator<ArbreN> iterator = arbres.listIterator(arbres.size()); 
		while(iterator.hasPrevious()){ 
		   ArbreN item = iterator.previous();System.out.println("pile.top()" + pile.top().getPoids() + " " + pile.top().getCout()*pile.top().getPoids());
		   float calcul = calculEsperance(pile.top(), item);
			System.out.println(item.getSac().getPoids() + " calcul esperance : " + calcul);	  
		  /* if( calcul >= solution+ 1){
			   //item.creationArbreN2(pile);
			   //item.solutionV3(pile.clone());
		   }*/
		} 
	}
	
	
	/*
	 * test solution crï¿½ation arbre courcircuitï¿½ total, + utilisation mï¿½thode solutionV2
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
