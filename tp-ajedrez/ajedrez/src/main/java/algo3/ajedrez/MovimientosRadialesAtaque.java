package algo3.ajedrez;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class MovimientosRadialesAtaque implements MovimientosStrategy{

	private boolean estado;
	private boolean ok;

	@Override
	public ArrayList<Coordenada> comoMoverme(Pieza pieza, Map<Pieza, Coordenada> tablero, int EtapaDelJuegoActual) {
		// TODO Auto-generated method stub
		var array = Arrays.asList(1,2,3,4,5,6,7,8);
		
		var lista = new ArrayList<Coordenada>();
		
		var piezaUbicacion = tablero.get(pieza);
		
		this.estado = true;
		
		array.stream()
			.map(x -> piezaUbicacion.sumar(new Coordenada(x,x)))
			.filter(coor -> coor != null )
			.filter(coor -> this.celdaOk(tablero, coor, pieza.getColor() ) )
			.forEach(coor -> lista.add(coor));
		
		
		ok = lista.stream()
			.filter(coor -> this.piezaEnemiga(tablero, coor))
			.collect(Collectors.toList())
			.size() == 1;
		
		if (ok) return lista;
		
		lista.clear();
		
		this.estado = true;
		array.stream()
			.map(x -> piezaUbicacion.sumar(new Coordenada(x,-x)))
			.filter(coor -> coor != null )
			.filter(coor -> this.celdaOk(tablero, coor, pieza.getColor() ) )
			.forEach(coor -> lista.add(coor));
		
		
		ok = lista.stream()
				.filter(coor -> this.piezaEnemiga(tablero, coor))
				.collect(Collectors.toList())
				.size() == 1;
			
		if (ok) return lista;
			
		lista.clear();
		
		
		this.estado = true;
		array.stream()
			.map(x -> piezaUbicacion.sumar(new Coordenada(-x,x)))
			.filter(coor -> coor != null )
			.filter(coor -> this.celdaOk(tablero, coor, pieza.getColor() ) )
			.forEach(coor -> lista.add(coor));
		
		
		ok = lista.stream()
				.filter(coor -> this.piezaEnemiga(tablero, coor))
				.collect(Collectors.toList())
				.size() == 1;
			
		if (ok) return lista;
			
		lista.clear();
		
		this.estado = true;
		array.stream()
			.map(x -> piezaUbicacion.sumar(new Coordenada(-x,-x)))
			.filter(coor -> coor != null )
			.filter(coor -> this.celdaOk(tablero, coor, pieza.getColor() ) )
			.forEach(coor -> lista.add(coor));
		
		ok = lista.stream()
				.filter(coor -> this.piezaEnemiga(tablero, coor))
				.collect(Collectors.toList())
				.size() == 1;
			
		if (ok) return lista;
		
		lista.clear();
		
		/********/
	
		this.estado = true;
		array.stream()
			.map(x -> piezaUbicacion.sumar(new Coordenada(0,x)))
			.filter(coor -> coor != null )
			.filter(coor -> this.celdaOk(tablero, coor, pieza.getColor() ) )
			.forEach(coor -> lista.add(coor));
		
		ok = lista.stream()
				.filter(coor -> this.piezaEnemiga(tablero, coor))
				.collect(Collectors.toList())
				.size() == 1;
			
		if (ok) return lista;
		
		lista.clear();
		
		this.estado = true;
		array.stream()
			.map(x -> piezaUbicacion.sumar(new Coordenada(0,-x)))
			.filter(coor -> coor != null )
			.filter(coor -> this.celdaOk(tablero, coor, pieza.getColor() ) )
			.forEach(coor -> lista.add(coor));
		
		ok = lista.stream()
				.filter(coor -> this.piezaEnemiga(tablero, coor))
				.collect(Collectors.toList())
				.size() == 1;
			
		if (ok) return lista;
		
		lista.clear();
		
		this.estado = true;
		array.stream()
			.map(x -> piezaUbicacion.sumar(new Coordenada(x,0)))
			.filter(coor -> coor != null )
			.filter(coor -> this.celdaOk(tablero, coor, pieza.getColor() ) )
			.forEach(coor -> lista.add(coor));
		
		ok = lista.stream()
				.filter(coor -> this.piezaEnemiga(tablero, coor))
				.collect(Collectors.toList())
				.size() == 1;
			
		if (ok) return lista;
		
		lista.clear();
		
		this.estado = true;
		array.stream()
			.map(x -> piezaUbicacion.sumar(new Coordenada(-x,0)))
			.filter(coor -> coor != null )
			.filter(coor -> this.celdaOk(tablero, coor, pieza.getColor() ) )
			.forEach(coor -> lista.add(coor));
		
		ok = lista.stream()
				.filter(coor -> this.piezaEnemiga(tablero, coor))
				.collect(Collectors.toList())
				.size() == 1;
			
		if (ok) return lista;
		
		lista.clear();
		
		return lista;
		
	}
	
	private boolean piezaEnemiga(Map<Pieza, Coordenada> tablero, Coordenada coor) {
		// TODO Auto-generated method stub
		return tablero.containsValue(coor);
		
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
	
	private void pushOK() {
		this.ok = true;
	}
	
	private boolean getOk() {
		return this.ok;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}
}
