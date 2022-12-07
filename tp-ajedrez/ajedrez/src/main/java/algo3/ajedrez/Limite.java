package algo3.ajedrez;

public class Limite {
	final static int xSup = 8;
	final static int ySup = 8;
	final static int xInf = 0;
	final static int yInf = 0;
	
	static public boolean dentroTablero( Coordenada p) {
		return p.getx() < xSup && p.gety() < ySup && p.getx() >= xInf && p.gety() >= yInf;
	}
}
