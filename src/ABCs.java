import java.io.*;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ABCs {

	public static CardDatabase cards;
	public static CardName card;
	
	public static void main(String[] args) {
		cards = new CardDatabase();
		
		/*
		String s1 = "[R, P, G, F, H, Z, U, D, X]";
		CardName c1 = new CardName(s1);
		String s2 = "[E, V, R, Q, U, T, J, L]";
		CardName c2 = new CardName(s2);
		*/
		
		String filePath = "C:\\Users\\Mark\\workspace\\NowIKnowMyABCs\\AllSets-x.json";
		String printPath = "C:\\Users\\Mark\\workspace\\NowIKnowMyABCs\\cards-MultiLang.txt";
		CardName cnCard = null;
		
		File f = new File(printPath);
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				System.out.println("Failed to create file");
				System.exit(-1);
			}
		}
		

		FileWriter fw = null;
		
		try {
			fw = new FileWriter(f.getAbsoluteFile());
		} catch (IOException e) {
			System.out.println("Failed to open filewriter");
			System.exit(-1);
		}
		
		BufferedWriter bw = new BufferedWriter(fw);
		
		try {
			FileReader reader = new FileReader(filePath);
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject)parser.parse(reader);
			
			@SuppressWarnings("unchecked")
			Set<String> keys = (Set<String>) obj.keySet();
			
			for(String i: keys){
				JSONObject set = (JSONObject)obj.get(i);
				JSONArray setOfCards = (JSONArray)set.get("cards");
				Iterator<?> c = setOfCards.iterator();
				
				while(c.hasNext()){
					JSONObject card = (JSONObject) c.next();
					try {
						String cName = (String)card.get("name");
						bw.write(cName + "\n");
						cnCard = new CardName(cName);
						cards.add(cnCard);
						
					} catch (IOException e) {
						System.out.println("Failed to write line");
						System.exit(-1);
					}
				}
				
				
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		
		try {
			bw.close();
		} catch (IOException e) {
			System.out.println("Failed to close writer");
			System.exit(-1);
		}
		
		//long iter = 0;
		/*
		String filePath = "C:\\Users\\Mark\\workspace\\NowIKnowMyABCs\\AllCards.json";
		
		try {
			// read the json file
			FileReader reader = new FileReader(filePath);

			JSONParser jsonParser = new JSONParser();
			JSONObject obj = (JSONObject)jsonParser.parse(reader);
			
			@SuppressWarnings("unchecked")
			Set<String> keys =(Set<String>) obj.keySet();
			
			for(String i: keys){
				JSONObject jobj = (JSONObject)obj.get(i);
				String name = (String)jobj.get("name");
				CardName card = new CardName(name);
				cards.add(card);
				//System.out.println(++iter);
				
				if(card.getSet().containsAll(c1.getSet()) || card.getSet().containsAll(c2.getSet())){
					System.out.println (name);
				}
				
				//System.out.println(name);
			}
			
			//String name = null;
			
			
			//System.out.println();
		
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		*/
		
		/*
		// Open the file
		FileInputStream fstream = null;
		try {
			//fstream = new FileInputStream("C:\\Users\\Mark\\workspace\\NowIKnowMyABCs\\AllCardsSets.txt");
			fstream = new FileInputStream("C:\\Users\\Mark\\workspace\\NowIKnowMyABCs\\AllCards.txt");
		} catch (FileNotFoundException e) {
			System.out.println("File open error");
			System.exit(-1);
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;

		//Read File Line By Line
		try {
			while ((strLine = br.readLine()) != null)   {
				card = new CardName(strLine);
				cards.add(card);
				
				if(card.getSet().containsAll(c1.getSet()) || card.getSet().containsAll(c2.getSet())){
					System.out.println (strLine);
				}
				
		
			}
		} catch (IOException e) {
			System.out.println("File line read error");
			System.exit(-1);
		}

		//Close the input stream
		try {
			br.close();
		} catch (IOException e) {
			System.out.println("file close failed");
			System.exit(-1);
		}
		*/
		
		cards.reduceDatabase();
		
		cards.print();
		
		cards.exportSets();
		/*
		CardDatabase cover = cards.cover2();
		System.out.print("Cover of 2 is:");
		if(cover != null){
			cover.print();
		}
		else {
			System.out.println(" N/A");
		}
		*/
		/*
		cover = cards.cover3();
		System.out.print("Cover of 3 is:");
		if(cover != null){
			cover.print();
		}
		else {
			System.out.println(" N/A");
		}
		*/
		
	}
	
}
