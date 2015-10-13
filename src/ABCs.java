import java.io.*;
import java.util.Iterator;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ABCs {

	public static CardDatabase cards;
	public static CardDatabaseFr cardsFr;
	public static CardName card;
	public static BufferedReader in;
	
	public static void main(String[] args) {
		in = new BufferedReader(new InputStreamReader(System.in));
		cards = new CardDatabase();
		cardsFr = new CardDatabaseFr();
		
		loadData();
		
		checkReduce();
		
	}

	//loads into the CardsDatabase variable cards
	public static void loadFromAllSetsx(){
		/*
		 * You need to put the MTGJSON's allsets-x in the ABCs directory
		 */
		File file = new File(System.getProperty("user.dir") + File.separator + "AllSets-x.json");
		String printPath = System.getProperty("user.dir") + File.separator + "cards-MultiLang.txt";
		CardName cnCard = null;
		CardNameFr cnCardF = null;
		
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
			FileReader reader = new FileReader(file.getAbsolutePath());
			JSONParser parser = new JSONParser();
			//read set-x.json
			JSONObject obj = (JSONObject)parser.parse(reader);
			
			//get keyset
			@SuppressWarnings("unchecked")
			Set<String> keys = (Set<String>) obj.keySet();
			
			//for each of the sets
			for(String i: keys){
				//get the cards from the set
				JSONObject set = (JSONObject)obj.get(i);
				JSONArray setOfCards = (JSONArray)set.get("cards");
				Iterator<?> c = setOfCards.iterator();
				
				//for all the cards
				while(c.hasNext()){
					JSONObject card = (JSONObject) c.next();
					
					//check the types
					JSONArray cTypes = (JSONArray)card.get("types");
					if(cTypes != null){
						Iterator<?> typ = cTypes.iterator();
						boolean spell = false;
						
						//for all the types
						while(typ.hasNext()){
							String type = (String) typ.next();
							if(type.equals("Instant") || type.equals("Sorcery")){
								spell = true;
							}
						}
						//if they aren't an instant or sorcery
						if(!spell){
							try {
								//add them to the sets and put them in the cards
								String cName = (String)card.get("name");
								bw.write(cName + "\n");
								cnCard = new CardName(cName);
								cards.add(cnCard);
								
								cnCardF = new CardNameFr(cName);
								cardsFr.add(cnCardF);
							} catch (IOException e) {
								System.out.println("Failed to write line");
								System.exit(-1);
							}
						}
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
	}

	//loads into the CardsDatabase variable cards
	public static void loadFromAllCards(){
		/*
		 * You need to put the MTGJSON's allcard in the ABCs directory
		 */
		String filePath = System.getProperty("user.dir")+ File.separator + "AllCards.json";
		
		try {
			// read the json file
			FileReader reader = new FileReader(filePath);

			JSONParser jsonParser = new JSONParser();
			JSONObject obj = (JSONObject)jsonParser.parse(reader);
			
			//get all the cards
			@SuppressWarnings("unchecked")
			Set<String> keys =(Set<String>) obj.keySet();
			
			//for all the cards
			for(String i: keys){
				JSONObject card = (JSONObject)obj.get(i);
				JSONArray cTypes = (JSONArray)card.get("types");
				if(cTypes != null){
					Iterator<?> typ = cTypes.iterator();
					boolean spell = false;
					
					//for all the types
					while(typ.hasNext()){
						String type = (String) typ.next();
						if(type.equals("Instant") || type.equals("Sorcery")){
							spell = true;
						}
					}
					//if they aren't an instant or sorcery
					if(!spell){
						String name = (String)card.get("name");
						
						CardName cardname = new CardName(name);
						cards.add(cardname);
						

						CardNameFr cnCardF = new CardNameFr(name);
						cardsFr.add(cnCardF);
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
	}
	
	public static void loadSets(){
		CardName card = null;
		CardNameFr cardf = null;
		
		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream(System.getProperty("user.dir")+ File.separator + "AllCardsSets.txt");
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
				
				cardf = new CardNameFr(strLine);
				cardsFr.add(cardf);
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
	}
	
	public static void loadData(){
		//loop until we get a valid input
		while(true){
			String line = null;
			//prompt
			System.out.println("What you like to load from? \n"
					+ "AllSets-x.json   (1) \n"
					+ "AllCards.json    (2) \n"
					+ "AllCardsSets.txt (3) \n");
			
			//read line
			try { line = in.readLine();} 
			catch (IOException e) { e.printStackTrace();}
			
			//check load type
			if(line.equals("1")){
				loadFromAllSetsx();
				return;
			}
			else if(line.equals("2")){
				loadFromAllCards();
				return;
			}
			else if(line.equals("3")){
				loadSets();
				return;
			}
			else{
				//invalid
				System.out.println("Please input the number associated with what you would like to load.");
			}
		}
	}
	
	public static void checkReduce(){
		while(true){
			String line = null;
			//prompt
			System.out.println("Would you like to reduce the data set? (Y/N)");
			
			//read line
			try { line = in.readLine();} 
			catch (IOException e) { e.printStackTrace();}
			
			if(line.equals("Y")){
				cards.reduceDatabase();
				cardsFr.reduceDatabase();
				checkPrint();
				checkExport();
				checkCover();
				return;
			}
			else if(line.equals("N")){
				System.out.println("Okay, but you won't be able to check for covers as it will take far to long.");
				checkPrint();
				checkExport();
				return;
			}
			else{
				//invalid
				System.out.println("Please input Y or N");
			}
		}
	}
	
	public static void checkPrint(){
		while(true){
			String line = null;
			//prompt
			System.out.println("Would you like to print the data set? (Y/N)");
			
			//read line
			try { line = in.readLine();} 
			catch (IOException e) { e.printStackTrace();}
			
			if(line.equals("Y")){
				cards.print();
				cardsFr.print();
				return;
			}
			else if(line.equals("N")){
				return;
			}
			else{
				//invalid
				System.out.println("Please input \"Y\" or \"N\"");
			}
		}
	}
	
	public static void checkExport(){
		while(true){
			String line = null;
			//prompt
			System.out.println("Would you like to export the data set into AllCardsSets.txt? (Y/N)");
			
			//read line
			try { line = in.readLine();} 
			catch (IOException e) { e.printStackTrace();}
			
			if(line.equals("Y")){
				while(true){
					System.out.println("English(1) or French(2)");
					
					try{line = in.readLine();}
					catch (IOException e) {e.printStackTrace();}
					if(line.equals("1")){
						cards.exportSets();	
					}
					else if(line.equals("2")){
						cardsFr.exportSets();	
					}
					else {
						System.out.println("Sorry, try again");
					}
					break;
				}
				return;
			}
			else if(line.equals("N")){
				return;
			}
			else{
				//invalid
				System.out.println("Please input \"Y\" or \"N\"");
			}
		}
	}
	
	public static void checkCover(){
		while(true){
			String line = null;
			//prompt
			System.out.println("Would you like to check for covers of 2? (Y/N)");
			
			//read line
			try { line = in.readLine();} 
			catch (IOException e) { e.printStackTrace();}
			
			if(line.equals("Y")){
				cards.cover2();
				cardsFr.cover2();
				return;
			}
			else if(line.equals("N")){
				return;
			}
			else{
				//invalid
				System.out.println("Please input \"Y\" or \"N\"");
			}
		}
	}
}

