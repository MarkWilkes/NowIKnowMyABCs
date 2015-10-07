import java.util.*;

public class CardName {
	private HashSet<Letters> card;
	
	public CardName(){
		
	}
	
	public boolean equals(CardName c){
		return card.containsAll(c.getSet());
	}
	
	public CardName(String s){
		//System.out.println(s);
		card = getSet(s);
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
