package com.ligachad.model;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    private List<Jugador> jugadores;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.jugadores = new ArrayList<>();
    }

    public String getNombre() { return nombre; }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public void quitarJugador(Jugador jugador) {
        jugadores.remove(jugador);
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void exportarJugadoresACSV() {
        String nombreArchivo = this.nombre.replaceAll("\\s+", "_") + "_jugadores.csv";
        try (java.io.PrintWriter writer = new java.io.PrintWriter(nombreArchivo)) {
            writer.println("Es titular SI/NO,Nombre,Edad,Cantidad de goles");
            for (Jugador j : jugadores) {
                String esTitular = j.esTitular() ? "SI" : "NO";
                writer.printf("%s,%s,%d,%d%n", esTitular, j.getNombre(), j.getEdad(), j.getGoles());
            }
            System.out.println("Archivo exportado exitosamente: " + nombreArchivo);
        } catch (Exception e) {
            System.out.println("Error al exportar: " + e.getMessage());
        }
    }
}
