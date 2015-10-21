package backpack;

public class Arbre {
	
	private Backpack sac;
	private Arbre gauche;
	private Arbre droit;
	
	
	
	public Arbre(Backpack sac){
		this.sac = sac;
		this.gauche = null;
		this.droit = null;
		
	}
	
	public void creationArbre(PileObjet pile){
	
		
		if(pile.estVide() > 0){
			Backpack sacDroit = new Backpack(this.sac.getPoidsMax(), sac.getObjets());
			
			sacDroit.addObjet(pile.depiler());
			PileObjet pile2 = pile.clone();
			
			this.setDroit(new Arbre(new Backpack(this.sac.getPoidsMax(), sacDroit.getObjets())));
			this.droit.creationArbre(pile);
			
			this.setGauche(new Arbre(new Backpack(this.sac.getPoidsMax(), sac.getObjets())));
			this.gauche.creationArbre(pile2);
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
