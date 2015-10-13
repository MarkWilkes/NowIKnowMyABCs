import java.util.*;
import java.text.Normalizer;

public class CardName {
	private HashSet<Letters> card;
	
	public CardName(){
		
	}
	
	public boolean equals(CardName c){
		return card.containsAll(c.getSet());
	}
	
	public CardName(String s){
	    String normalized = Normalizer.normalize(s, Normalizer.Form.NFD);
	    normalized.replaceAll("[^\\p{ASCII}]", "");
		card = getSet(normalized);
	}
	
	public HashSet<Letters> getSet(){
		return card;
	}
	
	public boolean containsAll(CardName c){
		return card.containsAll(c.getSet());
	}
	
	public void print(){
		System.out.println(card.toString());
	}
	
	public static HashSet<Letters> getSet(String s){
		HashSet<Letters> t = new HashSet<Letters>();
		
		for(int i = 0; i < s.length(); i++){
			if(Letters.isInLetters(s.charAt(i))){
				t.add(Letters.getLetter(s.charAt(i)));
			}
		}
		
		return t;
	}
	
	public String toString(){
		return card.toString();
	}
}
