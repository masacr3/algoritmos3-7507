package algo3.ajedrez;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Juego {
	private Tablero tablero;
	private final int CANT_JUGADORES = 2;
	private String colores[] = {"Juegan las negras", "Juegan las blancas"};
	private String color[] = {"negro","blanco"};
	private Map<Pieza, ArrayList<Coordenada>> atributosJugador;
	
	public Juego(Tablero tablero) {
		this.tablero = tablero;
		this.refresh(false);
	}
	
	public void refresh(boolean jaque) {
		this.atributosJugador = new HashMap<>();
		var listaPiezas = tablero.getAllPiezas();
		listaPiezas.forEach(p -> {
			var lista = tablero.movimientosPieza(p);
			if (p.esRey()) {
				var simulacion = new Simular(p, tablero);
				lista = simulacion.casillasHabilitadas(lista);
				this.atributosJugador.put(p, lista);
			}
			else if (!jaque) {
				var simulacion = new Simular(p, tablero);
				if (simulacion.esJaqueDescubierto()) lista.removeAll(lista);
				this.atributosJugador.put(p, lista);
			}
			else {
				var color = this.getColorAnterior();
				var rey = this.tablero.getReyContrario(color);
				var rr = new MovimientosRadialesAtaque();
				var listaFiltrada = rr.comoMoverme(rey, this.tablero.getTablero(), this.tablero.getEtapa());
				var listafinal = new ArrayList<Coordenada>();
				
				lista.stream()
					.filter(coor -> listaFiltrada.contains(coor))
					.forEach(coor -> listafinal.add(coor));
				this.atributosJugador.put(p, listafinal);
			}
		});
	}
	
	public boolean turnoDelJugador(Coordenada pos) {
		var pieza = tablero.getPieza(pos);
		if ( pieza == null) return false;
		return this.atributosJugador.containsKey(pieza);
	}
	
	public ArrayList<Coordenada> movimientos(Coordenada pos){
		if ( !this.turnoDelJugador(pos)) return new ArrayList<Coordenada>();
		var pieza = tablero.getPieza(pos);
		return this.atributosJugador.get(pieza);
	}
	
	public String quienTieneQueJugar() {
		return colores[tablero.getEtapa() % this.CANT_JUGADORES];
	}
	
	public boolean esMate() {
		var cont = 0;
		for ( Map.Entry<Pieza, ArrayList<Coordenada>> item : this.atributosJugador.entrySet()   ) {
			cont += item.getValue().size();
		}
		return cont == 0;
	}
	
	public String getColor() {
		return color[tablero.getEtapa() % this.CANT_JUGADORES];
	}

	public String getColorGanador() {
		// TODO Auto-generated method stub
		return color[(tablero.getEtapa()+1) % this.CANT_JUGADORES].replace("o", "as");
	}
	
	public String getColorAnterior() {
		return color[(tablero.getEtapa()+1) % this.CANT_JUGADORES];
	}
	
}
