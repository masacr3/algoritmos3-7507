package ajedrez;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import algo3.ajedrez.Alfil;
import algo3.ajedrez.Caballo;
import algo3.ajedrez.Coordenada;
import algo3.ajedrez.Peon;
import algo3.ajedrez.Pieza;
import algo3.ajedrez.Rey;
import algo3.ajedrez.Reyna;
import algo3.ajedrez.Tablero;
import algo3.ajedrez.Torre;

public class TestJaque {
    
       
    @Test
    /*
	 * 		 TABLERO
	 * 
	 *  7               
	 *  6 
	 *  5        
	 *  4          
	 *  3       B   
	 *  2          
	 *  1          
	 *  0       
	 *    0 1 2 3 4 5 6 7
	*/
    public void testNoEstaEnJaque() {
        Tablero tablero = new Tablero();
        var rey = new Rey("blanco");

        Coordenada coordRey = new Coordenada(3, 3);

        tablero.insertarPieza(coordRey, rey);

		assertFalse( rey.estaEnJaque(tablero.getTablero(), tablero.getEtapa() ));
    }
 
    
    @Test
    /*
	 * 		 TABLERO
	 * 
	 *  7               
	 *  6 
	 *  5     x   x
	 *  4   x       x
	 *  3       B   
	 *  2   x       x
	 *  1     x   B  
	 *  0       
	 *    0 1 2 3 4 5 6 7
	 */
    
    public void testComprobarConCaballoAliado() {
        Tablero tablero = new Tablero();
        Rey reyBlanco = new Rey("blanco");
        Caballo caballoBlanco = new Caballo("blanco");

        Coordenada coordRey = new Coordenada(3, 3);
        Coordenada coordCaballo = new Coordenada(4, 5);

        tablero.insertarPieza(coordRey, reyBlanco);
        tablero.insertarPieza(coordCaballo, caballoBlanco);

        assertFalse( reyBlanco.estaEnJaque(tablero.getTablero(), tablero.getEtapa() ));
    }

    
    @Test
    /*
	 * 		 TABLERO
	 * 
	 *  7               
	 *  6 
	 *  5     x   N
	 *  4   x       x
	 *  3       B   
	 *  2   x       x
	 *  1     x   x  
	 *  0       
	 *    0 1 2 3 4 5 6 7
	 */
    
    public void testComprobarCaballoEnemigo() {
        Tablero tablero = new Tablero();
        Rey reyBlanco = new Rey("blanco");
        Caballo caballoNegro = new Caballo("negro");

        Coordenada coordRey = new Coordenada(3, 3);
        Coordenada coordCaballo = new Coordenada(4, 5);

        tablero.insertarPieza(coordRey, reyBlanco);
        tablero.insertarPieza(coordCaballo, caballoNegro);

        assertTrue( reyBlanco.estaEnJaque(tablero.getTablero(), tablero.getEtapa() ));
    }
    
    
    @Test
	/*
	 * 		 TABLERO
	 * 
	 *  7               x
	 *  6 x           x
	 *  5   x       x
	 *  4     x   x 
	 *  3       B 
	 *  2     x   x
	 *  1   x       B
	 *  0 x           
	 *    0 1 2 3 4 5 6 7
	 */
    
    public void testComprobarAlfilAliado() {
    	Tablero tablero = new Tablero();
        Rey reyBlanco = new Rey("blanco");
        Alfil alfilBlanco = new Alfil("blanco");

        Coordenada coordRey = new Coordenada(3, 3);
        Coordenada coordAlfil = new Coordenada(5,1);

        tablero.insertarPieza(coordRey, reyBlanco);
        tablero.insertarPieza(coordAlfil, alfilBlanco);

        assertFalse( reyBlanco.estaEnJaque(tablero.getTablero(), tablero.getEtapa() ));
    }
    
    
    @Test
	/*
	 * 		 TABLERO
	 * 
	 *  7               x
	 *  6 x           x
	 *  5   x       x
	 *  4     x   x 
	 *  3       B 
	 *  2     x   x
	 *  1   x       A
	 *  0 x           
	 *    0 1 2 3 4 5 6 7
	 */
    
    public void testComprobarAlfilEnemigo() {
    	Tablero tablero = new Tablero();
        Rey reyBlanco = new Rey("blanco");
        Alfil alfilNegro = new Alfil("negro");

        Coordenada coordRey = new Coordenada(3, 3);
        Coordenada coordAlfil = new Coordenada(5,1);

        tablero.insertarPieza(coordRey, reyBlanco);
        tablero.insertarPieza(coordAlfil, alfilNegro);

        assertTrue( reyBlanco.estaEnJaque(tablero.getTablero(), tablero.getEtapa() ));
    }
    
    
    @Test
	/*
	 * 		 TABLERO
	 * 
	 *  7       x
	 *  6       x
	 *  5       x
	 *  4       x
	 *  3 x x x B x x B
	 *  2       x
	 *  1       x
	 *  0       x
	 *    0 1 2 3 4 5 6 7
	 */
    
    public void testComprobarTorreAliado() {
    	Tablero tablero = new Tablero();
        Rey reyBlanco = new Rey("blanco");
        Torre torreBlanca = new Torre("blanco");

        Coordenada coordRey = new Coordenada(3, 3);
        Coordenada coordtorre = new Coordenada(6,3);

        tablero.insertarPieza(coordRey, reyBlanco);
        tablero.insertarPieza(coordtorre, torreBlanca);

        assertFalse( reyBlanco.estaEnJaque(tablero.getTablero(), tablero.getEtapa() ));
    }
    
    
    @Test
	/*
	 * 		 TABLERO
	 * 
	 *  7       x
	 *  6       x
	 *  5       x
	 *  4       x
	 *  3 x x x B x x T
	 *  2       x
	 *  1       x
	 *  0       x
	 *    0 1 2 3 4 5 6 7
	 */
    
    public void testComprobarTorreEnemigo() {
    	Tablero tablero = new Tablero();
        Rey reyBlanco = new Rey("blanco");
        Torre torreNegro = new Torre("negro");

        Coordenada coordRey = new Coordenada(3, 3);
        Coordenada coordtorre = new Coordenada(6,3);

        tablero.insertarPieza(coordRey, reyBlanco);
        tablero.insertarPieza(coordtorre, torreNegro);

        assertTrue( reyBlanco.estaEnJaque(tablero.getTablero(), tablero.getEtapa() ));
    }
    
    
    @Test
	/*
	 * 		 TABLERO
	 *  7       x       x
	 *  6 x     x     x  
	 *  5   x   x   x
	 *  4     x x x
	 *  3 x x x b x x x x
	 *  2     x x x  
	 *  1   D   x   x
	 *  0       x     x
	 *    0 1 2 3 4 5 6 7
	 */
    
    public void testComprobarReynaEnemigo() {
    	Tablero tablero = new Tablero();
        Rey reyBlanco = new Rey("blanco");
        Reyna reynaNegro = new Reyna("negro");

        Coordenada coordRey = new Coordenada(3, 3);
        Coordenada coordreyna = new Coordenada(1,1);

        tablero.insertarPieza(coordRey, reyBlanco);
        tablero.insertarPieza(coordreyna, reynaNegro);

        assertTrue( reyBlanco.estaEnJaque(tablero.getTablero(), tablero.getEtapa() ));
    }
    
    
    @Test
	/*
	 * 		 TABLERO
	 *  7       x       x
	 *  6 x     x     x  
	 *  5   x   x   x
	 *  4     x x x
	 *  3 x x x b x x x x
	 *  2     x x x  
	 *  1   b   x   x
	 *  0       x     x
	 *    0 1 2 3 4 5 6 7
	 */
    
    public void testComprobarReynaAliada() {
    	Tablero tablero = new Tablero();
        Rey reyBlanco = new Rey("blanco");
        Reyna reynaBlanca = new Reyna("blanco");

        Coordenada coordRey = new Coordenada(3, 3);
        Coordenada coordreyna = new Coordenada(1,1);

        tablero.insertarPieza(coordRey, reyBlanco);
        tablero.insertarPieza(coordreyna, reynaBlanca);

        assertFalse( reyBlanco.estaEnJaque(tablero.getTablero(), tablero.getEtapa() ));
    }
    
    
    @Test
	/*
	 * 		 TABLERO
	 *  7       
	 *  6   
	 *  5   
	 *  4     
	 *  3 
	 *  2      
	 *  1     x  
	 *  0       n
	 *    0 1 2 3 4 5 6 7
	 */
    
    public void testComprobarPeonEnemigo() {
    	Tablero tablero = new Tablero();
        Rey reyNegro = new Rey("negro");
        var peonB = new Peon("blanco");

        Coordenada coordRey = new Coordenada(3, 0);
        Coordenada coordPeon = new Coordenada(2,1);

        tablero.insertarPieza(coordRey, reyNegro);
        tablero.insertarPieza(coordPeon, peonB);

        assertTrue( reyNegro.estaEnJaque(tablero.getTablero(), tablero.getEtapa() ));
    }
    
    
    @Test
	/*
	 * 		 TABLERO
	 *  7       
	 *  6   
	 *  5   
	 *  4     
	 *  3 
	 *  2      
	 *  1     n  
	 *  0       n
	 *    0 1 2 3 4 5 6 7
	 */
    
    public void testComprobarPeonAliado() {
    	Tablero tablero = new Tablero();
        Rey reyNegro = new Rey("negro");
        var peonN = new Peon("negro");

        Coordenada coordRey = new Coordenada(3, 0);
        Coordenada coordPeon = new Coordenada(2,1);

        tablero.insertarPieza(coordRey, reyNegro);
        tablero.insertarPieza(coordPeon, peonN);

        assertFalse( reyNegro.estaEnJaque(tablero.getTablero(), tablero.getEtapa() ));
    }
    
    
    @Test
	/*
	 * 		 TABLERO
	 *  7       
	 *  6   
	 *  5   
	 *  4     
	 *  3 
	 *  2      
	 *  1       b
	 *  0     n  
	 *    0 1 2 3 4 5 6 7
	 */
    
    public void testComprobarPeonEnemigoPasado() {
    	Tablero tablero = new Tablero();
        Rey reyNegro = new Rey("negro");
        var peonB = new Peon("blanco");

        Coordenada coordRey = new Coordenada(3, 1);
        Coordenada coordPeon = new Coordenada(2,0);

        tablero.insertarPieza(coordRey, reyNegro);
        tablero.insertarPieza(coordPeon, peonB);

        assertFalse( reyNegro.estaEnJaque(tablero.getTablero(), tablero.getEtapa() ));
    }
    
    
    @Test
	/*
	 * 		 TABLERO
	 *  7       b       
	 *  6         x 
	 *  5   
	 *  4     
	 *  3 
	 *  2     
	 *  1   
	 *  0      
	 *    0 1 2 3 4 5 6 7
	 */
    
    public void testComprobarPeonEnemigoNegro() {
    	Tablero tablero = new Tablero();
        Rey reyBlanco = new Rey("blanco");
        var peonN = new Peon("negro");

        Coordenada coordRey = new Coordenada(3, 7);
        Coordenada coordPeon = new Coordenada(4, 6);

        tablero.insertarPieza(coordRey, reyBlanco);
        tablero.insertarPieza(coordPeon, peonN);

        assertTrue( reyBlanco.estaEnJaque(tablero.getTablero(), tablero.getEtapa() ));
    }
    
    
    @Test
	/*
	 * 		 TABLERO
	 *  7       b      
	 *  6         b  
	 *  5   
	 *  4     
	 *  3 
	 *  2     
	 *  1   
	 *  0      
	 *    0 1 2 3 4 5 6 7
	 */
    
    public void testComprobarPeonAliadoBlanco() {
    	Tablero tablero = new Tablero();
        Rey reyBlanco = new Rey("blanco");
        var peonB = new Peon("blanco");

        Coordenada coordRey = new Coordenada(3, 7);
        Coordenada coordPeon = new Coordenada(4, 6);

        tablero.insertarPieza(coordRey, reyBlanco);
        tablero.insertarPieza(coordPeon, peonB);

        assertFalse( reyBlanco.estaEnJaque(tablero.getTablero(), tablero.getEtapa() ));
    }
    
    
    @Test
	/*
	 * 		 TABLERO
	 *  7              
	 *  6         n 
	 *  5       b
	 *  4     
	 *  3 
	 *  2     
	 *  1   
	 *  0      
	 *    0 1 2 3 4 5 6 7
	 */
    
    public void testComprobarPeonPasado() {
    	Tablero tablero = new Tablero();
        Rey reyBlanco = new Rey("blanco");
        var peonB = new Peon("blanco");

        Coordenada coordRey = new Coordenada(3, 5);
        Coordenada coordPeon = new Coordenada(4, 6);

        tablero.insertarPieza(coordRey, reyBlanco);
        tablero.insertarPieza(coordPeon, peonB);

        assertFalse( reyBlanco.estaEnJaque(tablero.getTablero(), tablero.getEtapa() ));
    }
    
}
