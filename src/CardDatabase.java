import java.util.*;
import java.io.*;


public class CardDatabase {
	private HashSet<CardName> cards;
	
	public CardDatabase(){
		cards = new HashSet<CardName>();
	}
	
	public boolean add(CardName s){
		return cards.add(s);
	}
	
	public boolean addAll(HashSet<CardName> s){
		return cards.addAll(s);
	}
	
	public boolean remove(CardName s){
		return cards.remove(s);
	}
	
	public void reduceDatabase(){
		
		HashSet<CardName> s = new HashSet<CardName>();
		s.addAll(cards);
		
		for(CardName i : s){
			for(CardName j : s){
				int res = properSubset(i, j);
				if(1 == res){
					cards.remove(j);
				}
				else if(0 == res){
					cards.remove(i);
					break;
				}
			}
		}
		
		s = new HashSet<CardName>();
		s.addAll(cards);
		
		System.out.println("Done removing subsets");
		System.out.println(s.size());
		CardName[] a = s.toArray(new CardName[s.size()]);
		
		for(int i = 0; i < a.length-1; i ++){
			for(int j = (i+1); j < a.length; j ++){
				int res = properSubset(a[i], a[j]);
				if(res == -2){
					cards.remove(a[j]);
				}
			}
		}
		System.out.println("Done removing duplicates");
	}
	
	public void print(){
		System.out.println(cards.toString());
	}
	
	public void exportSets(){
		File f = new File("C:\\Users\\Mark\\workspace\\NowIKnowMyABCs\\AllCardsSets.txt");
		
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
		
		for(CardName c : cards){
			try {
				bw.write(c.toString() + "\n");
			} catch (IOException e) {
				System.out.println("Failed to write line");
				System.exit(-1);
			}
		}
		
		try {
			bw.close();
		} catch (IOException e) {
			System.out.println("Failed to close writer");
			System.exit(-1);
		}

	}
	
	/*
	 * 1 = a is a proper superset for b 
	 * 0 = b is a proper superset for a
	 * -1 = a and b are distinct
	 * -2 = a and b are the same
	 */
	public static Integer properSubset(CardName a, CardName b){
		if(a.containsAll(b) && !b.containsAll(a)){
			return 1;
		}
		else if(!a.containsAll(b) && b.containsAll(a)){
			return 0;
		}
		else if(a.containsAll(b) && b.containsAll(a)){
			return -2;
		}
		
		return -1;
	}
	
	public void cover2(){
		System.out.print("Cover of 2 is: \n");
		
		HashSet<CardName> s = new HashSet<CardName>();
		s.addAll(cards);
		
		HashSet<Letters> union;
		
		for(CardName i: s){
			for(CardName j: s){
				union = new HashSet<Letters>();
				union.addAll(i.getSet());
				union.addAll(j.getSet());
				if(coversAlphabet(union)){
					CardDatabase covered = new CardDatabase();
					covered.add(i);
					covered.add(j);
					covered.print();
				}
			}
		}
	}

	public void cover3(){
		System.out.print("Cover of 3 is: \n");
		
		HashSet<CardName> s = new HashSet<CardName>();
		s.addAll(cards);
		
		HashSet<Letters> union;
		
		for(CardName i: s){
			for(CardName j: s){
				for(CardName k: s){
					union = new HashSet<Letters>();
					union.addAll(i.getSet());
					union.addAll(j.getSet());
					union.addAll(k.getSet());
					if(coversAlphabet(union)){
						CardDatabase covered = new CardDatabase();
						covered.add(i);
						covered.add(j);
						covered.add(k);
						covered.print();
					}	
				}
			}
		}
	}
	
	private boolean coversAlphabet(HashSet<Letters> s){
		CardName alphabet;
		String str = "abcdefghijklmnopqrstuvwxyz";
		
		alphabet = new CardName(str);
		
		return(s.containsAll(alphabet.getSet()));
	}
}
