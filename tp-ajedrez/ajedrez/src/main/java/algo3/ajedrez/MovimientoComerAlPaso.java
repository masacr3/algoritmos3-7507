package algo3.ajedrez;

import java.util.ArrayList;
import java.util.Map;

public class MovimientoComerAlPaso implements MovimientosStrategy {

	private boolean ok;
	private boolean estado;

	@Override
	public ArrayList<Coordenada> comoMoverme(Pieza pieza, Map<Pieza, Coordenada> tablero, int EtapaDelJuegoActual) {
		var lista = new ArrayList<Coordenada>();
		var mov = new ArrayList<Coordenada>();
		var piezaUbicacion = tablero.get(pieza);
		var peon = (Peon)pieza;
		
		mov.clear();
		for(int i=0, j=0; i<2; i++, j++) {
			mov.add(piezaUbicacion.sumar(new Coordenada(-1 + 2*j , 0)));
		}
		
		this.estado = true;
		mov.stream()
			.filter(coor -> coor != null)
			.filter(coor ->  this.comerPiezaAlPaso(tablero, coor, peon, pieza.getColor(), EtapaDelJuegoActual))
			.forEach(coor -> lista.add(coor));
		
		
		return lista;
	}
	
	private boolean comerPiezaAlPaso(Map<Pieza, Coordenada> tablero, Coordenada pos, Peon miPeon, String color, int EtapaDelJuegoActual) {
		if (!tablero.containsValue(pos)) return false;
		
		this.ok = false;
		
		tablero.forEach((pieza, coor)->{
			if( coor.equals(pos) && pieza.getColor() != color && pieza.esPeon() ) {
				var peon = (Peon)pieza;
				if (peon.movioDoble() && EtapaDelJuegoActual - peon.getEtapa()  == 1) this.pushOK();
			}
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
		return "comerAlPaso";
	}

}
