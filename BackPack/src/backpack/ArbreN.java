package backpack;

import java.util.ArrayList;

public class ArbreN {
	
	private Backpack sac;
	private ArrayList<ArbreN> arbres;
	private static int nbNoeuds = 0;
	
	public int getNbNoeuds() {
		return nbNoeuds;
	}

	public ArbreN(Backpack monSac){
		this.nbNoeuds++;
		this.sac = monSac;
		this.arbres = new ArrayList<ArbreN>();
	}
	
	public void creationArbreN(PileObjet pile){
		
		if(pile.estVide() > 1){
			Backpack newSac = new Backpack(this.sac.getPoidsMax(), this.sac.getObjets());
			Objet o = pile.depiler();
			this.arbres.add(new ArbreN(newSac.clone()));
			while(newSac.getPoids() + o.getPoids() <= newSac.getPoidsMax()){
				newSac.addObjet(o);
				this.arbres.add(new ArbreN(newSac.clone()));
			}
			for(ArbreN arb: arbres)
				arb.creationArbreN(pile.clone());
		}
		else if(pile.estVide() == 1){ //si c'est le dernier objet de la pile, on en met autant que l'on peut sans créer de branche inutile
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
		// a faire
		Backpack res = new Backpack(this.sac.getPoidsMax(),new ArrayList<Objet>());
		Backpack res2 = new Backpack(this.sac.getPoidsMax(),new ArrayList<Objet>());
		if(arbres.size()>0)
			for(ArbreN arb: arbres){
				res2 = arb.solutionV1();
				if(res.compareTo(res2) == 1){
					res = res2;
				}
			}
		return res;
	}
	
	public ArrayList<ArbreN> getArbres(){
		return this.arbres;
	}
	
	public Backpack getSac(){
		return this.sac;
	}
}
