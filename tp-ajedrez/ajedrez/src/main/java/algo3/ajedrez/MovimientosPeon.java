package algo3.ajedrez;

import java.util.ArrayList;
import java.util.Map;

public class MovimientosPeon implements MovimientosStrategy {

	private boolean ok;
	private boolean estado;

	@Override
	public ArrayList<Coordenada> comoMoverme(Pieza pieza, Map<Pieza, Coordenada> tablero, int EtapaDelJuegoActual) {
		var lista = new ArrayList<Coordenada>();
		var mov = new ArrayList<Coordenada>();
		var piezaUbicacion = tablero.get(pieza);
		var peon = (Peon)pieza;
		var pasos = peon.PuedeMoverDoble() ? 2 : 1;
		for(int i=1; i<=pasos; i++) {
			mov.add(piezaUbicacion.sumar(new Coordenada(0, peon.esBlanco() ? -i : i)));
		}
		
		this.estado = true;
		mov.stream()
			.filter(coor -> coor != null)
			.filter(coor -> !tablero.containsValue(coor))
			.forEach(coor -> lista.add(coor));
		
		
		mov.clear();
		for(int i=0, j=0; i<2; i++, j++) {
			mov.add(piezaUbicacion.sumar(new Coordenada(-1 + 2*j , peon.esBlanco() ? -1 : 1)));
		}
		
		this.estado = true;
		mov.stream()
			.filter(coor -> coor != null)
			.filter(coor ->  this.comerPieza(tablero, coor,  pieza.getColor()))
			.forEach(coor -> lista.add(coor));
		
		
		
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

	private boolean celdaOk(Map<Pieza, Coordenada> tablero, Coordenada pos, String color) {
		if (!this.estado) return false;
		if (!tablero.containsValue(pos)) return true;
		
		this.estado = false;
		this.ok = false;
		
		tablero.forEach((pieza, coor)->{
			if( coor.equals(pos) && pieza.getColor() != color) this.pushOK(); 
		});
		
		return this.getOk();
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
		return "peon";
	}


}
