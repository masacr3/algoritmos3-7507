package ajedrez;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import algo3.ajedrez.Coordenada;
import algo3.ajedrez.Tablero;
import algo3.ajedrez.Torre;

public class TorreTest {
	
	Torre torreNegro, torreBlanco;
	
	@Before
	public void testCrear() {
		this.torreNegro = new Torre("negro");
		this.torreBlanco = new Torre("blanco");
	}
	
	@Test
	public void testCheckeaColor() {
		assertTrue(this.torreBlanco.esBlanco());
		assertFalse(this.torreNegro.esBlanco());
	}
	
	@Test
	public void testCheckeaQueSeanTorres() {
		assertTrue(this.torreBlanco.esTorre() && this.torreNegro.esTorre());
	}
	
	@Test
	/*
	 * 		 TABLERO
	 * 
	 *  7       x
	 *  6       x
	 *  5       x
	 *  4       x
	 *  3 x x x B x x x x
	 *  2       x
	 *  1       x
	 *  0       x
	 *    0 1 2 3 4 5 6 7
	 */
	public void testMovimientosQuePuedeHacerLaTorre() {
		var tablero = new Tablero();
		tablero.insertarPieza(new Coordenada(3,3), torreBlanco);
		
		//movimientosoK
		ArrayList<Coordenada> movOK = new ArrayList<>();
		for(int i=0; i<8; i++) {
			if ( i==3 ) continue;
			movOK.add(new Coordenada(i,3));
			movOK.add(new Coordenada(3,i));
		}
		
		var movPosiblesTorre = tablero.movimientosPieza(torreBlanco);
		
		//doble inclusion
		movPosiblesTorre.forEach(mov -> assertTrue(movOK.contains(mov)));
		movOK.forEach(mov -> assertTrue(movPosiblesTorre.contains(mov)));
	}
	
	@Test
	/*
	 * 		 TABLERO
	 * 
	 *  7       x
	 *  6       x
	 *  5       x
	 *  4       x
	 *  3 x x x B x  
	 *  2       x
	 *  1       x
	 *  0       x
	 *    0 1 2 3 4 5 6 7
	 */
	public void testMovimientosQuePuedeHacerLaTorreConPiezaEnElCamino() {
		var tablero = new Tablero();
		var torreBlanco2 = new Torre("blanco");
		ArrayList<Coordenada> movimientosRta = new ArrayList<>();
		for(int i=0; i<8; i++) {
			if (i==3) continue;
			if (i<5) {
				movimientosRta.add(new Coordenada(i,3));
			}
			movimientosRta.add(new Coordenada(3,i));
		}
		tablero.insertarPieza(new Coordenada(3,3), torreBlanco);
		tablero.insertarPieza(new Coordenada(5,3), torreBlanco2);
		var movimientosDeLaTorre = tablero.movimientosPieza(torreBlanco);
		
		
		//doble inclusion
		
		movimientosDeLaTorre.forEach(mov -> assertTrue(movimientosRta.contains(mov)));
		movimientosRta.forEach(mov -> assertTrue(movimientosDeLaTorre.contains(mov)));
		
	}
	
	@Test
	/*
	 * 		 TABLERO
	 * 
	 *  7       x
	 *  6       x
	 *  5       x
	 *  4       x
	 *  3 x x x B x [N]<-B  
	 *  2       x
	 *  1       x
	 *  0       x
	 *    0 1 2 3 4  5  6 7
	 */
	public void testTorreBlancaMueveyAlMoverComeOtraPieza() {
		var tablero = new Tablero();
		
		tablero.insertarPieza(new Coordenada(3,3), torreBlanco);
		tablero.insertarPieza(new Coordenada(5,3), torreNegro);
		
		assertTrue( tablero.moverPieza(torreBlanco,new Coordenada(3,3), new Coordenada(5,3)));
		assertTrue( tablero.getCantidadPiezas() == 1);
	}
	
	@Test
	/*
	 * 		 TABLERO
	 * 
	 *  7       x
	 *  6       x
	 *  5       x
	 *  4       x
	 *  3   B x B x [N]<- B  
	 *  2       x
	 *  1       x
	 *  0       x
	 *    0 1 2 3 4  5  6 7
	 */
	public void testTorreBlancaMueveYalMoverNoPuedeComerAOtraTorreBlanca() {
		var torreBlanco2 = new Torre("blanco");
		var tablero = new Tablero();
		
		tablero.insertarPieza(new Coordenada(3,3), torreBlanco);
		tablero.insertarPieza(new Coordenada(5,3), torreNegro);
		tablero.insertarPieza(new Coordenada(1,3), torreBlanco2);
		
		assertTrue( tablero.moverPieza(torreBlanco,new Coordenada(3,3), new Coordenada(5,3)));
		
		assertTrue( tablero.getCantidadPiezas() == 2);
	}

}
