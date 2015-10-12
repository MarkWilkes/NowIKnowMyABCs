
/*
 * "Now I Know My ABC's" language
 */

public enum Letters {
	D,E,F,G,H,J,L,P,Q,R,T,U,V,X,Z,FAIL;
	
	public static boolean isInLetters(char c){
		return(Letters.FAIL != Letters.getLetter(c));
	}
	
	public static Letters getLetter(char c){
		switch(Character.toLowerCase(c)){
			case 'a': break;
			case 'b': break;
			case 'c': break;
			case 'd': return Letters.D;
			case 'e': return Letters.E;
			case 'f': return Letters.F;
			case 'g': return Letters.G;
			case 'h': return Letters.H;
			case 'i': break;
			case 'j': return Letters.J;
			case 'k': break;
			case 'l': return Letters.L;
			case 'm': break;
			case 'n': break;
			case 'o': break;
			case 'p': return Letters.P;
			case 'q': return Letters.Q;
			case 'r': return Letters.R;
			case 's': break;
			case 't': return Letters.T;
			case 'u': return Letters.U;
			case 'v': return Letters.V;
			case 'w': break;
			case 'x': return Letters.X;
			case 'y': break;
			case 'z': return Letters.Z;
		}
		return Letters.FAIL;
	}
}
