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
	
	public static void main(String[] args) {
		cards = new CardDatabase();
		cardsFr = new CardDatabaseFr();
		
		loadFromAllSetsx();
		
		//loadFromAllCards();
		
		//loadSets();
		
		cards.reduceDatabase();
		
		//cards.print();
		
		cards.exportSets();
		
		cards.cover2();
		//as it turns out there are a lot of 3 covers
		//cards.cover3();
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
			JSONObject obj = (JSONObject)parser.parse(reader);
			
			@SuppressWarnings("unchecked")
			Set<String> keys = (Set<String>) obj.keySet();
			
			for(String i: keys){
				JSONObject set = (JSONObject)obj.get(i);
				JSONArray setOfCards = (JSONArray)set.get("cards");
				Iterator<?> c = setOfCards.iterator();
				
				while(c.hasNext()){
					JSONObject card = (JSONObject) c.next();
					
					JSONArray cTypes = (JSONArray)card.get("types");
					if(cTypes != null){
						Iterator<?> typ = cTypes.iterator();
						boolean spell = false;
						
						while(typ.hasNext()){
							String type = (String) typ.next();
							if(type.equals("Instant") || type.equals("Sorcery")){
								spell = true;
							}
						}
						if(!spell){
							try {
								String cName = (String)card.get("name");
								bw.write(cName + "\n");
								cnCard = new CardName(cName);
								cnCardF = new CardNameFr(cName);
								
								cards.add(cnCard);
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
			
			@SuppressWarnings("unchecked")
			Set<String> keys =(Set<String>) obj.keySet();
			
			for(String i: keys){
				JSONObject jobj = (JSONObject)obj.get(i);
				String name = (String)jobj.get("name");
				CardName card = new CardName(name);
				cards.add(card);
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
}

