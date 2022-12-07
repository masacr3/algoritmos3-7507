package algo3.ajedrez;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MovimientosEnL implements MovimientosStrategy {

	private boolean ok;

	@Override
	public ArrayList<Coordenada> comoMoverme(Pieza pieza, Map<Pieza, Coordenada> tablero, int EtapaDelJuegoActual) {
		var movimientos = new ArrayList<Coordenada>();
		var lista = new ArrayList<Coordenada>();
		var piezaUbicacion = tablero.get(pieza);
		
		int dx[] = { -2,-2, -1, -1, 1, 1, 2, 2 };
		int dy[] = { -1, 1, -2, 2 , -2, 2, -1, 1};

		for(int i=0; i < 8; i++) {
			Coordenada coordPosible = piezaUbicacion.sumar(new Coordenada(dx[i], dy[i]));
			if( coordPosible == null ) continue;
			movimientos.add(coordPosible);
		}
		
		movimientos.stream()
		.filter(coor -> this.celdaOk(tablero, coor, pieza.getColor() ) )
		.forEach(coor -> lista.add(coor));
		
		return lista;
	}
	
	private boolean celdaOk(Map<Pieza, Coordenada> tablero, Coordenada pos, String color) {
		if (!tablero.containsValue(pos)) return true;
		
		this.ok = false;
		
		tablero.forEach((pieza, coor)->{
			if( coor.equals(pos) && pieza.getColor() != color) this.pushOK(); 
		});
		
		return this.getOk();
	}
	
	private void pushOK() {
		this.ok = true;
	}
	
	private boolean getOk() {
		return this.ok;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "enL";
	}

}
