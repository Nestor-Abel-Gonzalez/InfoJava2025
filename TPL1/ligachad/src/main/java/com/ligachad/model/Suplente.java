package com.ligachad.model;

public class Suplente extends Jugador {

    private int partidosIngresados;

    public Suplente(String nombre, int edad) {
        super(nombre, edad);
        this.partidosIngresados = 0;
    }

    public int getPartidosIngresados() {
        return partidosIngresados;
    }

    public void ingresarPartido() {
        this.partidosIngresados++;
    }

    @Override
    public boolean esTitular() {
        return false;
    }

    @Override
    public String toString() {
        return "[Suplente] " + super.toString() + " - Ingresos: " + partidosIngresados;
    }
}
