package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import backpack.Objet;

public class TextParser {
	FileReader input;
	ArrayList<Objet> objets;

	public TextParser(File fichier) throws FileNotFoundException{
		super();
		this.input = new FileReader(fichier);
		this.objets = new ArrayList<Objet>();
	}

	protected ArrayList<Objet> parse() throws IOException{
		BufferedReader bufRead = new BufferedReader(this.input);
		String ligne = null;

		while ( (ligne = bufRead.readLine()) != null)
		{    
			if(ligne.startsWith("#")){}
			else{
				String[] array1 = ligne.split(" ");
				if(array1[1].equals("")){}
				else
					objets.add(
							new Objet(
									Integer.parseInt(array1[0]),  
									Integer.parseInt(array1[1])
									)
							);
			}

		}
		return objets;

	}


}
