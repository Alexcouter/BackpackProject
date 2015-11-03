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
            Objet o = pile.depiler();
            
            PileObjet pile2 = pile.clone();
            if(sacDroit.getPoids() + o.getPoids() <= sacDroit.getPoidsMax()){
                sacDroit.addObjet(o);
                this.setDroit(new ArbreB(new Backpack(this.sac.getPoidsMax(), sacDroit.getObjets())));
                this.droit.creationArbre(pile2);
            }    
            this.setGauche(new ArbreB(new Backpack(this.sac.getPoidsMax(), sac.getObjets())));
            this.gauche.creationArbre(pile);
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
	
	public Backpack rechercheV1(){
        if(this.gauche != null && this.droit != null)
            return max(this.gauche.rechercheV1(), this.droit.rechercheV1());
        if(this.gauche != null)
            return this.gauche.rechercheV1();
        if(this.droit != null)
            return this.droit.rechercheV1();
        return this.sac;
    }
	
	private static Backpack max(Backpack f, Backpack g){
        if(f.getValeur()<g.getValeur())
            return g;
        return f;
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
