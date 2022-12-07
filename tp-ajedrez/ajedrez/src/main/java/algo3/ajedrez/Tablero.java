package algo3.ajedrez;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tablero {

	private Map<Coordenada, Pieza> miTablero;
	private Map<Pieza, Coordenada> miTableropos;
	private int etapa;
	
	public Tablero() {
		miTablero = new HashMap<>();
		miTableropos = new HashMap<>();
		etapa = 1;
	}

	
	public boolean contains(Coordenada coordenada) {
		return this.miTablero.containsKey(coordenada);
	}
	
	public boolean contains(Pieza pieza) {
		return this.miTableropos.containsKey(pieza);
	}
	
	public Pieza getPieza (Coordenada coordenada) {
		return this.miTablero.get(coordenada);
	}
	
	public boolean insertarPieza (Coordenada coord, Pieza pieza) {
		if (!coord.estaEnLimite()) {
			return false;
		}
		this.miTablero.put(coord, pieza);
		this.miTableropos.put(pieza, coord);
		return true;
	}
	
	public Coordenada getCoordenada(Pieza piezaBuscada) {
		return this.miTableropos.get(piezaBuscada);
	}
	
	public Map<Pieza, Coordenada> getTablero(){
		return this.miTableropos;
	}
	
	public boolean moverPieza(Pieza pieza, Coordenada inicio, Coordenada destino) {
		var ok = pieza.ejecutarEstrategia(this.miTablero, this.miTableropos, inicio , destino, etapa);
		if (ok) this.etapa++;
		return ok;
	}
	
	public ArrayList<Coordenada> movimientosPieza(Pieza pieza){
		var lista = new ArrayList<Coordenada>();
		if(pieza == null) return lista;
		pieza.getStrategy().forEach((_n, estrategia) ->{
			var mov = estrategia.comoMoverme(pieza, miTableropos, etapa);
			mov.stream()
				.filter(coor -> !lista.contains(coor))
				.forEach(coor -> lista.add(coor));
				
		});
		return lista;
	}
	
	public int getEtapa() {
		return this.etapa;
	}
	
	public void setEtapa(int nuevaEtapa) {
		this.etapa = nuevaEtapa;
	}


	public int getCantidadPiezas() {
		// TODO Auto-generated method stub
		return this.miTablero.size();
	}

	public boolean removerPieza(Pieza pieza) {
		if (! this.contains(pieza) ) return false;
		Coordenada coord = this.getCoordenada(pieza);
		this.miTablero.remove(coord);
		this.miTableropos.remove(pieza);
		return true;
	}

	public Pieza getReyAliado(String color) {
		// TODO Auto-generated method stub
		Pieza pieza = null;
		for( Pieza p: miTableropos.keySet()) {
			if ( p.esRey() && p.getColor() == color ) {
				pieza = p;
				break;
			}
		}
		return pieza;
	}

	public Pieza getReyContrario(String color) {
		// TODO Auto-generated method stub
		Pieza pieza = null;
		for( Pieza p: miTableropos.keySet()) {
			if ( p.esRey() && p.getColor() != color ) {
				pieza = p;
				break;
			}
		}
		return pieza;
	}

	public Tablero copiarTablero(Tablero tablero) {
		Tablero tableroNuevo = new Tablero();
		for( Pieza p: tablero.getTablero().keySet()) {
			tableroNuevo.insertarPieza(tablero.getCoordenada(p), p);
		}
		return tableroNuevo;
	}
	
	public ArrayList<Pieza> getAllPiezas(){
		var color = this.etapa % 2 == 0? "negro" : "blanco";
		
		var lista = new ArrayList<Pieza>();
		
		for( Pieza p: miTableropos.keySet()) {
			if ( p.getColor() == color ) {
				lista.add(p);
			}
		}
		return lista;
	}
}
