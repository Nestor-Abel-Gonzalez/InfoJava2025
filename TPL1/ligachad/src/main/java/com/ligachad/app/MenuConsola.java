package com.ligachad.app;

import com.ligachad.model.*;

import java.util.*;

public class MenuConsola {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Liga liga = new Liga();
  
    
    
    public static void iniciar() {
        limpiarPantalla(); 
        int opcion;
        do {
            System.out.println("\n--- MENU LIGA CHAD ---");
            System.out.println("1. Registrar jugador");
            System.out.println("2. Crear equipo");
            System.out.println("3. Agregar jugador a equipo");
            System.out.println("4. Registrar partido");
            System.out.println("5. Mostrar goleador de la liga");
            System.out.println("6. Ranking de equipos por goles");
            System.out.println("7. Transferir jugador");
            System.out.println("8. Jugadores suplentes sin ingresar");
            System.out.println("9. Titular con mas minutos");
            System.out.println("10. Exportar jugadores a CSV");
            System.out.println("11. Reporte general de liga");
            System.out.println("12. Salir");
            System.out.println("13. Reporte de equipo");
            System.out.println("14. Mostrar listado de todos los jugadores y su tipo");

            System.out.print("\nINGRESE OPCION : ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> registrarJugador();
                case 2 -> crearEquipo();
                case 3 -> agregarJugadorAEquipo();
                case 4 -> registrarPartido();
                case 5 -> mostrarGoleador();
                case 6 -> mostrarRanking();
                case 7 -> transferirJugador();
                case 8 -> mostrarSuplentesSinIngresar();
                case 9 -> mostrarTitularMasMinutos();
                case 10 -> exportarJugadoresCSV();
                case 11 -> generarReporteGeneral();
                case 12 -> System.out.println("Hasta luego!");
                case 13 -> generarReporteEquipo();
                case 14 -> mostrarTodosLosJugadoresConTipo();
                
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 12);
    }

    // === Métodos de operaciones principales ===

    private static void registrarJugador() {
        System.out.print("Nombre del jugador: ");
        String nombre = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = Integer.parseInt(scanner.nextLine());
        System.out.print("Es titular? (S/N): ");
        String tipo = scanner.nextLine().toUpperCase();

        Jugador jugador = tipo.equals("S") ? new Titular(nombre, edad) : new Suplente(nombre, edad);
        System.out.println("Jugador creado: " + jugador.getNombre() + " (" + (jugador.esTitular() ? "Titular" : "Suplente") + ")");
    }

    private static void crearEquipo() {
        System.out.print("Nombre del equipo: ");
        String nombre = scanner.nextLine();
        liga.agregarEquipo(new Equipo(nombre));
        System.out.println("Equipo creado.");
    }

    private static void agregarJugadorAEquipo() {
        Equipo equipo = elegirEquipo();
        if (equipo == null) return;
        System.out.print("Nombre del jugador: ");
        String nombre = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = Integer.parseInt(scanner.nextLine());
        System.out.print("Es titular? (S/N): ");
        String tipo = scanner.nextLine().toUpperCase();

        Jugador jugador = tipo.equals("S") ? new Titular(nombre, edad) : new Suplente(nombre, edad);
        equipo.agregarJugador(jugador);
        System.out.println("Jugador agregado.");
    }

    private static void registrarPartido() {
        System.out.println("Seleccione equipo local:");
        Equipo local = elegirEquipo();
        System.out.println("Seleccione equipo visitante:");
        Equipo visitante = elegirEquipo();
        if (local == null || visitante == null || local == visitante) {
            System.out.println("Equipos invalidos.");
            return;
        }

        Partido partido = new Partido(local, visitante);
        System.out.print("Cuantos goles desea registrar?: ");
        int total = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < total; i++) {
            System.out.print("Nombre del jugador que anoto: ");
            String nombre = scanner.nextLine();
            Jugador goleador = buscarJugadorPorNombre(nombre);
            if (goleador != null) {
                partido.registrarGol(goleador);
                System.out.println("Gol registrado para " + nombre);
            } else {
                System.out.println("Jugador no encontrado.");
            }
        }
        liga.registrarPartido(partido);
        System.out.println("Partido registrado.");
    }

    private static void mostrarGoleador() {
        Jugador goleador = liga.getGoleadorLiga();
        if (goleador != null) {
            System.out.println("Goleador: " + goleador.getNombre() + " con " + goleador.getGoles() + " goles.");
        } else {
            System.out.println("No hay goles registrados.");
        }
    }

    private static void mostrarRanking() {
        List<Equipo> ranking = liga.getRankingEquipos();
        System.out.println("--- Ranking por Goles ---");
        for (Equipo e : ranking) {
            int total = e.getJugadores().stream().mapToInt(Jugador::getGoles).sum();
            System.out.println(e.getNombre() + " - " + total + " goles");
        }
    }

    private static void transferirJugador() {
        System.out.print("Nombre del jugador a transferir: ");
        String nombre = scanner.nextLine();
        Jugador jugador = buscarJugadorPorNombre(nombre);
        if (jugador == null) {
            System.out.println("Jugador no encontrado.");
            return;
        }

        Equipo origen = buscarEquipoPorJugador(jugador);
        Equipo destino = elegirEquipo();
        if (origen != null && destino != null && origen != destino) {
            origen.quitarJugador(jugador);
            destino.agregarJugador(jugador);
            System.out.println("Transferencia completada.");
        } else {
            System.out.println("Error en transferencia.");
        }
    }

    private static void mostrarSuplentesSinIngresar() {
        List<Suplente> suplentes = liga.getJugadoresSuplentesSinIngresar();
        System.out.println("Suplentes sin ingresar:");
        for (Suplente s : suplentes) {
            System.out.println(s.getNombre() + " (" + s.getEdad() + " años)");
        }
    }

    private static void mostrarTitularMasMinutos() {
        Titular titular = liga.getJugadorTitularMasMinutos();
        if (titular != null) {
            System.out.println("Titular con mas minutos: " + titular.getNombre() + " - " + titular.getMinutosJugados() + " minutos");
        } else {
            System.out.println("No hay titulares registrados.");
        }
    }

    private static void exportarJugadoresCSV() {
        Equipo equipo = elegirEquipo();
        if (equipo != null) {
            equipo.exportarJugadoresACSV();
        } else {
            System.out.println("No se pudo exportar.");
        }
    }

    private static void generarReporteGeneral() {
        System.out.println("--- Reporte General de Liga ---");
        System.out.println("Total de goles: " + liga.getTotalGoles());
        Jugador goleador = liga.getGoleadorLiga();
        if (goleador != null) {
            System.out.println("Jugador mas eficiente: " + goleador.getNombre() + " (" + goleador.getGoles() + " goles)");
        }
    }

    private static void generarReporteEquipo() {
        Equipo equipo = elegirEquipo();
        if (equipo == null) return;

        List<Jugador> jugadores = equipo.getJugadores();
        if (jugadores.isEmpty()) {
            System.out.println("El equipo no tiene jugadores.");
            return;
        }

        double promedio = jugadores.stream()
                .mapToInt(Jugador::getGoles)
                .average()
                .orElse(0.0);

        List<Jugador> sinGoles = jugadores.stream()
                .filter(j -> j.getGoles() == 0)
                .toList();

        Optional<Titular> titularMasMinutos = jugadores.stream()
                .filter(Jugador::esTitular)
                .map(j -> (Titular) j)
                .max(Comparator.comparingInt(Titular::getMinutosJugados));

        Optional<Suplente> suplenteMasUsado = jugadores.stream()
                .filter(j -> !j.esTitular())
                .map(j -> (Suplente) j)
                .max(Comparator.comparingInt(Suplente::getPartidosIngresados));

        System.out.println("\n--- Reporte del Equipo: " + equipo.getNombre() + " ---");
        System.out.printf("Promedio de goles por jugador: %.2f%n", promedio);
        System.out.println("Jugadores sin goles:");
        sinGoles.forEach(j -> System.out.println("- " + j.getNombre()));

        titularMasMinutos.ifPresent(t -> System.out.println("Titular con más minutos: " + t.getNombre() + " (" + t.getMinutosJugados() + " minutos)"));
        suplenteMasUsado.ifPresent(s -> System.out.println("Suplente más utilizado: " + s.getNombre() + " (" + s.getPartidosIngresados() + " ingresos)"));
    }

    private static void mostrarTodosLosJugadoresConTipo() {
    System.out.println("Listado de jugadores por equipo:");
    for (Equipo equipo : liga.getEquipos()) {
        System.out.println("Equipo: " + equipo.getNombre());
        for (Jugador jugador : equipo.getJugadores()) {
            String tipo = jugador.esTitular() ? "Titular" : "Suplente";
            System.out.println(" - " + jugador.getNombre() + " (Edad: " + jugador.getEdad() + ") - Tipo: " + tipo + " - Goles: " + jugador.getGoles());
        }
        System.out.println();
    }
}

    
    // === Métodos auxiliares ===

    private static Equipo elegirEquipo() {
        List<Equipo> equipos = liga.getEquipos();
        if (equipos.isEmpty()) {
            System.out.println("No hay equipos registrados.");
            return null;
        }

        System.out.println("Equipos disponibles:");
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println((i + 1) + ". " + equipos.get(i).getNombre());
        }

        System.out.print("Seleccione numero de equipo: ");
        try {
            int opcion = Integer.parseInt(scanner.nextLine());
            if (opcion < 1 || opcion > equipos.size()) {
                System.out.println("Opción inválida.");
                return null;
            }
            return equipos.get(opcion - 1);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            return null;
        }
    }

    private static Jugador buscarJugadorPorNombre(String nombre) {
        for (Equipo equipo : liga.getEquipos()) {
            for (Jugador jugador : equipo.getJugadores()) {
                if (jugador.getNombre().equalsIgnoreCase(nombre)) {
                    return jugador;
                }
            }
        }
        return null;
    }

    private static Equipo buscarEquipoPorJugador(Jugador jugador) {
        for (Equipo equipo : liga.getEquipos()) {
            if (equipo.getJugadores().contains(jugador)) {
                return equipo;
            }
        }
        return null;
    }


    private static void limpiarPantalla() {
    try {
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    } catch (Exception e) {
        System.out.println("No se pudo limpiar la pantalla.");
    }
}

}
