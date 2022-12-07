package algo3.ajedrez;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AjedrezModel {
	
	private FabricaDePiezas fabricaPiezasBlancas, fabricaPiezasNegras;
	private String[] piezasMayores = {"torre","caballo","alfil","reyna","rey","alfil","caballo","torre"};
	private Tablero tablero;
	private Juego juego;
	
	
	public AjedrezModel() {
		this.tablero = new Tablero();
		this.fabricaPiezasBlancas = new FabricaDePiezasBlancas();
		this.fabricaPiezasNegras = new FabricaDePiezasNegras();
		this.juego = new Juego(this.tablero);
	}
	
	public String quienJuega() {
		return juego.quienTieneQueJugar();
	}
	
	public void refresh(boolean jaque) {
		this.juego.refresh(jaque);
	}
	
	public ArrayList<Coordenada> movimientosPieza(Coordenada pos){
		return this.juego.movimientos(pos);
	}
	
	public boolean seleccionoPieza(int i, int j) {
		var pos = new Coordenada(i,j);
		return this.tablero.contains(pos);
	}

	public void agregarPieza(String nombrePieza, String color, int i, int j) {
		var pos = new Coordenada(i,j);
		var pieza = color == "blanco" ? this.fabricaPiezasBlancas.crearPieza(nombrePieza) : this.fabricaPiezasNegras.crearPieza(nombrePieza);
		this.tablero.insertarPieza(pos, pieza);
	}
	
	public List<String> getPiezasMayores() {
		return Arrays.asList(this.piezasMayores);
	}

	public void moverPieza(Coordenada inicio, Coordenada fin) {
		var pieza = this.tablero.getPieza(inicio);
		this.tablero.moverPieza(pieza,inicio, fin);
	}
	
	public Pieza getPieza(Coordenada pos) {
		return this.tablero.getPieza(pos);
	}
	
	public Coordenada getPosicion(Pieza pieza) {
		return this.tablero.getCoordenada(pieza);
	}

	public Map<Pieza, Coordenada> getTablero() {
		return this.tablero.getTablero();
	}

	public Tablero getTableroCompleto() {
		return this.tablero;
	}
	
	public int getEtapa() {
		return this.tablero.getEtapa();
	}

	public Pieza getReyContrario(String color) {
		Pieza pieza = null;
		for( Pieza p: this.tablero.getTablero().keySet()) {
			var condicion = p.getColor() != color;
			if ( p.esRey() &&  condicion ) {
				pieza = p;
				break;
			}
		}
		return pieza;
	}
	
	public boolean esJaqueMate() {
		var color = this.juego.getColorAnterior();
		return this.estaEnJaque( color ) && juego.esMate();
	}

	public boolean estaEnJaque(String color) {
		var pieza = (Rey) this.getReyContrario(color );
		return pieza.estaEnJaque(this.getTablero(), this.getEtapa());
	}

	public void ponerReyJaque(String color , boolean jaque) {
		// TODO Auto-generated method stub
		var pieza = (Rey) this.getReyContrario(color);
		pieza.setEnJaque(jaque);
	}

	public String mensajeJaqueMate() {
		// TODO Auto-generated method stub
		return "JAQUE MATE ganaron las piezas " + this.juego.getColorGanador();
	}

	public String mensajeJaque() {
		// TODO Auto-generated method stub
		return "Jaque,  " ;
	}

	public String tituloAPP() {
		// TODO Auto-generated method stub
		return "ajedrez";
	}

}
