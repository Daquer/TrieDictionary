package br.com.cefet.TrieTree;

public enum Alphabet {
	A(0),
	B(1),
	C(2),
	D(3),
	E(4),
	F(5),
	G(6),
	H(7),
	I(8),
	J(9),
	K(10),
	L(11),
	M(12),
	N(13),
	O(14),
	P(15),
	Q(16),
	R(17),
	S(18),
	T(19),
	U(20),
	V(21),
	W(22),
	X(23),
	Y(24),
	Z(25);
	
	private int value;

    private Alphabet(int value) {
            this.value = value;
    }

	public int getValue() {
		return value;
	}
	
	public static Alphabet getLetterByInt(int intLetter) {
		Alphabet[] letras = Alphabet.values();
		for(int i=0; i<letras.length; i++) {
			if(intLetter == letras[i].getValue()) {
				return letras[i];
			}
		}
		
		return null;
	}
	
	public static Alphabet getIntByChar() {
		return null;
	}
}
