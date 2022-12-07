package ajedrez;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import algo3.ajedrez.Coordenada;
import algo3.ajedrez.Rey;
import algo3.ajedrez.Tablero;

public class TestRey {

	@Test
	public void testCrearRey() {
		Rey rey = new Rey("blanco");
		
		assertTrue(rey != null);
	}
	
	@Test
	public void testColor() {
		Rey reyNegro = new Rey("negro");
		Rey reyBlanco = new Rey("blanco");

		assertTrue(reyBlanco.esBlanco());
		assertFalse(reyNegro.esBlanco());
	}
	
	@Test
	public void testEsRey() {
		Rey reyNegro = new Rey("negro");
		Rey reyBlanco = new Rey("blanco");

		assertTrue(reyBlanco.esRey());
		assertTrue(reyNegro.esRey());
	}
	
	@Test
	/*
	 * 		 TABLERO
	 *  7 
	 *  6   
	 *  5   
	 *  4     x x x
	 *  3     x n x 
	 *  2     x x x  
	 *  1 
	 *  0 
	 *    0 1 2 3 4 5 6 7
	 */
	public void testTodosMovimientosPosibles() {
		Tablero tablero = new Tablero();
		Coordenada coordRey = new Coordenada(3,3);
		Rey reyNegro = new Rey("negro");

		tablero.insertarPieza(coordRey, reyNegro);
		
		//movOK
		ArrayList<Coordenada> movOK = new ArrayList<Coordenada>();
		int posx[] = { -1,-1, -1, 0, 0, 1, 1, 1};
		int posy[] = { -1, 0, 1, -1, 1, -1, 0, 1};

		for(int i=0; i<8; i++) {
			movOK.add(coordRey.sumar(new Coordenada(posx[i],posy[i])));
		}
		
		ArrayList<Coordenada> movPosiblesRey = tablero.movimientosPieza(reyNegro);
		
		//doble inclusion
		movPosiblesRey.forEach((mov) -> assertTrue(movOK.contains(mov)));
		movOK.forEach((mov) -> assertTrue(movPosiblesRey.contains(mov)));
	}

}
