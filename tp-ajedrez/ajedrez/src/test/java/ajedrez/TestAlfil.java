package ajedrez;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import algo3.ajedrez.Alfil;
import algo3.ajedrez.Coordenada;
import algo3.ajedrez.Tablero;

public class TestAlfil {
	
	private Alfil alfilBlanco, alfilNegro;

	@Before
	public void testCrearAlfil() {
		this.alfilBlanco = new Alfil("blanco");
		this.alfilNegro = new Alfil("negro");
	}
	
	@Test
	public void testCheckeaColor() {
		assertTrue(this.alfilBlanco.esBlanco());
		assertFalse(this.alfilNegro.esBlanco());
	}
	
	@Test
	public void testCheckeaQueSeanTorres() {
		assertTrue(this.alfilBlanco.esAlfil() && this.alfilNegro.esAlfil());
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
	 *  1   x       x
	 *  0 x           x
	 *    0 1 2 3 4 5 6 7
	 */
	public void testMovimientosQuePuedeHacerElAlfil() {
		var tablero = new Tablero();
		tablero.insertarPieza(new Coordenada(3,3), alfilBlanco);
		
		//movimientosoK
		ArrayList<Coordenada> movOK = new ArrayList<>();
		for(int i=0; i<8; i++) {
			if ( i==3 ) continue;
			movOK.add(new Coordenada(i,i));
			
			if (i < 7 ) movOK.add(new Coordenada(i,6-i));
		}
		
		var movPosiblesAlfil = tablero.movimientosPieza(alfilBlanco);
		
		//doble inclusion
		movPosiblesAlfil.forEach(mov -> assertTrue(movOK.contains(mov)));
		movOK.forEach(mov -> assertTrue(movPosiblesAlfil.contains(mov)));
	}
	
	@Test
	/*
	 * 		 TABLERO
	 * 
	 *  7               
	 *  6 x           
	 *  5   x       B
	 *  4     x   x 
	 *  3       B 
	 *  2     x   x
	 *  1   x       x
	 *  0 x           x
	 *    0 1 2 3 4 5 6 7
	 */
	public void testMovimientosQuePuedeHacerElAlfilConPiezaEnElCamino() {
		var tablero = new Tablero();
		var alfilBlanco2 = new Alfil("blanco");
		
		tablero.insertarPieza(new Coordenada(3,3), alfilBlanco);
		tablero.insertarPieza(new Coordenada(5,5), alfilBlanco2);
		//movimientosoK
		ArrayList<Coordenada> movOK = new ArrayList<>();
		for(int i=0; i<8; i++) {
			if ( i==3 ) continue;
			if ( i < 5 ) movOK.add(new Coordenada(i,i));
			
			if (i < 7 ) movOK.add(new Coordenada(i,6-i));
		}
		
		var movPosiblesAlfil = tablero.movimientosPieza(alfilBlanco);
		
		//doble inclusion
		movPosiblesAlfil.forEach(mov -> assertTrue(movOK.contains(mov)));
		movOK.forEach(mov -> assertTrue(movPosiblesAlfil.contains(mov)));
	}
	
	@Test
	/*
	 * 		 TABLERO
	 * 
	 *  7               
	 *  6 x           
	 *  5   x       N -> [B]
	 *  4     x   x 
	 *  3       B 
	 *  2     x   x
	 *  1   x       x
	 *  0 x           x
	 *    0 1 2 3 4 5 6 7
	 */
	public void testAlfilBlancaMueveyAlMoverComeOtraPieza() {
		var tablero = new Tablero();
		
		tablero.insertarPieza(new Coordenada(3,3), alfilBlanco);
		tablero.insertarPieza(new Coordenada(5,5), alfilNegro);
		
		assertTrue( tablero.moverPieza(alfilBlanco,new Coordenada(3,3), new Coordenada(5,5)));
		assertTrue( tablero.getCantidadPiezas() == 1);
	}
	
	@Test
	/*
	 * 		 TABLERO
	 * 
	 *  7               
	 *  6 x           
	 *  5   x       N -> [B]
	 *  4     x   x 
	 *  3       B 
	 *  2     x   x
	 *  1   B       x
	 *  0             x
	 *    0 1 2 3 4 5 6 7
	 */
	public void testAlfilMueveYalMoverNoPuedeComerAOtraAlfilBlanco() {
		var tablero = new Tablero();
		var alfilBlanco2 = new Alfil("blanco");
		
		tablero.insertarPieza(new Coordenada(3,3), alfilBlanco);
		tablero.insertarPieza(new Coordenada(5,5), alfilNegro);
		tablero.insertarPieza(new Coordenada(1,1), alfilBlanco2);
		
		assertTrue( tablero.moverPieza(alfilBlanco,new Coordenada(3,3), new Coordenada(5,5)));
		assertTrue( tablero.getCantidadPiezas() == 2);
	}

}
