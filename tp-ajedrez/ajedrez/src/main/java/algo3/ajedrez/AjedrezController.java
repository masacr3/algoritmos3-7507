package algo3.ajedrez;

import java.util.ArrayList;

public class AjedrezController {
	private AjedrezModel modelo;
	private AjedrezView vista;
	private int iter;
	private boolean estaSeleccionado;
	private boolean jaque;
	private Coordenada seleccionAnterior;
	private boolean jaquemate;

	public AjedrezController(AjedrezModel modelo, AjedrezView vista) {
		this.modelo = modelo;
		this.vista = vista;
		this.jaquemate = false;
	}

	/* main */
	private void capaNegocio(int i, int j) {
		this.modelo.refresh(jaque);
		if (!this.estaSeleccionado && this.modelo.seleccionoPieza(i, j)) {
			if (!this.jaquemate)
				this.vista.intensificarBaldosa(new Coordenada(i, j), false);
			this.mostrarMovimientoDeLaPieza(new Coordenada(i, j));
			this.estaSeleccionado = true;
			this.seleccionAnterior = new Coordenada(i, j);
			return;
		}

		if (this.estaSeleccionado
				&& (!this.esRey(this.seleccionAnterior)
						|| (this.esRey(seleccionAnterior) && !this.enroco(seleccionAnterior, new Coordenada(i, j))))
				&& this.eligioMover(this.seleccionAnterior, new Coordenada(i, j))) {
			this.pintarDefaulTablero();
			if (this.modelo.seleccionoPieza(i, j)) {
				this.vista.eliminarPiezaVisual(i, j);
			}
			this.moverPieza(seleccionAnterior, new Coordenada(i, j));
			this.seleccionAnterior = null;
			this.estaSeleccionado = false;

		} else if (this.estaSeleccionado && this.esRey(seleccionAnterior)
				&& this.enroco(this.seleccionAnterior, new Coordenada(i, j))) {
			this.pintarDefaulTablero();
			this.moverPiezaEnroque(seleccionAnterior, new Coordenada(i, j));
			this.estaSeleccionado = false;
		} else {
			this.pintarDefaulTablero();
			this.estaSeleccionado = false;
		}
		this.vista.setMensaje(this.modelo.quienJuega());
		this.verificarJaque();
	}

	/* estructuras de control */

	public ArrayList<Coordenada> mostrarMovimientosPieza(Coordenada pos, boolean jaque) {
		return this.modelo.movimientosPieza(pos);
	}

	public void agregarPieza(String nombrePieza, String color, int i, int j) {
		this.modelo.agregarPieza(nombrePieza, color, i, j);
		this.vista.agregarPiezaVisual(nombrePieza, color, i, j);
	}

	public boolean esRey(Coordenada coordenada) {
		var pieza = this.modelo.getPieza(coordenada);
		return pieza == null ? false : pieza.esRey();
	}

	public boolean enroco(Coordenada inicio, Coordenada fin) {
		var pieza = (Rey) this.modelo.getPieza(inicio);
		return pieza.enroco(this.modelo.getTablero(), fin, this.modelo.getEtapa());
	}

	public boolean Jaque(String color) {
		return this.modelo.estaEnJaque(color);
	}

	public void init(App app) {
		this.pintarTablero();
		this.agregoPiezasAlTablero();
		this.vista.agregarEscuchaEmpezar(e -> {
			this.vista.disabledCreditos();
		});

		this.vista.agregarEscuchaSalir(e -> {
			app.start(vista.getStage());
		});
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				var i = x;
				var j = y;
				this.vista.agregarEventoMouse(i, j, e -> {
					this.capaNegocio(i, j);
				});
			}
		}

	}

	public boolean eligioMover(Coordenada anterior, Coordenada actual) {
		return this.mostrarMovimientosPieza(anterior, this.jaque).contains(actual);
	}

	private void verificarJaque() {
		var ok1 = this.Jaque("blanco");
		var ok2 = this.Jaque("negro");

		this.pintarReyJaque("blanco", ok1);
		this.pintarReyJaque("negro", ok2);

		// aviso del jaque al modelo para cuando tenga q refrescar no tenga encuenta el
		// enroque
		this.modelo.ponerReyJaque("blanco", ok1);
		this.modelo.ponerReyJaque("negro", ok2);

		this.jaque = ok1 || ok2;

		// actualizamos para calcular los mov posibles preparando el scenario para el
		// jaquemate
		if (this.jaque) {
			this.modelo.refresh(jaque);
		}

		if (this.modelo.esJaqueMate()) {
			this.vista.setMensaje(this.modelo.mensajeJaqueMate());
			this.jaquemate = true;
			// this.vista.enabledCreditos();
			this.vista.habilitarSalir();
		} else if (ok1 || ok2) {
			this.vista.setMensaje(this.modelo.mensajeJaque() + this.modelo.quienJuega());
		}
	}

	/* modifica la vista */

	public void moverPiezaEnroque(Coordenada inicio, Coordenada fin) {
		var inicioAnterior = inicio.copiar();
		var finAnterior = fin.copiar();
		this.modelo.moverPieza(inicio, fin);
		this.vista.moverPiezaEnroque(inicioAnterior, finAnterior, inicio, fin);
	}

	public void moverPieza(Coordenada inicio, Coordenada fin) {
		this.modelo.moverPieza(inicio, fin);
		this.vista.moverPiezaTablero(inicio, fin);
	}

	public void pintarReyJaque(String color, boolean pintar) {
		var pieza = this.modelo.getReyContrario(color);
		var coor = this.modelo.getPosicion(pieza);
		if (pintar) {
			this.vista.intensificarBaldosa(coor, true);
		} else {
			this.vista.pintarBaldosa(coor);
		}

	}

	public void mostrarMovimientoDeLaPieza(Coordenada pos) {
		this.mostrarMovimientosPieza(pos, this.jaque).forEach(coor -> {
			this.vista.intensificarBaldosa(coor);
		});
	}

	private void agregoPiezasAlTablero() {
		for (int i = 0; i < 8; i++) {
			this.agregarPieza("peon", "negro", i, 1);
			this.agregarPieza("peon", "blanco", i, 6);
			this.vista.insertarPiezaEnELTablero(i, 1);
			this.vista.insertarPiezaEnELTablero(i, 6);
		}

		var piezasMayores = this.modelo.getPiezasMayores();

		this.restarIter();
		piezasMayores.forEach(pieza -> {
			var i = this.getIter();
			this.agregarPieza(pieza, "negro", i, 0);
			this.agregarPieza(pieza, "blanco", i, 7);
			this.vista.insertarPiezaEnELTablero(i, 0);
			this.vista.insertarPiezaEnELTablero(i, 7);
			this.incrementarIter();
		});

	}

	private void pintarTablero() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.vista.insertarBaldosaEnElTablero(i, j);
			}
		}
	}

	public void pintarDefaulTablero() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.vista.pintarBaldosa(new Coordenada(i, j));
			}
		}
	}
	/* control de iteracion */

	private int getIter() {
		return this.iter;
	}

	private void restarIter() {
		this.iter = 0;

	}

	private void incrementarIter() {
		this.iter++;
	}

}
