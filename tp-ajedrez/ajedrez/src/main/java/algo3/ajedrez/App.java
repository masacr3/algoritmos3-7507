package algo3.ajedrez;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

	@Override
    public void start(Stage stage) {
    	
		var modelo = new AjedrezModel();
		var vista = new AjedrezView(stage, modelo);
		var controlador = new AjedrezController(modelo,vista);
		controlador.init(this);
    }

    public static void main(String[] args) {
        launch();
    }
    
    
}