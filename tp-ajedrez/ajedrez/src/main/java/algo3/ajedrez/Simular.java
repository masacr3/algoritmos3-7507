package algo3.ajedrez;

import java.util.ArrayList;

public class Simular {
    
    private Pieza pieza;
    private Tablero tablero;

    public Simular(Pieza pieza, Tablero tablero) {
        this.pieza = pieza;
        this.tablero = tablero;
    }

    public boolean esJaqueDescubierto() {
        Tablero tableroSimulador = tablero.copiarTablero(tablero);
        Rey reyAliado = (Rey) tableroSimulador.getReyAliado(pieza.getColor());
        tableroSimulador.removerPieza(this.pieza);
        return reyAliado.estaEnJaque(tableroSimulador.getTablero(), tableroSimulador.getEtapa());
    }

    public ArrayList<Coordenada> casillasHabilitadas(ArrayList<Coordenada> movimientos) {
        Tablero tableroSimulador = tablero.copiarTablero(tablero);
        ArrayList<Coordenada> lista = new ArrayList<>();  
        Rey rey = (Rey) pieza;
        for (Coordenada coord : movimientos) {
            tableroSimulador.removerPieza(pieza);
            tableroSimulador.insertarPieza(coord, pieza);
            if ( !rey.estaEnJaque(tableroSimulador.getTablero(), tableroSimulador.getEtapa())) {
                lista.add(coord);
            }
        }
        return lista;
    }

}
