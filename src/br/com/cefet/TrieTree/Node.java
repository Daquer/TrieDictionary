package br.com.cefet.TrieTree;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Node {
	public static final int LEAF = 1;
	public static final int NOLEAF = 0;
	
//TODO limitar hashMap à maior chave ser 25
	private HashMap<Integer, Long> elements; //<posicao / ponteiro (ou proximo)>
	
	private int isLeaf;//1 ou 0 - uso de int pois boolean nao tem um tamanho fixo de bytes.
    
    public Node(int isLeaf) {
    	this.elements = new HashMap<Integer, Long>();
    	this.isLeaf = isLeaf;
    }
    
    public void writeNode(DataOutput out) throws IOException {
		short qt = (short) elements.size();
		out.writeShort(qt);
		
		for(int key : elements.keySet()) {
			out.writeInt((int) key);//posicao
			out.writeLong(elements.get(key));
		}
		
		out.writeInt(isLeaf);
        
		int falta = 4096-(2+qt*4+qt*8+4);
		byte b[] = new byte[falta];
		out.write(b);
    }
    
    public void readNodeFromFile(DataInput in) throws IOException {
    	int quantidade = in.readShort();

    	elements.clear();
        
        for(int i=0; i<quantidade;i++)
        {
        	int key = in.readInt();
        	Long ponteiro = in.readLong();
        	elements.put(key, ponteiro);  	
        }
        
        isLeaf = in.readInt();
    }
    
    public void setPointerByKey(Integer key, Long pointer) {
    	elements.put(key, pointer) ;
    }
    
    //implementado no main
    public void insertWord(String word) {
    	//transformar word em array de alfabeto para poder limitar o uso ao alfabeto escolhido
    }
    
    public Integer insertLetter(Alphabet letter) { //exemplo de chamada "insereLetra(Alfabeto.A);" //Posso inserir duas letras no mesmo nó numa mesma vez? //
    	int intLetter = letter.getValue();
    	
    	elements.put(intLetter, new Long(0));
    	
    	return intLetter;
    }
    
    public ArrayList<String> getActiveLetters() { //letras em ordem decrescente
    	ArrayList<String> letters = new ArrayList<String>();
    	for(int key : elements.keySet()) {
    		
    		if(elements.get(key) != null) {
    			letters.add(Alphabet.getLetterByInt(key).toString());
    		}
    	}
    	
    	Collections.sort(letters);
    	
    	return letters;
    }
    
    public void insertWordLetter(Alphabet letter) {
    	
    }
    
    /*public List<Integer> getPonteiros() { //este raciocinio gerara um getLetras()
        List<Integer> ponteiros = new ArrayList<Integer>();
        
        for(ElementoNo elemento : this.elementos){
            ponteiros.add(elemento.getPonteiro());
        }
          
        return ponteiros;
    }*/
    
    public int getIsFolha() {
        return isLeaf;
    }
    
    public void setIsFolha(int isLeaf) {
    	this.isLeaf = isLeaf;
    }
    
    
    @Deprecated
    public int[] clearIntArray(int[] intArray) {
    	for(int i=0; i<intArray.length; i++) {
    		intArray[i] = 0;
    	}
    	
    	return intArray;
    }

	public HashMap<Integer, Long> getElements() {
		return elements;
	}

	public void setElements(HashMap<Integer, Long> elements) {
		this.elements = elements;
	}
}