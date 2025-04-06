package Logico;
import java.sql.Date;

public class Main {
    public static void main(String[] args) {
    	
        // Crear equipos
        Equipo lakers = new Equipo(
            "Los Angeles Lakers", 
            "Los Angeles", 
            "Crypto.com Arena", 
            "Darvin Ham", 
            "Jeanie Buss", 
            17, 
            Date.valueOf("1947-01-01")
        );
        
        Equipo celtics = new Equipo(
            "Boston Celtics", 
            "Boston", 
            "TD Garden", 
            "Joe Mazzulla", 
            "Boston Basketball Partners", 
            17, 
            Date.valueOf("1946-06-06")
        );
        
        // Añadir jugadores a los Lakers
        agregarJugador(lakers, "LeBron James", 38, "Estados Unidos", null, 23, 206, 113, "Alero");
        agregarJugador(lakers, "Anthony Davis", 30, "Estados Unidos", null, 3, 208, 115, "Ala-Pívot");
        agregarJugador(lakers, "D'Angelo Russell", 27, "Estados Unidos", null, 1, 193, 88, "Base");
        agregarJugador(lakers, "Austin Reaves", 25, "Estados Unidos", null, 15, 196, 89, "Escolta");
        agregarJugador(lakers, "Rui Hachimura", 25, "Japón", null, 28, 203, 104, "Alero");
        
        // Añadir jugadores a los Celtics
        agregarJugador(celtics, "Jayson Tatum", 25, "Estados Unidos", null, 0, 203, 95, "Alero");
        agregarJugador(celtics, "Jaylen Brown", 27, "Estados Unidos", null, 7, 198, 101, "Escolta");
        agregarJugador(celtics, "Kristaps Porzingis", 28, "Letonia", null, 8, 221, 109, "Pívot");
        agregarJugador(celtics, "Derrick White", 29, "Estados Unidos", null, 9, 193, 86, "Base");
        agregarJugador(celtics, "Al Horford", 37, "República Dominicana", null, 42, 206, 109, "Ala-Pívot");
        
        // Crear y configurar partido
        Partido partido = new Partido(lakers, celtics);
        partido.setFecha(new Date(System.currentTimeMillis()));
        partido.setUbicacion(lakers.getEstadio());
        partido.setDuracion(30000); // 30 segundos de simulación (ajustable)
        
        // Simular partido
        System.out.println("=== SIMULACIÓN DE PARTIDO ===");
        System.out.println(lakers.getNombre() + " vs " + celtics.getNombre());
        System.out.println("Ubicación: " + partido.getUbicacion());
        System.out.println("Fecha: " + partido.getFecha());
        System.out.println("Entrenadores: " + lakers.getEntrenador() + " vs " + celtics.getEntrenador());
        
        partido.simularPartido();
        
        // Mostrar resultados detallados
        mostrarEstadisticas(partido);
        
        // Añadir partido al historial de los equipos
        lakers.agregarAlHistorial(partido);
        celtics.agregarAlHistorial(partido);
    }
    
    private static void agregarJugador(Equipo equipo, String nombre, int edad, String nacionalidad, 
                                     Lesion lesion, int dorsal, int altura, int peso, String posicion) {
        Jugador jugador = new Jugador(
            nombre, 
            edad, 
            nacionalidad, 
            lesion, 
            equipo, 
            new StatsJugador(null), // Se inicializará en el partido
            dorsal, 
            altura, 
            peso, 
            posicion, 
            new Date(System.currentTimeMillis())
        );
        jugador.getStats().setJugador(jugador); // Asignar el jugador a sus stats
        equipo.agregarJugador(jugador);
    }
    
    public static void mostrarEstadisticas(Partido partido) {
        System.out.println("\n=== ESTADÍSTICAS FINALES ===");
        System.out.printf("Resultado: %s %d - %d %s\n", 
            partido.getEquipoLocal().getNombre(), 
            partido.getPuntosLocal(), 
            partido.getPuntosVisitante(), 
            partido.getEquipoVisitante().getNombre());
        
        System.out.println("\nEstadísticas por jugador:");
        for (Logico.StatsJugador stats : partido.getStatsJugadoresLocal()) {
            Jugador jugador = stats.getJugador();
            System.out.printf("\n%s #%d (%s) - %s\n", 
                jugador.getNombre(), 
                jugador.getDorsal(),
                jugador.getPosicion(), 
                jugador.getEquipo().getNombre());
            
            System.out.printf("Puntos: %d (Dobles: %d/%d, Triples: %d/%d, TL: %d/%d)\n", 
                stats.puntosGenerados(), 
                stats.getDobles(), stats.getDobles() + (stats.getTirosAcert() - stats.getDobles() - stats.getTriples()),
                stats.getTriples(), stats.getTriples() + (stats.getTiros() - stats.getTirosAcert()),
                stats.getTirosLibresAcert(), stats.getTirosLibres());
            
            System.out.printf("Rebotes: %d (Of: %d, Def: %d) | Asistencias: %d\n", 
                stats.getRebotes() + stats.getRebotesDef(), 
                stats.getRebotes(), stats.getRebotesDef(),
                stats.getAsistencias());
            
            System.out.printf("Robos: %d | Bloqueos: %d | Faltas: %d | Pérdidas: %d\n", 
                stats.getRobos(), stats.getBloqueos(), 
                stats.getFaltas(), stats.getPerdidas());
            
            System.out.printf("Valoración: %.1f | Eficiencia: %.1f%%\n", 
                stats.valoracion(), stats.eFGPercent());
            
        }
        
    }
    
} 