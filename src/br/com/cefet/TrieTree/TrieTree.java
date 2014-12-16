
package br.com.cefet.TrieTree;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class TrieTree {

	public static final int NAO = 0;
	public static final int SIM = 1;

	public static void printTree(RandomAccessFile arq) throws IOException {
		long pos = 0;
		
		arq.seek(0);
		
		int i = 0;
		while(pos != arq.length()) {
			
			
			Node node = new Node(SIM);
			
			
			
			node.readNodeFromFile(arq);
			System.out.println("No " + i + "\n");
			
			int qt = node.getElements().size();
			
			System.out.println("qt "+ " = " + qt);
			
			for(Integer key : node.getElements().keySet())
	        {
				
				System.out.println("key => " + key + " letra => " + Alphabet.getLetterByInt(key) + " | Ponteiro => " + node.getElements().get(key));
				
				//pos = node.getElements().get(key);
				
				//arq.seek(pos);
	        }
			
			System.out.println("isLeaf?? => " + (node.getIsFolha() == 1 ? "SIM" : "NAO") + "\n");
			pos += 4096;
			//long falta = 4096-(2+qt*4+qt*8+4);
			
			arq.seek(pos);
			i++;
		}
	}
	
	//leitura do arquivo
	public static void main(String[] args) throws Exception {
		RandomAccessFile arq = new RandomAccessFile("/tmp/TrieTree.dat", "rw");
		
		printTree(arq);
//		ArrayList<String> palavras = new ArrayList<String>();
//		long pos = 0;
//		
//		arq.seek(0);
//		//String palavra = "";
//		
//		while(pos != arq.length()) {
//			int[] keys = new int[26];
//			Node node = new Node(SIM);
//			
//			node.readNodeFromFile(arq);
//			
//			int qt = node.getElements().size();
//			
//			System.out.println("numero de elementos ==> " + qt);
//			
//			int i = 0;
//			
//			for(Integer key : node.getElements().keySet())
//	        {
////				if(qt>1) {
////					keys[i] = key;
////				}
//				
//				System.out.println(node.getActiveLetters() + " | Prox => " + node.getElements().get(key)
//						+ " | isFolha? " + node.getIsFolha());
				
//				int folha = 0;
//				while(folha != 1) {
//					arq.seek(node.getElements().get(key));
//					
//					Node nodeAux = new Node(SIM);
//					
//					nodeAux.getElements().keySet();
//					
//					folha = nodeAux.getIsFolha();
//				}
//	        }
			
			//Posiciona na raiz novamente
//			if(node.getIsFolha() == 1) {
//				//palavras.add(palavra);
//				//palavra = "";
//				
//				if(pos != arq.length()){
//					arq.seek(0);
//				} else {
//					arq.seek(pos);
//				}
//			} else {
//				arq.seek(pos);
//			}
//			
			
//			System.out.println("PROXIMO " + pos + "\n");
//			
//		}
//		
//		
//		//System.out.println(palavras);
//		arq.close();
	}
	
//	private static void readWords(RandomAccessFile arq) {
//		
//		
//	}

	//Insercao no arquivo
    public static void main__(String[] args) throws Exception {
        RandomAccessFile arq = new RandomAccessFile("/tmp/TrieTree.dat", "rw");
    	
        ArrayList<String> palavras = new ArrayList<String>();
        palavras.add("teste");
        palavras.add("Leandro");
        
    	ArrayList<Node> nodes = new ArrayList<Node>();
    	
    	for(int i=0; i<palavras.size(); i++) {
    		String word = palavras.get(i);
    		
	    	for(int j=0; j<word.length(); j++) { //escreve os nos
	    		
	    		
	    		//?????implementar indetificação de no ja existente para conseguir inserir na trie
	    		
	    		//implementar adição de palavra com um radical ja existente
	    		if(j==0 ) {
	    			Node node = new Node(SIM);
	    			//int letter = r.nextInt(26);
	    			if(i != 0) { 
	    				node = nodes.get(0);
	    			}
	    			
	    			String letter = String.valueOf(word.charAt(j));
	    			
	    			int hashPos = node.insertLetter(
	    					Alphabet.getLetterByInt(
	    							Alphabet.valueOf(letter.toUpperCase()).getValue()));
	    			
	    			nodes.add(0, node);
	    			if(nodes.size() > 1 ) {
	    				nodes.remove(1);
	    			}
	    			arq.seek(0);
	    			
	    			node.writeNode(arq);//raiz na posicao 0 do arquivo
	    			
	    			if(j+1<word.length()){
	    				node.setIsFolha(NAO);
	    			}
	    			
	    			long finalNodePosition = arq.length();
	    			node.setPointerByKey(hashPos, finalNodePosition);
	    			arq.seek(0);
	    			node.writeNode(arq);
	    		} else {
	    			
	    			Node node = new Node(SIM);
	    			//int letter = r.nextInt(26);
	    			
	    			String letter = String.valueOf(word.charAt(j));
	    			
	    			int hashPos = node.insertLetter(
	    					Alphabet.getLetterByInt(
	    							Alphabet.valueOf(letter.toUpperCase()).getValue()));
	    			
	    			nodes.add(node);
	    			//arq.seek(arq.length());
	    			//node.writeNode(arq);//raiz na posicao 0 do arquivo
	    			
	    			long initialNodePosition = arq.length();
	    			arq.seek(initialNodePosition);
	    			node.writeNode(arq);
	
	    			if(j+1<word.length()){
	    				node.setIsFolha(NAO);
	    			}
	    			
	    			long finalNodePosition = arq.length();
	    			node.setPointerByKey(hashPos, finalNodePosition);
	    			arq.seek(initialNodePosition);
	    			node.writeNode(arq);
	    		}
	    	
	    	}
    	}
    
    	arq.close();
    	
//    	Node node = new Node(Node.LEAF);
//    	
//    	node.setIsFolha(1);
//    	
//    	node.insertLetter(Alphabet.C);
//    	node.insertLetter(Alphabet.R);
//    	node.insertLetter(Alphabet.Z);
//    	
    	for(Node node: nodes) {
    		System.out.println(node.getActiveLetters());
    		if(node.getIsFolha() == 1) {
    			System.out.println("-------");
    		}
    	}
    }
}
