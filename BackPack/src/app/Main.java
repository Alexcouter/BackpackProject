package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import parser.TextParser;
import backpack.Arbre;
import backpack.Backpack;
import backpack.Objet;
import backpack.PileObjet;

public class Main {

	public static void main(String[] args) throws IOException {
		
		long startTime = System.currentTimeMillis(); 
//		Objet objet1 = new Objet("cuillere", 1, 10);
//		Objet objet2 = new Objet("couteau", 2, 5);
//		Objet objet3 = new Objet("fourchete", 1, 20);
//		//Objet objet4 = new Objet("assiette", 4, 100);
//		
//		ArrayList<Objet> collec1 = new ArrayList<Objet>();
//		collec1.add(objet1);
//		collec1.add(objet2);
//		collec1.add(objet3);
//		//collec1.add(objet4);
//		
//		PileObjet pile = new PileObjet(collec1);
//		Backpack sac1 = new Backpack(8, new ArrayList<Objet>());
//		Arbre a = new Arbre(sac1);
//		a.creationArbre(pile);
//		System.out.println("nb noeud"+ a.nbNoeuds(a));
//		System.out.println("droit " + a.getDroit().getSac());
//		System.out.println("droit droit " + a.getDroit().getDroit().getSac());
//		System.out.println("droit gauche " + a.getDroit().getGauche().getSac());
//		System.out.println("gauche " + a.getGauche().getSac());
//		System.out.println("gauche droit" + a.getGauche().getDroit().getSac());
		
		//a.parcours();
		//System.out.println(a.getSac());
		/*
		System.out.println(a.getDroit().getDroit().getSac());
		System.out.println(a.getDroit().getSac());
		System.out.println(a.getGauche().getDroit());
		System.out.println(a.getDroit().getSac());
		
		System.out.println(a.getDroit().getDroit().getSac());
		System.out.println(a.getGauche().getSac());
		//System.out.println(a.getDroit().getDroit().getDroit().getGauche().getSac());
		System.out.println(a.getSac());
		System.out.println(a.getGauche().getSac());
		System.out.println(a.getDroit().getSac());
		System.out.println(a.getGauche().getDroit().getSac());
		*/
		
		TextParser parser = new TextParser(new File("data\\large01.txt"));
		
		parser.parse();
		
		long estimatedTime = System.currentTimeMillis() - startTime;
		
		System.out.println("Runtime : "+ estimatedTime + "ms");
		
		
		
	}
	
	

}
