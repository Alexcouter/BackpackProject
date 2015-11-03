package backpack;

public class ArbreB {
	
	private Backpack sac;
	private ArbreB gauche;
	private ArbreB droit;
	
	
	
	public ArbreB(Backpack sac){
		this.sac = sac;
		this.gauche = null;
		this.droit = null;
		
	}
	
	public void creationArbre(PileObjet pile){
	
		
		if(pile.estVide() > 0){
			Backpack sacDroit = new Backpack(this.sac.getPoidsMax(), sac.getObjets());
			
			sacDroit.addObjet(pile.depiler());
			PileObjet pile2 = pile.clone();
			
			this.setDroit(new ArbreB(new Backpack(this.sac.getPoidsMax(), sacDroit.getObjets())));
			this.droit.creationArbre(pile);
			
			this.setGauche(new ArbreB(new Backpack(this.sac.getPoidsMax(), sac.getObjets())));
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
	
	public int nbNoeuds(ArbreB a){
		if(a == null){
			return( 0);
		}
		else return (1 + (nbNoeuds(a.gauche)+ (nbNoeuds(a.droit))));
	}
	
	public Backpack getSac(){
		return this.sac;
	}
	
	public ArbreB getGauche(){
		return this.gauche;
	}
	
	public ArbreB getDroit(){
		return this.droit;
	}

	public void setSac(Backpack sac) {
		this.sac = sac;
	}

	public void setGauche(ArbreB gauche) {
		this.gauche = gauche;
	}

	public void setDroit(ArbreB droit) {
		this.droit = droit;
	}

}
