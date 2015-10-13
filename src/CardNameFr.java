import java.text.Normalizer;
import java.util.*;


public class CardNameFr {
	private HashSet<LettersFr> card;
	
	public CardNameFr(){
		
	}
	
	public CardNameFr(String s){
	    String normalized = Normalizer.normalize(s, Normalizer.Form.NFD);
	    normalized.replaceAll("[^\\p{ASCII}]", "");
		card = getSet(normalized);
	}
	
	public HashSet<LettersFr> getSet(){
		return card;
	}
	
	public boolean containsAll(CardNameFr c){
		return card.containsAll(c.getSet());
	}
	
	public static HashSet<LettersFr> getSet(String s){
		HashSet<LettersFr> t = new HashSet<LettersFr>();
		
		for(int i = 0; i < s.length(); i++){
			if(LettersFr.isInLetters(s.charAt(i))){
				t.add(LettersFr.getLetter(s.charAt(i)));
			}
		}
		
		return t;
	}
	
	public boolean equals(CardNameFr c){
		return card.containsAll(c.getSet());
	}
	
	public void print(){
		System.out.println(card.toString());
	}
	
	public String toString(){
		return card.toString();
	}
}
