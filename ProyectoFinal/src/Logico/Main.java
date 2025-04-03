package Logico;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        SerieNacional liga = SerieNacional.getInstance();

        // Crear jugadores
        Jugador j1 = new Jugador("Carlos Méndez", 24, "Dominicana", null, null,
                new StatsJugador(10, 5, 3, 2, 1, 0, 2, 1, 0, 0, 0, null),
                7, 190, 85, "Escolta", new Date(System.currentTimeMillis()));
        j1.setRating(8.5f);

        Jugador j2 = new Jugador("Luis Gómez", 26, "Dominicana", null, null,
                new StatsJugador(20, 2, 6, 1, 0, 3, 4, 2, 0, 0, 0, null),
                12, 200, 95, "Ala-Pívot", new Date(System.currentTimeMillis()));
        j2.setRating(9.2f);

        liga.agregarJugador(j1);
        liga.agregarJugador(j2);

        // Crear equipo
        Equipo equipo1 = new Equipo();
        equipo1.setNombre("Tigres del Este");
        equipo1.setCiudad("Santiago");
        equipo1.setEstadio("Arena del Sol");

        j1.setEquipo(equipo1);
        j2.setEquipo(equipo1);
        equipo1.agregarJugador(j1);
        equipo1.agregarJugador(j2);

        StatsEquipo statsEquipo = new StatsEquipo(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, equipo1);
        equipo1.setStats(statsEquipo);

        liga.agregarEquipo(equipo1);

        // Crear partido
        Equipo equipo2 = new Equipo();
        equipo2.setNombre("Panteras FC");
        equipo2.setCiudad("Santo Domingo");
        equipo2.setEstadio("Panther Arena");
        equipo2.setNomina(new java.util.ArrayList<>());
        equipo2.setStats(new StatsEquipo(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, equipo2));

        liga.agregarEquipo(equipo2);

        Partido p1 = new Partido(equipo1, equipo2);
        try {
            p1.setFecha(new SimpleDateFormat("dd/MM/yyyy").parse("15/04/2025"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        p1.setUbicacion("Arena del Sol");
        p1.setPuntosLocal(87);
        p1.setPuntosVisitante(78);
        p1.setJugado(true);

        liga.agregarPartido(p1);

        // Probar MVP
        Jugador mvp = liga.mejorDelTorneo();
        if (mvp != null) {
            System.out.println("MVP del torneo: " + mvp.getNombre() + " con valoración " + mvp.getStats().valoracion());
        } else {
            System.out.println("No hay MVP (aún).");
        }

        // Mostrar clasificación
        System.out.println("\nEquipos en la liga:");
        for (Equipo e : liga.getClasificacion()) {
            System.out.println("- " + e.getNombre());
        }

        // Mostrar calendario
        System.out.println("\nPartidos agendados:");
        for (Partido p : liga.getCalendario()) {
            System.out.println(p.getEquipoLocal().getNombre() + " vs " + p.getEquipoVisitante().getNombre() +
                    " [" + (p.isJugado() ? "Jugado" : "Pendiente") + "]");
        }
    }
}
