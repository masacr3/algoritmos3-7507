package algo3.ajedrez;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface MovimientosStrategy {
	ArrayList<Coordenada> comoMoverme(Pieza pieza, Map<Pieza, Coordenada> tablero, int EtapaDelJuegoActual);

	String getNombre();
}
