package algo3.ajedrez;

public class Alfil extends Pieza {

	public Alfil(String color) {
		super("alfil", color, new MovimientosDiagonales());
	}

}
