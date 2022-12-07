package algo3.ajedrez;

import java.util.ArrayList;
import java.util.Map;

public class MovimientosDiagonalesYRectos implements MovimientosStrategy {

	@Override
	public ArrayList<Coordenada> comoMoverme(Pieza pieza, Map<Pieza, Coordenada> tablero, int EtapaDelJuegoActual) {
		var estrategiaTipoAlfil = new MovimientosDiagonales();
		var estrategiaTipoTorre = new MovimientosVerticalesHorizontales();
		
		var lista = estrategiaTipoAlfil.comoMoverme(pieza, tablero, EtapaDelJuegoActual);
		estrategiaTipoTorre.comoMoverme(pieza, tablero,EtapaDelJuegoActual).forEach(coor -> lista.add(coor));
		
		return lista;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "diagonalesYRectos";
	}

}
