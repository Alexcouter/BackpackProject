package backpack;

import java.util.Collections;
import java.util.Comparator;
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

	public Backpack selectionSac(LinkedList<Backpack> sacs, Backpack sacPere){

		float totalCout = 0, totalTemp = 0, randomCout;

		for(Backpack sacTemp: sacs){
			totalCout += sacTemp.getCout();
		}

		randomCout = (float) (Math.random() * sacs.size());
		for(Backpack sacTemp: sacs){
			totalTemp += sacTemp.getCout();
			if(totalCout> randomCout)
				if(!sacTemp.equals(sacPere))
					return sacTemp;
		}
		
		return null;
	}
	
	
	public Backpack reproduction(LinkedList<Objet> objets, Backpack pere, Backpack mere){
		int pointAlea, nbItr;
		Backpack enfant = new Backpack(pere.getPoidsMax());
		
		if(pere.objets.size()<= mere.objets.size())
			nbItr = pere.objets.size();
		else
			nbItr = mere.objets.size();
		
		pointAlea = (int) ((Math.random() * 10) * nbItr);
		
		for(int i = 1; i<=nbItr; i++){
			if(i < pointAlea)
				enfant.objets.add(pere.objets.get(i));
			else
				enfant.objets.add(mere.objets.get(i));	
		}
		
		LinkedList<Objet> objetsAAjouter = new LinkedList<Objet>();
		for(Objet obj: objets){
			if(!enfant.objets.contains(obj))
				objetsAAjouter.add(obj);
		}
		
		if(objetsAAjouter.size() >0)
			enfant.restructurer(objetsAAjouter);
		
		return enfant;
	}
	
	void majSacs(LinkedList<Backpack>sacs, Backpack enfant){
		sacs.add(enfant);
		
		//A faire
//		Collections.sort(list, new Comparator<String>() {
//	         @Override
//	         public int compare(String o1, String o2) {
//	             return Collator.getInstance().compare(o1, o2);
//	         }
//	     });

	}
	
	Backpack getBestBackpack(LinkedList<Backpack> sacs){
		Backpack bestBp = null;
		float coutTemp = 0;
		for(Backpack sac: sacs){
			if(sac.getCout() > coutTemp){
				coutTemp = sac.getCout();
				bestBp = sac;
			}
		}
		
		return bestBp;
	}
	
}
