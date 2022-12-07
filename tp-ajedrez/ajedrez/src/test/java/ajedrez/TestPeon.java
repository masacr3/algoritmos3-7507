package ajedrez;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import algo3.ajedrez.Coordenada;
import algo3.ajedrez.Peon;
import algo3.ajedrez.Pieza;
import algo3.ajedrez.Tablero;

public class TestPeon {

	private Peon peonBlanco;
	private Peon peonNegro;

	@Before
	public void testCrearAlfil() {
		this.peonBlanco = new Peon("blanco");
		this.peonNegro = new Peon("negro");
	}
	
	@Test
	public void testCheckeaColor() {
		assertTrue(this.peonBlanco.esBlanco());
		assertFalse(this.peonNegro.esBlanco());
	}
	
	@Test
	public void testCheckeaQueSeanPeones() {
		assertTrue(this.peonBlanco.esPeon() && this.peonNegro.esPeon());
	}
	
	@Test
	/*
	 * 		 TABLERO
	 * 
	 *  7               
	 *  6 
	 *  5         x
	 *  4         n
	 *  3   x          x
	 *  2   x      [b] x [b]
	 *  1   n          n
	 *  0            
	 *    0 1 2 3 4 5  6  7
	 */
	public void testMovimientosQuePuedeHacerElPeon() {
		var tablero = new Tablero();
		var peonNegro2 = new Peon("negro");
		peonNegro2.setEtapa(1);
		
		var peonNegro3 = new Peon("negro");
		var peonBlanco2 = new Peon("blanco");
		
		tablero.insertarPieza(new Coordenada(1,1), peonNegro);
		
		tablero.insertarPieza(new Coordenada(4,4), peonNegro2);
		
		tablero.insertarPieza(new Coordenada(6,1), peonNegro3);
		tablero.insertarPieza(new Coordenada(5,2), this.peonBlanco);
		tablero.insertarPieza(new Coordenada(7,2), peonBlanco2);
		
		
		//movimientosoK
		ArrayList<Coordenada> movOK = new ArrayList<>();
		movOK.add(new Coordenada(1,2));
		movOK.add(new Coordenada(1,3));
		
		var movPosiblesPeon =tablero.movimientosPieza(peonNegro);
		
		//doble inclusion
		movPosiblesPeon.forEach(mov -> assertTrue(movOK.contains(mov)));
		movOK.forEach(mov -> assertTrue(movPosiblesPeon.contains(mov)));
		
		
		var movPosiblesPeon2 = tablero.movimientosPieza(peonNegro2);
		ArrayList<Coordenada> movOK2 = new ArrayList<>();
		movOK2.add(new Coordenada(4,5));
		
		movPosiblesPeon2.forEach(mov -> assertTrue(movOK2.contains(mov)));
		movOK2.forEach(mov -> assertTrue(movPosiblesPeon2.contains(mov)));
		
		var movPosiblesPeon3 = tablero.movimientosPieza(peonNegro3);
		ArrayList<Coordenada> movOK3 = new ArrayList<>();
		movOK3.add(new Coordenada(6,2));
		movOK3.add(new Coordenada(6,3));
		movOK3.add(new Coordenada(5,2));
		movOK3.add(new Coordenada(7,2));
		
		movPosiblesPeon3.forEach(mov -> assertTrue(movOK3.contains(mov)));
		movOK3.forEach(mov -> assertTrue(movPosiblesPeon3.contains(mov)));
		
	}

	/*
	
	@Test
	/*
	 * 		 TABLERO
	 * 
	 *  7               
	 *  6 
	 *  5         
	 *  4         
	 *  3   x n      b    
	 *  2   x x n    x     b
	 *  1   n n n    n     n
	 *  0            
	 *    0 1 2 3 4  5  6  7
	 */
	
	public void testMovimientosQuePuedeHacerElPeonConPiezaEnElCamino() {
		var tablero = new Tablero();
		var peonNegro2 = new Peon("negro");
		var peonNegro3 = new Peon("negro");
		var peonNegro4 = new Peon("negro");
		var peonNegro5 = new Peon("negro");
		var peonNegro6 = new Peon("negro");
		var peonNegro7 = new Peon("negro");
		var peonBlanco2 = new Peon("blanco");
		
		tablero.insertarPieza(new Coordenada(1,1), this.peonNegro);
		tablero.insertarPieza(new Coordenada(2,1), peonNegro2);
		tablero.insertarPieza(new Coordenada(3,1), peonNegro3);
		tablero.insertarPieza(new Coordenada(5,1), peonNegro4);
		tablero.insertarPieza(new Coordenada(7,1), peonNegro5);
		
		tablero.insertarPieza(new Coordenada(2,3), peonNegro6);
		tablero.insertarPieza(new Coordenada(3,2), peonNegro7);
		tablero.insertarPieza(new Coordenada(5,3), this.peonBlanco);
		tablero.insertarPieza(new Coordenada(7,2), peonBlanco2);
		
		ArrayList<Coordenada> movOK = new ArrayList<>();
		movOK.add(new Coordenada(1,2));
		movOK.add(new Coordenada(1,3));
		
		var movPosiblesPeon = tablero.movimientosPieza(peonNegro);
		movPosiblesPeon.forEach(mov -> assertTrue(movOK.contains(mov)));
		movOK.forEach(mov -> assertTrue(movPosiblesPeon.contains(mov)));
		
		ArrayList<Coordenada> movOK2 = new ArrayList<>();
		movOK2.add(new Coordenada(2,2));
		
		var movPosiblesPeon2 = tablero.movimientosPieza(peonNegro2); 
		movPosiblesPeon2.forEach(mov -> assertTrue(movOK2.contains(mov)));
		movOK2.forEach(mov -> assertTrue(movPosiblesPeon2.contains(mov)));
		
		var movPosiblesPeon3 = tablero.movimientosPieza(peonNegro3); 
		assertTrue(movPosiblesPeon3.size() == 0);
		
		ArrayList<Coordenada> movOK4 = new ArrayList<>();
		movOK4.add(new Coordenada(5,2));
		
		var movPosiblesPeon4 = tablero.movimientosPieza(peonNegro4); 
		movPosiblesPeon4.forEach(mov -> assertTrue(movOK4.contains(mov)));
		movOK4.forEach(mov -> assertTrue(movPosiblesPeon4.contains(mov)));
		
		var movPosiblesPeon5 = tablero.movimientosPieza(peonNegro5);
		assertTrue(movPosiblesPeon5.size() == 0);
	}

	@Test
	/*
	 * 		 TABLERO
	 * 
	 *  7               
	 *  6 
	 *  5         
	 *  4         
	 *  3  +              +
	 *  2 [b]            [b]
 	 *  1    n         n
	 *  0              
	 *     0 1  2 3 4  5  6  7
	 */
	/*
	
	public void testPeonMueveyAlMoverComeOtraPieza() {
		var tablero = new Tablero();
		var peonNegro2 = new Peon("negro");
		var peonBlanco2 = new Peon("blanco");
		
		tablero.insertarPieza(new Coordenada(1,1), this.peonNegro);
		tablero.insertarPieza(new Coordenada(5,1), peonNegro2);
		tablero.insertarPieza(new Coordenada(0,3), this.peonBlanco);
		tablero.insertarPieza(new Coordenada(6,3), peonBlanco2);
		
		tablero.moverPieza(new Coordenada(0,3), new Coordenada(0,2));
		tablero.moverPieza(new Coordenada(6,3), new Coordenada(6,2));
		
		assertTrue( tablero.moverPieza(new Coordenada(1,1), new Coordenada(0,2)));
		assertTrue( tablero.moverPieza(new Coordenada(5,1), new Coordenada(6,2)));
		assertTrue( tablero.getCoordenada(this.peonNegro).equals(new Coordenada(0,2)));
		assertTrue( tablero.getCoordenada(peonNegro2).equals(new Coordenada(6,2)));
		assertTrue( tablero.getCantidadPiezas() == 2);
	}
	
	@Test
	/*
	 * 		 TABLERO
	 * 
	 *  7               
	 *  6 
	 *  5                 b
	 *  4                 + 
	 *  3   [x] b      n  b     
	 *  2    |<-+      |  x  
 	 *  1    n         n
	 *  0                 
	 *     0 1  2 3 4  5  6  7
	 */
	
	public void testPeonComerAlpaso() {
		var tablero = new Tablero();
		var peonNegro2 = new Peon("negro");
		var peonBlanco2 = new Peon("blanco");
	
		this.peonBlanco.pasoDobleOff();
		peonBlanco2.pasoDobleOff();
		
		tablero.insertarPieza(new Coordenada(1,1), this.peonNegro);
		tablero.insertarPieza(new Coordenada(5,1), peonNegro2);
		tablero.insertarPieza(new Coordenada(2,4), this.peonBlanco);
		tablero.insertarPieza(new Coordenada(6,5), peonBlanco2);
		
		tablero.moverPieza(this.peonBlanco,new Coordenada(2,4), new Coordenada(2,3));
		tablero.moverPieza(this.peonNegro,new Coordenada(1,1), new Coordenada(1,3));
		
		var listamov = tablero.movimientosPieza(this.peonBlanco);
		
		assertTrue( listamov.contains(new Coordenada(1,3)));
		

		tablero.moverPieza(peonNegro2, new Coordenada(5,1), new Coordenada(5,3));
		tablero.moverPieza(peonBlanco2, new Coordenada(6,5),new Coordenada(6,4));
		tablero.moverPieza(peonBlanco2, new Coordenada(6,4),new Coordenada(6,3));
		
		var listamov2 = tablero.movimientosPieza(peonBlanco2);
		
		assertTrue ( listamov2.size() == 1 );
		assertTrue( listamov2.contains(new Coordenada(6,2)));
		assertFalse( listamov2.contains(new Coordenada(5,2))); //no tendria q comer al paso
		
	}

}

