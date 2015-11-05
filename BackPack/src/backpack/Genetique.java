package backpack;

import java.util.LinkedList;

public class Genetique {

	public Backpack nouveauSac(LinkedList<Objet> objets, float volMax){

		Backpack nouveauSac = new Backpack(volMax);
		LinkedList<Objet> tempObjects = (LinkedList<Objet>) objets.clone();

		int aleatoire, i = 0, maxIter = 100;
		float ecartMax = 2;
		Objet tempObject;

		while (tempObjects.size() > 0 && nouveauSac.getDiff() > ecartMax && i < maxIter){
			aleatoire = (int) (Math.random() * tempObjects.size());
			tempObject = tempObjects.get(aleatoire);
			tempObjects.remove(aleatoire);

			if(!nouveauSac.tropPlein())
				nouveauSac.objets.removeLast();
			i++;
		}		

		return nouveauSac;
	}

	public LinkedList<Backpack> creerEnsembleDeSacs(LinkedList<Objet> objets, float volMax, int nbEnsembleSouhaites){

		Backpack nouveauSac;
		LinkedList<Backpack> xSacs = new LinkedList<Backpack>();
		for(int i = 1; i <= nbEnsembleSouhaites; i++){
			nouveauSac = nouveauSac(objets, volMax);
			xSacs.add(nouveauSac);
		}

		return xSacs;
	}

}
