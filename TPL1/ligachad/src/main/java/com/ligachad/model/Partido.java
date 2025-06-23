package com.ligachad.model;

import java.util.HashMap;
import java.util.Map;

public class Partido {
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private final Map<Jugador, Integer> golesPorJugador = new HashMap<>();

    public Partido(Equipo equipoLocal, Equipo equipoVisitante) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
    }

    public void registrarGol(Jugador jugador) {
        golesPorJugador.put(jugador, golesPorJugador.getOrDefault(jugador, 0) + 1);
        jugador.anotarGol();

        // Si es Titular, simulamos que jugó más minutos (por ejemplo, 10 por gol)
        if (jugador instanceof Titular titular) {
            titular.jugarMinutos(10);
        }

        // Si es Suplente, contamos que ingresó al partido
        if (jugador instanceof Suplente suplente) {
            suplente.ingresarPartido();
        }
    }

    public Map<Jugador, Integer> getGolesPorJugador() {
        return golesPorJugador;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    @Override
    public String toString() {
        int golesLocal = golesPorJugador.entrySet().stream()
                .filter(e -> equipoLocal.getJugadores().contains(e.getKey()))
                .mapToInt(Map.Entry::getValue)
                .sum();

        int golesVisitante = golesPorJugador.entrySet().stream()
                .filter(e -> equipoVisitante.getJugadores().contains(e.getKey()))
                .mapToInt(Map.Entry::getValue)
                .sum();

        return equipoLocal.getNombre() + " " + golesLocal + " - " + golesVisitante + " " + equipoVisitante.getNombre();
    }
}
