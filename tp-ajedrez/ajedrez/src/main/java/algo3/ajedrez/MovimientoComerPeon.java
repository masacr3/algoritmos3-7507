package algo3.ajedrez;

import java.util.ArrayList;
import java.util.Map;

public class MovimientoComerPeon implements MovimientosStrategy {

	private boolean ok;

	@Override
	public ArrayList<Coordenada> comoMoverme(Pieza pieza, Map<Pieza, Coordenada> tablero, int EtapaDelJuegoActual) {
		var lista = new ArrayList<Coordenada>();
		var mov = new ArrayList<Coordenada>();
		var piezaUbicacion = tablero.get(pieza);
		
		for(int i=0, j=0; i<2; i++, j++) {
			mov.add(piezaUbicacion.sumar(new Coordenada(-1 + 2*j , pieza.esBlanco() ? -1 : 1)));
		}
		
		mov.stream()
			.filter(coor -> coor != null)
			.filter(coor ->  this.comerPieza(tablero, coor,  pieza.getColor()))
			.forEach(coor -> lista.add(coor));
		
		return lista;
	}
	
	private boolean comerPieza(Map<Pieza, Coordenada> tablero, Coordenada pos, String color) {
		if (!tablero.containsValue(pos)) return false;
		
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
		// TODO Auto-generated method stub
		return this.ok;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "comerPeon";
	}

}
