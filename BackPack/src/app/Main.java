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
		
		long startTime = System.currentTimeMillis(); 
		Objet objet1 = new Objet("cuillere", 33, 4);
		Objet objet2 = new Objet("couteau", 49, 5);
		Objet objet3 = new Objet("fourchete", 51, 5);
		Objet objet4 = new Objet("assiette", 22, 2);
		
		ArrayList<Objet> collec1 = new ArrayList<Objet>();
		collec1.add(objet1);
		collec1.add(objet2);
		collec1.add(objet3);
		collec1.add(objet4);
		
		System.out.println(((int)( Math.random()) * 15));
		TextParser parser = new TextParser(new File("data\\large03.txt"));
		
		PileObjet pile = new PileObjet(parser.parse());
		//PileObjet pile2 = pile.clone();
		Backpack sac1 = new Backpack(431, new LinkedList<Objet>());
		long startTimeArbre1 = System.currentTimeMillis(); 
		ArbreN aN = new ArbreN(sac1);
		aN.creationArbreN2(pile.clone());
		System.out.println("Arbre Cr��.");
		//System.out.println("nb Noeuds " + aN.getNbNoeuds());
		//System.out.println("Solution V1: " + aN.solutionV1());
		//aN.solutionV2(pile2);
		System.out.println("Solution V3: " + aN.getSolution());
		System.out.println("Solution V3: " + aN.getSacSolution() + "\tpoids du sac : " + aN.getSacSolution().getPoids() + "\tvaleur du sac" + aN.getSacSolution().getValeur());
		
		long estimatedTime = System.currentTimeMillis() - startTimeArbre1;
		System.out.println("time : A1V3 " + estimatedTime + "ms");
		
		
		
		
		
		
		
		/*long startTimeArbre2 = System.currentTimeMillis(); 
		ArbreN aN2 = new ArbreN(sac1);
		aN.creationArbreN(pile);
		System.out.println("Arbre Cr��.");
		//System.out.println("nb Noeuds " + aN.getNbNoeuds());
		//System.out.println("Solution V1: " + aN.solutionV1());
		aN.solutionV2(pile2);
		System.out.println("Solution V3: " + aN.getSolution());
		System.out.println("Solution V3: " + aN.getSacSolution());
		estimatedTime = System.currentTimeMillis() - startTimeArbre2;
		System.out.println("time A2V2 : " + estimatedTime + "ms");*/
		/*Arbre a = new Arbre(sac1);
		a.creationArbre(pile);
		//a.parcours();
		System.out.println("best : " + a.rechercheV1());*/
		
		
		/*System.out.println(aN.getArbres().get(0).getSac());
		System.out.println(aN.getArbres().get(1).getSac());
		System.out.println(aN.getArbres().get(2).getSac());
		System.out.println(aN.getArbres().get(3).getSac());
		System.out.println(aN.getArbres().get(0).getArbres().get(0).getSac());
		System.out.println(aN.getArbres().get(0).getArbres().get(1).getSac());
		System.out.println(aN.getArbres().get(0).getArbres().get(2).getSac());
		System.out.println(aN.getArbres().get(0).getArbres().get(2).getArbres().get(0).getArbres().get(0));*/
		//39 noeuds 18 noeuds en trop
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
		
		
		estimatedTime = System.currentTimeMillis() - startTime;
		
		System.out.println("Runtime : "+ estimatedTime + "ms");
		
		
		
	}
	
	

}
