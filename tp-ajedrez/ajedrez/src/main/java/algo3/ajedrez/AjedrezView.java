package algo3.ajedrez;

import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AjedrezView {
	private AjedrezModel model;

	private VBox creditos;

	private GridPane view;
	private VBox contenedorMensajes;
	private Label mensaje;
	private Label jaque;
	private int jugador;
	private Stage stage;
	private BorderPane root;
	private Map<Coordenada, Rectangle> baldosas;
	private Paint color1, color2, color3, color4, color5;
	private Map<Coordenada, ImageView> piezasVisuales;
	private Button empezar;
	private Button salir;
	private boolean creditosOn;

	public AjedrezView(Stage stage, AjedrezModel model) {
		this.baldosas = new HashMap<Coordenada, Rectangle>();
		this.color1 = javafx.scene.paint.Color.DIMGREY;
		this.color2 = javafx.scene.paint.Color.GAINSBORO;
		this.color3 = javafx.scene.paint.Color.GREENYELLOW;
		this.color4 = javafx.scene.paint.Color.DEEPPINK;
		this.color5 = javafx.scene.paint.Color.DARKSEAGREEN;

		this.piezasVisuales = new HashMap<Coordenada, ImageView>();
		this.creditosOn = true;
		this.stage = stage;
		this.model = model;
		this.crearYConfigurarGrid();
		Scene scene = new Scene(asParent(), 565, 670);
		stage.setScene(scene);
		stage.setTitle(this.model.tituloAPP());
		// stage.setResizable(false);
		stage.show();
	}

	public Stage getStage() {
		return stage;
	}

	public void agregarPiezaVisual(String nombrePieza, String color, int i, int j) {
		var iv = new ImageView(getClass().getResource(nombrePieza + color + ".png").toExternalForm());
		iv.setFitHeight(64);
		iv.setFitWidth(64);
		this.piezasVisuales.put(new Coordenada(i, j), iv);
	}

	public ImageView getPiezaVisual(int i, int j) {
		return this.piezasVisuales.get(new Coordenada(i, j));
	}

	public void moverPiezaVisual(Coordenada inicio, Coordenada fin) {
		var piezaVisual = this.piezasVisuales.remove(inicio);
		this.piezasVisuales.put(fin, piezaVisual);
	}

	public void agregarBaldosa(Coordenada pos) {
		var r = new Rectangle(64, 64);
		r.setStyle("-fx-arc-height: 10; -fx-arc-width: 10;");
		this.baldosas.put(pos, r);
		this.pintarBaldosa(pos);
	}

	public void pintarBaldosa(Coordenada pos) {
		var i = pos.getx();
		var j = pos.gety();
		var r = this.baldosas.get(pos);
		r.setFill((i + j) % 2 != 0 ? color1 : color2);
	}

	public void intensificarBaldosa(Coordenada pos) {
		var r = this.baldosas.get(pos);
		r.setFill(color3);
	}

	public void intensificarBaldosa(Coordenada pos, boolean nan) {
		var r = this.baldosas.get(pos);
		r.setFill(nan ? color4 : color5);
	}

	@SuppressWarnings("exports")
	public Rectangle getBaldosa(Coordenada pos) {
		return this.baldosas.get(pos);
	}

	public void setMensaje(String mensaje) {
		this.mensaje.setText(mensaje);
	}

	private void crearYConfigurarGrid() {

		this.creditos = new VBox(10);
		var iv = new ImageView(getClass().getResource("ajedrez.jpg").toExternalForm());
		iv.setFitHeight(600);
		iv.setFitWidth(565);

		this.empezar = new Button("Comenzar");
		this.empezar.setStyle("-fx-font-size: 16px;");
		this.salir = new Button("Reiniciar el Juego");
		this.salir.setStyle("-fx-font-size: 13px;");

		this.creditos.getChildren().add(iv);
		this.creditos.getChildren().add(this.empezar);

		this.root = new BorderPane();
		this.view = new GridPane();
		this.mensaje = new Label(this.model.quienJuega());
		this.contenedorMensajes = new VBox();

		this.view.setStyle("-fx-background-color: #2C3E50;");
		this.view.setPadding(new Insets(10, 10, 10, 10));
		this.view.setVgap(5);
		this.view.setHgap(5);

		this.contenedorMensajes.setStyle("-fx-background-color: #434242;");
		this.contenedorMensajes.setPadding(new Insets(10, 10, 10, 10));

		this.contenedorMensajes.getChildren().add(mensaje);
		this.contenedorMensajes.getChildren().add(this.salir);
		// this.mensaje.setTextFill(Color.color(1, 0, 0));
		this.contenedorMensajes.setStyle("-fx-background-color: #434242;-fx-alignment:center;");
		this.mensaje.setStyle("-fx-text-fill: #F3EFE0; -fx-font-size: 16px;");
		this.creditos.setStyle("-fx-alignment:center;");

		// this.view.setStyle("-fx-pref-width:0");

		this.root.setLeft(this.view);
		this.root.setCenter(this.creditos);
		this.root.setBottom(this.contenedorMensajes);

		this.root.getBottom().setVisible(false);
		this.root.getBottom().setManaged(false);
		this.root.getLeft().setVisible(false);
		this.root.getLeft().setManaged(false);
		this.salir.setVisible(false);
		this.salir.setManaged(false);

	}

	public boolean getEstadoCredito() {
		return this.creditosOn;
	}

	public void setEstadoCredito(boolean estado) {
		this.creditosOn = estado;
	}

	public void agregarEventoMouse(int i, int j, EventHandler<MouseEvent> evm) {
		var pane = new Pane();
		pane.setOnMouseClicked(evm);
		this.view.add(pane, i, j);
	}

	public void disabledCreditos() {
		this.root.getLeft().setVisible(true); // aje
		this.root.getLeft().setManaged(true); // aje
		this.salir.setVisible(false); // salir
		this.salir.setManaged(false); // salir
		this.empezar.setVisible(false); // emp
		this.empezar.setManaged(false); // emp
		this.root.getCenter().setVisible(false);
		this.root.getCenter().setManaged(false);
		this.root.getBottom().setVisible(true);
		this.root.getBottom().setManaged(true);
	}

	public Parent asParent() {
		return this.root;
	}

	public GridPane getView() {
		return this.view;
	}

	public void agregarEscuchaEmpezar(EventHandler<ActionEvent> ahe) {
		this.empezar.setOnAction(ahe);
	}

	public void habilitarSalir() {
		this.salir.setVisible(true);
		this.salir.setManaged(true);
	}

	public void agregarEscuchaSalir(EventHandler<ActionEvent> ahe) {
		this.salir.setOnAction(ahe);
	}

	public void eliminarPiezaVisual(int i, int j) {
		var imagenPieza = this.getPiezaVisual(i, j);
		this.view.getChildren().remove(imagenPieza);

	}

	public void moverPiezaTablero(Coordenada inicio, Coordenada fin) {
		var x = fin.getx();
		var y = fin.gety();
		var i = inicio.getx();
		var j = inicio.gety();
		var imagenPieza = this.getPiezaVisual(i, j);
		this.view.setRowIndex(imagenPieza, y);
		this.view.setColumnIndex(imagenPieza, x);
		this.moverPiezaVisual(inicio, fin);
	}

	public void insertarPiezaEnELTablero(int i, int j) {
		var pieza = this.getPiezaVisual(i, j);
		this.view.add(pieza, i, j);
	}

	public void moverPiezaEnroque(Coordenada inicioAnterior, Coordenada finAnterior, Coordenada inicio,
			Coordenada fin) {
		var torre = this.getPiezaVisual(finAnterior.getx(), finAnterior.gety());
		var rey = this.getPiezaVisual(inicioAnterior.getx(), inicioAnterior.gety());

		this.view.setRowIndex(torre, fin.gety());
		this.view.setColumnIndex(torre, fin.getx());
		this.moverPiezaVisual(inicioAnterior, inicio);

		this.view.setRowIndex(rey, inicio.gety());
		this.view.setColumnIndex(rey, inicio.getx());
		this.moverPiezaVisual(finAnterior, fin);

	}

	public void insertarBaldosaEnElTablero(int i, int j) {
		this.agregarBaldosa(new Coordenada(i, j));
		var baldosa = this.getBaldosa(new Coordenada(i, j));
		this.getView().add(baldosa, i, j);

	}

}
