package Logico;

import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static String generarResumen(Partido partido) {
        StringBuilder resumen = new StringBuilder();
        resumen.append("Partido: ").append(partido.getEquipoLocal().getNombre())
              .append(" vs ").append(partido.getEquipoVisit().getNombre()).append("\n");
        resumen.append("Resultado: ").append(partido.getMarcadorLocal())
              .append(" - ").append(partido.getMarcadorVisit()).append("\n");
        resumen.append("Ubicación: ").append(partido.getUbicacion()).append("\n");
        resumen.append("Fecha: ").append(partido.getFechaPartido()).append("\n");
        
        if (partido.isPartidoFinalizado()) {
            Equipo ganador = partido.obtenerGanador();
            resumen.append(ganador != null ? "Ganador: " + ganador.getNombre() : "Empate").append("\n");
        } else {
            resumen.append("En progreso - Periodo: ").append(partido.getPeriodoActual()).append("\n");
        }
        
        resumen.append("\nJugadores destacados:\n");
        for (Jugador jugador : partido.getJugadoresDestacados()) {
            resumen.append("- ").append(jugador.getNombre())
                  .append(" (").append(jugador.getEquipo().getNombre()).append(")\n");
        }
        return resumen.toString();
    }

    public static void main(String[] args) {
        // Configuración de equipos y jugadores
        Equipo equipo1 = new Equipo();
        equipo1.setNombre("Leones de Santo Domingo");
        equipo1.setCiudad("Santo Domingo");
        equipo1.setNomina(new ArrayList<Jugador>());
        equipo1.setHistorial(new ArrayList<Partido>());
        equipo1.setStats(new StatsEquipo(0, 0, 0, 0, 0, 0));
        
        Equipo equipo2 = new Equipo();
        equipo2.setNombre("Tigres del Norte");
        equipo2.setCiudad("Santiago");
        equipo2.setNomina(new ArrayList<Jugador>());
        equipo2.setHistorial(new ArrayList<Partido>());
        equipo2.setStats(new StatsEquipo(0, 0, 0, 0, 0, 0));
        
        Jugador base = new Base("Juan Pérez", 25, "Dominicano", null, equipo1, 10, 185, 
                               15, 8, 12, 5, 3, 4, 6, 2);
        
        Jugador alero = new Alero("Carlos Rodríguez", 23, "Dominicano", null, equipo1, 7, 195,
                                20, 5, 18, 3, 2, 4, 2, 5, 3);
        
        Jugador pivot = new Pivot("Miguel García", 28, "Dominicano", null, equipo2, 14, 210,
                                10, 5, 3, 8, 15, 4, 2, 3, 4);
        
        equipo1.getNomina().add(base);
        equipo1.getNomina().add(alero);
        equipo2.getNomina().add(pivot);
        
        Partido partido = new Partido(equipo1, equipo2, new Date(), "Palacio de los Deportes");
        System.out.println("Iniciando partido...");
        
        partido.anotarPuntosLocal(25);
        partido.anotarPuntosVisitante(20);
        partido.agregarJugadorDestacado(base);
        partido.avanzarPeriodo();
        
        partido.anotarPuntosLocal(20);
        partido.anotarPuntosVisitante(22);
        partido.agregarJugadorDestacado(pivot);
        partido.avanzarPeriodo();
        
        partido.anotarPuntosLocal(28);
        partido.anotarPuntosVisitante(25);
        partido.agregarJugadorDestacado(alero);
        partido.avanzarPeriodo();
        
        partido.anotarPuntosLocal(22);
        partido.anotarPuntosVisitante(20);
        partido.finalizarPartido();
        
        System.out.println("\n=== RESULTADO FINAL ===");
        System.out.println(generarResumen(partido));
        
        System.out.println("\nEstadísticas de " + equipo1.getNombre());
        System.out.println("Victorias: " + equipo1.getStats().getVictorias());
        System.out.println("Puntos totales: " + equipo1.getStats().getPuntos());
        
        System.out.println("\nEstadísticas de " + equipo2.getNombre());
        System.out.println("Derrotas: " + equipo2.getStats().getDerrotas());
        System.out.println("Puntos totales: " + equipo2.getStats().getPuntos());
    }
}