package algo3.ajedrez;

import java.util.Map;

public class Peon extends Pieza {
	
	boolean pasoDoble;
	int etapaPeon;
	private boolean ok;

	public Peon(String color) {
		super("peon", color, new MovimientosPeon());
		// TODO Auto-generated constructor stub
		this.pasoDoble = true;
		this.etapaPeon = 0;
	}

	@Override
	boolean ejecutarEstrategia(Map<Coordenada, Pieza> miTablero, Map<Pieza, Coordenada> miTableropos, Coordenada inicio,
			Coordenada fin, int etapa) {
		
		this.pushFalse();
		
		var finn = fin; // lo puse asi por q se queja q fin tiene q ser final ?? no se por q
		
		this.getStrategy().forEach((_n, estrategia) ->{
			if( estrategia.comoMoverme(this, miTableropos, etapa).contains(finn)) this.pushOk();
		});
		
		if( !this.getOk() ) return false;
		
		if ( this.comioAlpaso(miTableropos, fin, etapa) ) {
			var pieza = miTablero.remove(fin);
			miTableropos.remove(pieza);
			var x = fin.getx();
			var y = fin.gety() + (this.esBlanco() ? -1 : 1);
			fin.setPosicion(new Coordenada(x, y));
		}
		miTablero.remove(inicio);
		miTableropos.remove(this);
		
		if(miTablero.containsKey(fin)) {
			var elem = miTablero.remove(fin);
			miTableropos.remove(elem);
		}
		
		miTablero.put(fin, this);
		miTableropos.put(this, fin);
		
		this.etapaPeon = etapa;
		if ( Math.abs( inicio.gety() - fin.gety() ) == 2) {
			this.pasoDobleOn();
		}
		else {
			this.pasoDobleOff();
		}
		
		return true;
	}

	private boolean comioAlpaso(Map<Pieza, Coordenada> miTableropos, Coordenada fin, int etapa) {
		var estrategia = new MovimientoComerAlPaso();
		var lista = estrategia.comoMoverme(this, miTableropos, etapa);
		return lista.contains(fin);
	}

	public boolean PuedeMoverDoble() {
		return this.etapaPeon == 0;
	}
	
	public int getEtapa() {
		return this.etapaPeon;
	}
	
	public void setEtapa(int etapa) {
		this.etapaPeon = etapa;
	}
	
	public boolean movioDoble() {
		return this.pasoDoble;
	}
	
	public void pasoDobleOff() {
		this.pasoDoble = false;
	}
	
	public void pasoDobleOn() {
		this.pasoDoble = true;
	}
}
