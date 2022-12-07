package ajedrez;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import algo3.ajedrez.Coordenada;
import algo3.ajedrez.Reyna;
import algo3.ajedrez.Tablero;

public class TestReyna {

	@Test
	public void testCrearReyna() {
		Reyna reyna = new Reyna("blanco");
		
		assertTrue(reyna != null);
	}
	
	@Test
	public void testColor() {
		Reyna reynaNegro = new Reyna("negro");
		Reyna reynaBlanco = new Reyna("blanco");

		assertTrue(reynaBlanco.esBlanco());
		assertFalse(reynaNegro.esBlanco());
	}
	
	@Test
	public void testEsReyna() {
		Reyna reynaNegro = new Reyna("negro");
		Reyna reynaBlanco = new Reyna("blanco");

		assertTrue(reynaBlanco.esReyna());
		assertTrue(reynaNegro.esReyna());
	}

	
	@Test
	/*
	 * 		 TABLERO
	 *  7       x       x
	 *  6 x     x     x  
	 *  5   x   x   x
	 *  4     x x x
	 *  3 x x x n x x x x
	 *  2     x x x  
	 *  1   x   x   x
	 *  0 x     x     x
	 *    0 1 2 3 4 5 6 7
	 */
	public void testMovimientosTodosPosibles() {
		Tablero tablero = new Tablero();
		Coordenada miCoordenada = new Coordenada(3,3);
		Reyna reyna = new Reyna("negro");

		tablero.insertarPieza(miCoordenada, reyna);
		
		var movOK = new ArrayList<Coordenada>();
		//movOK
		for(int i=0; i<8; i++) {
			if ( i==3 ) continue;
			//mov diagonales
			movOK.add(new Coordenada(i,i));
			if (i < 7 ) movOK.add(new Coordenada(i,6-i));
			
			//mov horizontales y verticales
			movOK.add(new Coordenada(i,3));
			movOK.add(new Coordenada(3,i));
		}
		
		var movPosiblesReyna = tablero.movimientosPieza(reyna);
		
		//doble inclusion
		movPosiblesReyna.forEach(mov -> assertTrue(movOK.contains(mov)));
		movOK.forEach(mov -> assertTrue(movPosiblesReyna.contains(mov)));
		
	}
	
	@Test
	/*
	 * 		 TABLERO
	 *  7       x       x
	 *  6 x     x     x  
	 *  5   x   x   x
	 *  4     x x x
	 *  3 x x x n x n
	 *  2     x x n  
	 *  1   x   x   
	 *  0 x    [b]     
	 *    0 1 2 3 4 5 6 7
	 */
	public void testMovimientosQuePuedeHacerLaReinaConPiezaEnElCamino() {
		Tablero tablero = new Tablero();
		Coordenada miCoordenada = new Coordenada(3,3);

		Reyna reynaNegro = new Reyna("negro");
		Reyna reynaBlanco = new Reyna("blanco");

		Reyna reynaNegro2 = new Reyna("negro");
		Reyna reynaNegro3 = new Reyna("negro");
		
		
		tablero.insertarPieza(miCoordenada, reynaNegro);
		tablero.insertarPieza(new Coordenada(3,0), reynaBlanco);
		tablero.insertarPieza(new Coordenada(4,2), reynaNegro2);
		tablero.insertarPieza(new Coordenada(5,3), reynaNegro3);
		
		var movOK = new ArrayList<Coordenada>();
		//movOK
		for(int i=0; i<8; i++) {
			if ( i==3 ) continue;
			//mov diagonales
			movOK.add(new Coordenada(i,i));
			if (i < 4 ) movOK.add(new Coordenada(i,6-i));
			
			//mov horizontales y verticales
			if(i<5) movOK.add(new Coordenada(i,3));
			movOK.add(new Coordenada(3,i));
		}
		
		var movPosiblesReyna = tablero.movimientosPieza(reynaNegro);
		
		//doble inclusion
		movPosiblesReyna.forEach(mov -> assertTrue(movOK.contains(mov)));
		movOK.forEach(mov -> assertTrue(movPosiblesReyna.contains(mov)));
		
	}
	
	@Test
	/*
	 * 		 TABLERO
	 *  7       x       x
	 *  6 x     x     x  
	 *  5   x   x   x
	 *  4     x x x
	 *  3 x x x n x n
	 *  2     x x n  
	 *  1   x   x   
	 *  0 x    [b]<--n    
	 *    0 1 2 3 4 5 6 7
	 */
	public void testReynaBlancaMueveyAlMoverComeOtraPieza() {
		var tablero = new Tablero();
		var miCoordenada = new Coordenada(3,3);

		Reyna reynaNegro = new Reyna("negro");
		Reyna reynaBlanco = new Reyna("blanco");

		var reynaNegro2 = new Reyna("negro");
		var reynaNegro3 = new Reyna("negro");
		
		
		tablero.insertarPieza(miCoordenada, reynaNegro);
		tablero.insertarPieza(new Coordenada(3,0), reynaBlanco);
		tablero.insertarPieza(new Coordenada(4,2), reynaNegro2);
		tablero.insertarPieza(new Coordenada(5,3), reynaNegro3);
		
		assertTrue( tablero.getCantidadPiezas() == 4);
		assertTrue( tablero.moverPieza(reynaNegro,new Coordenada(3,3), new Coordenada(3,0)));
		assertTrue( tablero.getCantidadPiezas() == 3);
	}
	
	@Test
	/*
	 * 		 TABLERO
	 *  7               x
	 *  6 x           x  
	 *  5   x       x
	 *  4     x n x
	 *  3 x x x n x x x x
	 *  2     x x n  
	 *  1   x   x   
	 *  0 x    [b]<--n    
	 *    0 1 2 3 4 5 6 7
	 */
	public void testReynaMueveYalMoverNoPuedeComerAOtraReynaBlanco() {
		var tablero = new Tablero();
		var miCoordenada = new Coordenada(3,3);

		Reyna reynaNegro = new Reyna("negro");
		Reyna reynaBlanco = new Reyna("blanco");

		var reynaNegro2 = new Reyna("negro");
		var reynaNegro3 = new Reyna("negro");
		
		
		tablero.insertarPieza(miCoordenada, reynaNegro);
		tablero.insertarPieza(new Coordenada(3,0), reynaBlanco);
		tablero.insertarPieza(new Coordenada(4,2), reynaNegro2);
		tablero.insertarPieza(new Coordenada(3,4), reynaNegro3);
		
		assertTrue( tablero.getCantidadPiezas() == 4);
		assertTrue( tablero.moverPieza(reynaNegro,new Coordenada(3,3), new Coordenada(3,0)));
		assertTrue( tablero.getCantidadPiezas() == 3);
	}
}
