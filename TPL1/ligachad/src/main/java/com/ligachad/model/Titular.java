package com.ligachad.model;

public class Titular extends Jugador {

    private int minutosJugados;

    public Titular(String nombre, int edad) {
        super(nombre, edad);
        this.minutosJugados = 0;
    }

    public int getMinutosJugados() {
        return minutosJugados;
    }

    public void jugarMinutos(int minutos) {
        this.minutosJugados += minutos;
    }

    @Override
    public boolean esTitular() {
        return true;
    }

    @Override
    public String toString() {
        return "[Titular] " + super.toString() + " - Minutos: " + minutosJugados;
    }
}
