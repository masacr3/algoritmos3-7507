package algo3.ajedrez;

public class FabricaDePiezasBlancas extends FabricaDePiezas {

	@Override
	public Pieza crearPieza(String tipoPieza) {
		if (tipoPieza == "peon") return new Peon("blanco");
		if (tipoPieza == "torre") return new Torre("blanco");
		if (tipoPieza == "caballo") return new Caballo("blanco");
		if (tipoPieza == "alfil") return new Alfil("blanco");
		if (tipoPieza == "reyna") return new Reyna("blanco");
		if (tipoPieza == "rey") return new Rey("blanco");
		return null;
	}

}
