package app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import backpack.ArbreN;
import backpack.Backpack;
import backpack.Objet;
import backpack.PileObjet;
import parser.TextParser;

public class Main {

	public static void main(String[] args) throws IOException {
		
		//argument : /-0 type de test /-1 taille du sac /-2 si test=0 adresse du fichier, si test =1 nb objet créer
		
		int test = Integer.parseInt(args[0]);
		int taille;
		long startTime = System.currentTimeMillis(); 
		
		Backpack sac1;
		PileObjet pile;
		
		if(test == -1){
			Objet objet1 = new Objet("cuillere", 33, 4);
			Objet objet2 = new Objet("couteau", 49, 5);
			Objet objet3 = new Objet("fourchete", 60, 6);
			Objet objet4 = new Objet("assiette", 32, 2);
			
			LinkedList<Objet> collec1 = new LinkedList<Objet>();
			collec1.add(objet1);
			collec1.add(objet2);
			collec1.add(objet3);
			collec1.add(objet4);
			
			pile = new PileObjet(collec1);
			
			sac1 = new Backpack(130, new LinkedList<Objet>());
		}
		else if(test == 0){
			taille = Integer.parseInt(args[1]);
			TextParser parser = new TextParser(new File(args[2]));
			
			pile = new PileObjet(parser.parse());
			
			sac1 = new Backpack(taille, new LinkedList<Objet>());
		}
		else{
			int i = 0;
			taille = Integer.parseInt(args[1]);
			LinkedList<Objet> dataRandom = new LinkedList<Objet>();
			for(i = 0; i < Integer.parseInt(args[1]);i++){
				dataRandom.add(new Objet(i, (float) (17*i + (Math.random() * (18*i - 17*i)))));
			}
			pile = new PileObjet(dataRandom);
			//PileObjet pile2 = pile.clone();
			sac1 = new Backpack(taille, new LinkedList<Objet>());
		}
		
		long startTimeArbre1 = System.currentTimeMillis(); 
		ArbreN aN = new ArbreN(sac1);
		aN.creationArbreN2(pile.clone());
		System.out.println("Arbre Créé.");
		
		//System.out.println("Solution V3: " + aN.getSolution());
		System.out.println("Solution : " + aN.getSacSolution() + "\tpoids du sac : " + aN.getSacSolution().getPoids() + "\tvaleur du sac : " + aN.getSacSolution().getValeur());
		
		long estimatedTime = System.currentTimeMillis() - startTimeArbre1;
		System.out.println("time : " + estimatedTime + "ms");
		
		
		
		estimatedTime = System.currentTimeMillis() - startTime;
		
		System.out.println("Runtime : "+ estimatedTime + "ms");
		
		
		
	}
	
	

}
