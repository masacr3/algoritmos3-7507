package algo3.ajedrez;

import java.util.HashMap;
import java.util.Map;

public abstract class Pieza{
	private String color;
	private String name;
	private Map<String,MovimientosStrategy> estrategias;
	private boolean estaEnJaque;
	private boolean ok;
	protected boolean movio;
	
	public Pieza( String name, String color, MovimientosStrategy estrategia) {
		this.name = name;
		this.color = color;
		this.estrategias = new HashMap<>();
		this.setStrategy(estrategia);
		this.movio = false;
		this.estaEnJaque = false;
	}

	public String getColor() {
		return this.color;
	}
	
	public void setStrategy(MovimientosStrategy estrategia) {
		if ( !estrategias.containsKey(estrategia.getNombre())) estrategias.put(estrategia.getNombre(), estrategia);
	}
	
	public String getName() {
		return this.name;
	}
	
	public Map<String,MovimientosStrategy> getStrategy() {
		if ( !this.esRey() || ( this.esRey() && !this.estaEnJaque) ) return this.estrategias;
		else {
			var estrategiaSinEnroque = new HashMap<String, MovimientosStrategy>();
			
			for (Map.Entry<String, MovimientosStrategy> item : this.estrategias.entrySet()) {
				if ( item.getKey() != "enroque") {
					estrategiaSinEnroque.put(item.getKey(), item.getValue());
				}
			}
			return estrategiaSinEnroque;
		}
	}
	
	public boolean esBlanco() {
		return this.color == "blanco";
	}
	
	public boolean esPeon() {
		return this.name == "peon";
	}
	
	public boolean es(String tipoPieza) {
		return this.name == tipoPieza;
	}
	
	public boolean esAlfil() {
		return this.name == "alfil";
	}
	
	public boolean esCaballo() {
		return this.name == "caballo";
	}
	
	public boolean esRey() {
		return this.name == "rey";
	}
	
	public boolean esReyna() {
		return this.name == "reyna";
	}
	
	public boolean esTorre() {
		return this.name == "torre";
	}
	
	boolean ejecutarEstrategia(Map<Coordenada, Pieza> miTablero, Map<Pieza, Coordenada> miTableropos, Coordenada inicio, Coordenada fin, int etapa) {
		
		this.pushFalse();
		
		this.getStrategy().forEach((_n, estrategia) ->{
			if( estrategia.comoMoverme(this, miTableropos, etapa).contains(fin)) this.pushOk();
		});
		
		if( !this.getOk() ) {
			return false;
		}
		
		//eliminamos pos anterior
		miTablero.remove(inicio);
		miTableropos.remove(this);
		
		if(miTablero.containsKey(fin)) {
			var elem = miTablero.remove(fin);
			miTableropos.remove(elem);
		}
		
		//actualizamos nueva pos
		miTablero.put(fin, this);
		miTableropos.put(this, fin);
		
		this.movio = true;
		return true;
	}

	public boolean getOk() {
		// TODO Auto-generated method stub
		return this.ok;
	}

	protected void pushOk() {
		// TODO Auto-generated method stub
		this.ok = true;
		
	}

	protected void pushFalse() {
		// TODO Auto-generated method stub
		this.ok = false;
	}

	public boolean esTorreYMovio() {
		// TODO Auto-generated method stub
		return  this.esTorre() && this.movio;
	}
	
	public void setEnJaque(boolean jaque) {
		this.estaEnJaque = jaque;
	}
	
}

