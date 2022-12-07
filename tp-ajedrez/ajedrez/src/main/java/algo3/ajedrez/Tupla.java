package algo3.ajedrez;

public class Tupla {
	private String tipoPieza;
	private Coordenada p;
	
	Tupla( String tipoPieza, Coordenada p){
		this.tipoPieza = tipoPieza;
		this.p = p;
	}
	
	public String getPieza() {
		return this.tipoPieza;
	}
	
	public Coordenada getPosicion() {
		return this.p;
	}
}
