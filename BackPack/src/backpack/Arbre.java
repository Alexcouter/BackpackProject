package backpack;

import java.util.ArrayList;

public class Arbre {
	
	private Backpack sac;
	private Arbre gauche;
	private Arbre droit;
	
	
	
	public Arbre(Backpack sac){
		this.sac = sac;
		this.gauche = null;
		this.droit = null;
		
	}
	
	public void arbre(PileObjet pile, String rendu){
		System.out.println(rendu + " taille pile : " + pile.estVide());
		//System.out.println(pile);
		//System.out.println("pallier : " + this.pallier);
		if(pile.estVide() > 0){
			Backpack sacDroit = new Backpack(this.sac.getPoidsMax(), sac.getObjets());
			//System.out.println("sac avant" + sac);
			sacDroit.addObjet(pile.depiler());
			//System.out.println("sac apres" + sac);
			//Arbre droit1 = );
			
			this.setDroit(new Arbre(new Backpack(this.sac.getPoidsMax(), sacDroit.getObjets())));
			this.droit.arbre(pile, rendu+ " Droit");
			
			//Backpack sacGauche = new Backpack(this.sac.getPoidsMax(), new ArrayList<Objet>());
			//Arbre gauche2 = new Arbre(sacGauche);
			this.setGauche(new Arbre(new Backpack(this.sac.getPoidsMax(), sac.getObjets())));
			this.gauche.arbre(pile, rendu + " Gauche");
		}
		else{
			this.setGauche(null);
			this.setDroit(null);
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
