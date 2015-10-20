package backpack;

import java.util.ArrayList;

public class Arbre {
	
	private Backpack sac;
	private Arbre gauche;
	private Arbre droit;
	private int pallier;
	
	
	public Arbre(Backpack sac){
		this.sac = sac;
		this.gauche = null;
		this.droit = null;
	}
	
	public Arbre(Backpack monSac, PileObjet pile, int pallier){
		this.pallier = pallier +1;
		this.sac = monSac;
		//System.out.println(pile);
		System.out.println("pallier : " + this.pallier);
		if(pile.estVide() > 0 && this.pallier != 0){
			Backpack sacDroit = new Backpack(monSac.getPoidsMax(), monSac.getObjets());
			sacDroit.addObjet(pile.depiler());
			
			this.droit = new Arbre(sacDroit, pile, this.pallier);
			Backpack sacGauche = new Backpack(monSac.getPoidsMax(), new ArrayList<Objet>());
			this.gauche = new Arbre(sacGauche, pile, this.pallier);
		}
		else if(this.pallier == 0 && pile.estVide() > 0){
			Backpack sacDroit = new Backpack(monSac.getPoidsMax(), new ArrayList<Objet>());
			this.droit = new Arbre(sacDroit, pile, this.pallier);
			Backpack sacGauche = new Backpack(monSac.getPoidsMax(), new ArrayList<Objet>());
			this.gauche = new Arbre(sacGauche, pile, this.pallier);
		}
		else{
			this.gauche = null;
			this.droit = null;
		}
	}
	
	public void parcours(){
		System.out.println(sac);
		if(gauche != null)
			gauche.parcours();
		if(droit != null)
			droit.parcours();
	}
	
	public int nbNoeuds(Arbre a){
		if(a == null){
			return( 0);
		}
		else return (1 + (nbNoeuds(a.gauche)+ (nbNoeuds(a.droit))));
	}
	
	public Backpack getSac(){
		return this.sac;
	}
	
	public Arbre getGauche(){
		return this.gauche;
	}
	
	public Arbre getDroit(){
		return this.droit;
	}

	public void setSac(Backpack sac) {
		this.sac = sac;
	}

	public void setGauche(Arbre gauche) {
		this.gauche = gauche;
	}

	public void setDroit(Arbre droit) {
		this.droit = droit;
	}

}
