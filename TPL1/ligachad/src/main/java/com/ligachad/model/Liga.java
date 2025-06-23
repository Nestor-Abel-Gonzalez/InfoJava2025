package com.ligachad.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Liga {
    private List<Equipo> equipos;
    private List<Partido> partidos;

    public Liga() {
        this.equipos = new ArrayList<>();
        this.partidos = new ArrayList<>();
    }

    public void agregarEquipo(Equipo equipo) {
        equipos.add(equipo);
    }

    public void registrarPartido(Partido partido) {
        partidos.add(partido);
    }

    public Jugador getGoleadorLiga() {
        return equipos.stream()
                .flatMap(e -> e.getJugadores().stream())
                .max(Comparator.comparingInt(Jugador::getGoles))
                .orElse(null);
    }

    public List<Equipo> getRankingEquipos() {
        return equipos.stream()
                .sorted((e1, e2) -> Integer.compare(
                        e2.getJugadores().stream().mapToInt(Jugador::getGoles).sum(),
                        e1.getJugadores().stream().mapToInt(Jugador::getGoles).sum()))
                .collect(Collectors.toList());
    }

    public List<Suplente> getJugadoresSuplentesSinIngresar() {
        return equipos.stream()
                .flatMap(e -> e.getJugadores().stream())
                .filter(j -> !j.esTitular() && ((Suplente) j).getPartidosIngresados() == 0)
                .map(j -> (Suplente) j)
                .collect(Collectors.toList());
    }

    public Titular getJugadorTitularMasMinutos() {
        return equipos.stream()
                .flatMap(e -> e.getJugadores().stream())
                .filter(Jugador::esTitular)
                .map(j -> (Titular) j)
                .max(Comparator.comparingInt(Titular::getMinutosJugados))
                .orElse(null);
    }
 
    public int getTotalGoles() {
        return equipos.stream()
                .flatMap(e -> e.getJugadores().stream())
                .mapToInt(Jugador::getGoles)
                .sum();
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }
}
