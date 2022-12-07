package algo3.ajedrez;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Rey extends Pieza {
	
	int iter;
	String piezas[] = {"caballo", "alfil", "torre", "reyna", "peon" };

	public Rey(String color) {
		super("rey", color, new MovimientoContorno());
		this.setStrategy(new MovimientosEnroque());
		// TODO Auto-generated constructor stub
	}
	
	public boolean estaEnJaque(Map<Pieza, Coordenada> miTableropos, int etapa) {
		var estrategiasDeTodasLasPiezas = Arrays.asList(new MovimientosEnL(), new MovimientosDiagonales(), new MovimientosVerticalesHorizontales(), new MovimientosDiagonalesYRectos(), new MovimientoComerPeon()); //caballo - alfil - torre - reyna - peon 
		
		this.iter = 0;
		
		return estrategiasDeTodasLasPiezas.stream()
			.map(f ->  this.checkPieza(f, miTableropos, etapa)  )
			.filter( pieza -> pieza != null )
			.collect(Collectors.toList())
			.size() > 0;
	}

	private  Pieza checkPieza(MovimientosStrategy f, Map<Pieza, Coordenada> miTableropos, int etapa) {
		var posiciones = f.comoMoverme(this, miTableropos, etapa);
		var lista = new ArrayList<Pieza>();
		miTableropos.forEach( (pieza, coor) ->{
			if( posiciones.contains(coor) && pieza.es(this.piezas[this.iter]) ) {	
				lista.add(pieza);
			}
			
		});
		this.iter ++;
		return lista.size() == 0 ? null : lista.get(0);
	}
	
	@Override
	boolean ejecutarEstrategia(Map<Coordenada, Pieza> miTablero, Map<Pieza, Coordenada> miTableropos, Coordenada inicio, Coordenada fin, int etapa) {
		
		this.pushFalse();
		
		this.getStrategy().forEach((_n, estrategia) ->{
			if( estrategia.comoMoverme(this, miTableropos, etapa).contains(fin)) this.pushOk();
		});
		
		if( !this.getOk() ) return false;
		
		if ( this.enroco(miTableropos, fin, etapa) ) {
			var torre = miTablero.remove(fin);
			var rey = miTablero.remove(inicio);
			
			miTablero.remove(inicio);
			miTableropos.remove(rey);
			miTablero.remove(fin);
			miTableropos.remove(torre);
			
			inicio.setPosicion(new Coordenada(inicio.getx() + (this.enRoqueCorto(inicio, fin) ? 2 : -2), inicio.gety()));
			fin.setPosicion(new Coordenada(fin.getx() + (this.enRoqueCorto(inicio, fin) ? -2 : 3), fin.gety()));
			
			//actualizamos nueva pos
			miTablero.put(inicio, rey);
			miTableropos.put(rey, inicio);
			//actualizamos nueva pos
			miTablero.put(fin, torre);
			miTableropos.put(torre, fin);
		}
		else {
			//eliminamos pos anterior
			miTablero.remove(inicio);
			miTableropos.remove(this);
			
			//actualizamos nueva pos
			miTablero.put(fin, this);
			miTableropos.put(this, fin);
		}
		this.movio = true;
		return true;
	}
	
	private boolean enRoqueCorto(Coordenada inicio, Coordenada fin) {
		// TODO Auto-generated method stub
		return inicio.getx() < fin.getx();
	}

	boolean enroco(Map<Pieza, Coordenada> miTableropos, Coordenada fin, int etapa) {
		var estrategia = new MovimientosEnroque();
		var lista = estrategia.comoMoverme(this, miTableropos, etapa);
		return lista.contains(fin);
	}

}
