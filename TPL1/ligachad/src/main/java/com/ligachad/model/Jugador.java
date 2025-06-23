package com.ligachad.model;

public abstract class Jugador {
    protected String nombre;
    protected int edad;
    protected int goles;

    public Jugador(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.goles = 0;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public int getGoles() { return goles; }

    public void anotarGol() { goles++; }

    public abstract boolean esTitular();
}
