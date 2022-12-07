package algo3.ajedrez;

import java.util.Objects;

public class Coordenada {
	private int x;
	private int y;
	
	public Coordenada(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public boolean contains(int x, int y) {
		return this.x == x && this.y == y;
	}
	
	public void setPosicion(Coordenada p) {
		this.x = p.getx();
		this.y = p.gety();
	}
	
	public int getx() {
		return this.x;
	}
	
	public int gety() {
		return this.y;
	}
	
	public Coordenada sumar( Coordenada otra) {
		if ( Limite.dentroTablero( new Coordenada(this.x + otra.getx(), this.y + otra.gety()) )) {
			return new Coordenada ( this.x + otra.getx(), this.y + otra.gety() );
		}
		else {
			return null;
		}	
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( this == obj) return true;
		if ( obj instanceof Coordenada ) {
			Coordenada otro = (Coordenada)obj;
			return otro.getx() == this.x && otro.gety() == this.y;
		}
		else {
			return false;
		}
		
	}
	
	public boolean estaEnLimite() {
		return Limite.dentroTablero(this);
	}
	
	@Override
	  public int hashCode() {
	    return Objects.hash(this.x, this.y);
	}
	
	@Override
	public String toString() {
		return "( "+this.x+", "+this.y+" )";
	}

	public Coordenada copiar() {
		// TODO Auto-generated method stub
		return new Coordenada(this.x, this.y);
	}
	
}
