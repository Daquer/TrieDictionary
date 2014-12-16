package br.com.cefet.TrieTree;

@Deprecated
public class Element {
	protected static int INACTIVE = 0;
	protected static int ACTIVE = 1;
	private int isActive;
	
	//PROVAVELMENTE SOH PRECISARA DO PONTEIRO
	private long ponteiro;
	
	public Element(int isActive, long ponteiro) {
		super();
		this.isActive = isActive;
		this.ponteiro = ponteiro;  
	}
	
	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public long getPonteiro() {
		return ponteiro;
	}

	public void setPonteiro(long ponteiro) {
		this.ponteiro = ponteiro;
	}

	


	
}
