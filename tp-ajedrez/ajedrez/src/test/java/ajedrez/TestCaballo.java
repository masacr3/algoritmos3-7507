package ajedrez;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import algo3.ajedrez.Caballo;
import algo3.ajedrez.Coordenada;
import algo3.ajedrez.Tablero;

public class TestCaballo {

	@Before
	public void testCrearCaballo() {
		Caballo caballoBlanco = new Caballo("blanco");
		Caballo caballoNegro = new Caballo("negro");

		assertTrue(caballoBlanco != null);
		assertTrue(caballoNegro != null);
	}
	
	@Test
	public void testColor() {
		Caballo caballoBlanco = new Caballo("blanco");
		Caballo caballoNegro = new Caballo("negro");

		assertTrue(caballoBlanco.esBlanco());
		assertFalse(caballoNegro.esBlanco());
	}
	
	
	@Test
	public void testQueSeanCaballos() {
		Caballo caballoBlanco = new Caballo("blanco");
		Caballo caballoNegro = new Caballo("negro");

		assertTrue(caballoBlanco.esCaballo());
		assertTrue(caballoNegro.esCaballo());
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
	 *  1     x   x  
	 *  0       
	 *    0 1 2 3 4 5 6 7
	 */
	
	public void testTodasPosicionesValidas() {
		var tablero = new Tablero();
		Caballo caballo = new Caballo("blanco");

		tablero.insertarPieza(new Coordenada(3,3), caballo);
		Coordenada miPos = new Coordenada(3,3);
		
		//movimientosoK
		int posx[] = { -2,-2, -1, -1, 1, 1, 2, 2 };
		int posy[] = { -1, 1, -2, 2 , -2, 2, -1, 1};
		ArrayList<Coordenada> movOK = new ArrayList<>();
		for(int i=0; i<8; i++) {
			movOK.add(miPos.sumar(new Coordenada(posx[i],posy[i])));
		}
		
		ArrayList<Coordenada> movPosiblesCaballo = tablero.movimientosPieza(caballo);
		
		//doble inclusion
		movPosiblesCaballo.forEach((mov) -> assertTrue(movOK.contains(mov)));
		movOK.forEach((mov) -> assertTrue(movPosiblesCaballo.contains(mov)));
	}
	
	
	@Test
	/*
	 * 		 TABLERO
	 * 
	 *  7               
	 *  6 
	 *  5     B   x
	 *  4   x       x
	 *  3       N   
	 *  2   x       B
	 *  1     x   x  
	 *  0       
	 *    0 1 2 3 4 5 6 7
	 */
	
	public void testMovimientosIncluyePiezasContrarias() {
		Tablero tablero = new Tablero();
		Caballo caballoBlanco1 = new Caballo("blanco");
		Caballo caballoBlanco2 = new Caballo("blanco");
		Caballo caballoNegro = new Caballo("negro");
		
		tablero.insertarPieza(new Coordenada(3,3), caballoNegro);
		tablero.insertarPieza(new Coordenada(2,5), caballoBlanco1);
		tablero.insertarPieza(new Coordenada(5,2), caballoBlanco2);

		Coordenada miPos = new Coordenada(3,3);
		
		//movimientosoK
		int posx[] = { -2,-2, -1, -1, 1, 1, 2, 2};
		int posy[] = { -1, 1, -2, 2 , -2, 2, 1, -1};
		ArrayList<Coordenada> movOK = new ArrayList<>();
		for(int i=0; i<8; i++) {
			movOK.add(miPos.sumar(new Coordenada(posx[i],posy[i])));
		}
		
		ArrayList<Coordenada> movPosiblesCaballo = tablero.movimientosPieza(caballoNegro);
		
		//doble inclusion pruebas
		movPosiblesCaballo.forEach(mov -> assertTrue(movOK.contains(mov)));
		movOK.forEach(mov -> assertTrue(movPosiblesCaballo.contains(mov)));
	}
	

	@Test
	/*
	 * 		 TABLERO
	 * 
	 *  7               
	 *  6 
	 *  5     N   x
	 *  4   x       x
	 *  3       N   
	 *  2   x       N
	 *  1     x   x  
	 *  0       
	 *    0 1 2 3 4 5 6 7
	 */
	
	public void testMovimientosNoIncluyePiezasAliadas() {
		Tablero tablero = new Tablero();
		Caballo caballoNegroPrueba1 = new Caballo("negro");
		Caballo caballoNegroPrueba2 = new Caballo("negro");
		Caballo caballoNegro = new Caballo("negro");
		
		tablero.insertarPieza(new Coordenada(3,3), caballoNegro);
		tablero.insertarPieza(new Coordenada(2,5), caballoNegroPrueba1);
		tablero.insertarPieza(new Coordenada(5,2), caballoNegroPrueba2);

		Coordenada miPos = new Coordenada(3,3);
		
		//movimientosoK
		int posx[] = { -2,-2, -1, 1, 1, 2};
		int posy[] = { -1, 1, -2, -2, 2, 1};
		ArrayList<Coordenada> movOK = new ArrayList<>();
		for(int i=0; i<6; i++) {
			movOK.add(miPos.sumar(new Coordenada(posx[i],posy[i])));
		}
		
		ArrayList<Coordenada> movPosiblesCaballo = tablero.movimientosPieza(caballoNegro);
		
		//doble inclusion pruebas
		movPosiblesCaballo.forEach(mov -> assertTrue(movOK.contains(mov)));
		movOK.forEach(mov -> assertTrue(movPosiblesCaballo.contains(mov)));
	}


	@Test
	/*
	 * 		 TABLERO
	 * 
	 *  7               
	 *  6 
	 *  5     N   x
	 *  4   x       x
	 *  3       N   
	 *  2   x       B
	 *  1     x   x  
	 *  0       
	 *    0 1 2 3 4 5 6 7
	 */
	
	public void testMovimientosPiezasAliadasYContrarias() {
		Tablero tablero = new Tablero();
		Caballo caballoNegroPrueba1 = new Caballo("negro");
		Caballo caballoBlancoPrueba2 = new Caballo("blanco");
		Caballo caballoNegro = new Caballo("negro");
		
		tablero.insertarPieza(new Coordenada(3,3), caballoNegro);
		tablero.insertarPieza(new Coordenada(2,5), caballoNegroPrueba1);
		tablero.insertarPieza(new Coordenada(5,2), caballoBlancoPrueba2);

		Coordenada miPos = new Coordenada(3,3);
		
		//movimientosoK
		int posx[] = { -2,-2, -1, 1, 1, 2, 2};
		int posy[] = { -1, 1, -2, -2, 2, 1, -1};
		ArrayList<Coordenada> movOK = new ArrayList<>();
		for(int i=0; i<7; i++) {
			movOK.add(miPos.sumar(new Coordenada(posx[i],posy[i])));
		}
		
		ArrayList<Coordenada> movPosiblesCaballo = tablero.movimientosPieza(caballoNegro);
		
		//doble inclusion pruebas
		movPosiblesCaballo.forEach(mov -> assertTrue(movOK.contains(mov)));
		movOK.forEach(mov -> assertTrue(movPosiblesCaballo.contains(mov)));
	}
}