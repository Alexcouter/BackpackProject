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
	
	
	public void parcours(){
		System.out.println(sac);
		if(gauche != null)
			gauche.parcours();
		if(droit != null)
			droit.parcours();
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
