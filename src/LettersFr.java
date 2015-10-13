/*
 * "Je Connais Mon Alphabet" language
 */

public enum LettersFr {
	D,F,G,K,Q,R,U,V,W,X,Y,Z,FAIL;
	
	public static boolean isInLetters(char c){
		return(LettersFr.FAIL != LettersFr.getLetter(c));
	}
	
	public static LettersFr getLetter(char c){
		switch(Character.toLowerCase(c)){
			case 'a': break;
			case 'b': break;
			case 'c': break;
			case 'd': return LettersFr.D;
			case 'e': break;
			case 'f': return LettersFr.F;
			case 'g': return LettersFr.G;
			case 'h': break;
			case 'i': break;
			case 'j': break;
			case 'k': return LettersFr.K;
			case 'l': break;
			case 'm': break;
			case 'n': break;
			case 'o': break;
			case 'p': break;
			case 'q': return LettersFr.Q;
			case 'r': break;
			case 's': break;
			case 't': break;
			case 'u': return LettersFr.U;
			case 'v': return LettersFr.V;
			case 'w': return LettersFr.W;
			case 'x': return LettersFr.X;
			case 'y': return LettersFr.Y;
			case 'z': return LettersFr.Z;
		}
		return LettersFr.FAIL;
	}
}
