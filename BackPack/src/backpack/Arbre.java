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
			Objet o = pile.depiler();
			
			PileObjet pile2 = pile.clone();
			if(sacDroit.getPoids() + o.getPoids() <= sacDroit.getPoidsMax()){
				sacDroit.addObjet(o);
				this.setDroit(new Arbre(new Backpack(this.sac.getPoidsMax(), sacDroit.getObjets())));
				this.droit.creationArbre(pile2);
			}	
			this.setGauche(new Arbre(new Backpack(this.sac.getPoidsMax(), sac.getObjets())));
			this.gauche.creationArbre(pile);
		}
		else{
			this.setGauche(null);
			this.setDroit(null);
		}
	}
	
	private static Backpack max(Backpack f, Backpack g){
		if(f.getValeur()<g.getValeur())
			return g;
		return f;
	}
	
	public Backpack rechercheV1(){
		if(this.gauche != null && this.droit != null)
			return max(this.gauche.rechercheV1(), this.droit.rechercheV1());
		if(this.gauche != null)
			return this.gauche.rechercheV1();
		if(this.droit != null)
			return this.droit.rechercheV1();
		return this.sac;
	}
	
	public void parcours(){
		System.out.println(sac.getValeur());
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
