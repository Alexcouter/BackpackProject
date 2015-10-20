package backpack;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		Objet objet1 = new Objet("cuillere", 1, 10);
		Objet objet2 = new Objet("couteau", 2, 5);
		Objet objet3 = new Objet("fourchete", 1, 20);
		Objet objet4 = new Objet("assiette", 4, 100);
		
		List<Objet> collec1 = new ArrayList<Objet>();
		collec1.add(objet1);
		collec1.add(objet2);
		collec1.add(objet3);
		collec1.add(objet4);
		
		Backpack sac1 = new Backpack(15, collec1);
		System.out.println(sac1);
	}

}
