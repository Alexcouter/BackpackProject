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
			
		}
		
		
		return nouveauSac;
	}
	
}
