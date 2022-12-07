package algo3.ajedrez;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class MovimientosEnroque implements MovimientosStrategy{

	private boolean ok;

	@Override
	public ArrayList<Coordenada> comoMoverme(Pieza pieza, Map<Pieza, Coordenada> tablero, int EtapaDelJuegoActual) {
		var lista = new ArrayList<Coordenada>();
		var mov = new ArrayList<Coordenada>();
		var pos = tablero.get(pieza);
		var color = pieza.getColor();
		
		if (pieza.movio || pos.gety() != (pieza.esBlanco() ? 7 : 0) ) return lista;
		
		this.pushFalse();
		
		tablero.forEach((item,coor) -> {
			if( coor.equals(new Coordenada(0, pieza.esBlanco() ? 7 : 0 )) && pieza.getColor() == color && !item.esTorreYMovio() ) this.pushOk();
		});
		
		if (this.getOk()) {
			for(int i=pos.getx(), j=1 ; i > 1 ; i--, j++) {
				mov.add(pos.sumar(new Coordenada(-j,0)));
			}
			
			var ok = mov.stream()
				.filter(coor -> tablero.containsValue(coor) )
				.collect(Collectors.toList())
				.size() == 0;
			
			if (ok) lista.add(new Coordenada(0, pieza.esBlanco() ? 7 : 0));
				
		}
		
		
		this.pushFalse();
		mov.clear();
		
		tablero.forEach((item,coor) -> {
			if( coor.equals(new Coordenada(7, pieza.esBlanco() ? 7 : 0 )) && pieza.getColor() == color && !item.esTorreYMovio() ) this.pushOk();
		});
		
		if (this.getOk()) {
			for(int i=pos.getx(), j=1 ; i < 6 ; i++, j++) {
				mov.add(pos.sumar(new Coordenada(j,0) ));
			}
			
			var ok = mov.stream()
				.filter(coor -> tablero.containsValue(coor) )
				.collect(Collectors.toList())
				.size() == 0;
			
			if (ok) lista.add(new Coordenada(7,pieza.esBlanco() ? 7 : 0));
				
		}
		return lista;
	
	}
	

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "enroque";
	}
	
	private boolean getOk() {
		// TODO Auto-generated method stub
		return this.ok;
	}

	private void pushOk() {
		// TODO Auto-generated method stub
		this.ok = true;
		
	}

	private void pushFalse() {
		// TODO Auto-generated method stub
		this.ok = false;
	}

}
