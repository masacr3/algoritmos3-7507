package algo3.ajedrez;

public class FabricaDePiezasNegras extends FabricaDePiezas {

	@Override
	public Pieza crearPieza(String tipoPieza) {
		// TODO Auto-generated method stub
		if (tipoPieza == "peon") return new Peon("negro");
		if (tipoPieza == "torre") return new Torre("negro");
		if (tipoPieza == "caballo") return new Caballo("negro");
		if (tipoPieza == "alfil") return new Alfil("negro");
		if (tipoPieza == "reyna") return new Reyna("negro");
		if (tipoPieza == "rey") return new Rey("negro");
		return null;
	}

}
