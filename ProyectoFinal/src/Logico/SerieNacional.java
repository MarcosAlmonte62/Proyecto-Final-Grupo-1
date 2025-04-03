package Logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SerieNacional implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Equipo> clasificacion;
    private List<Partido> calendario;
    private List<Jugador> todosLosJugadores;

    private static SerieNacional instance = null;

    private SerieNacional() {
        super();
        this.clasificacion = new ArrayList<>();
        this.calendario = new ArrayList<>();
        this.todosLosJugadores = new ArrayList<>();
    }

    public static SerieNacional getInstance() {
        if (instance == null) {
            instance = new SerieNacional();
        }
        return instance;
    }

    public void agregarEquipo(Equipo equipo) {
        clasificacion.add(equipo);
    }

    public void agregarPartido(Partido partido) {
        calendario.add(partido);
    }

    public List<Equipo> getClasificacion() {
        return clasificacion;
    }

    public List<Partido> getCalendario() {
        return calendario;
    }

    public void agregarJugador(Jugador jugador) {
        todosLosJugadores.add(jugador);
    }

    public List<Jugador> getTodosLosJugadores() {
        return todosLosJugadores;
    }

    public Equipo buscarEquipoNombre(String nombre) {
        for (Equipo e : clasificacion) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        return null;
    }

    public Jugador mejorDelTorneo() {
        Jugador mejor = null;
        double mejorValor = 0;

        for (Jugador j : todosLosJugadores) {
            if (j.getStats() != null) {
                double eficiencia = j.getStats().valoracion();
                if (eficiencia > mejorValor) {
                    mejorValor = eficiencia;
                    mejor = j;
                }
            }
        }

        return mejor;
    }

    public void generarCalendario() {
        calendario.clear();

        for (int i = 0; i < clasificacion.size(); i++) {
            for (int j = i + 1; j < clasificacion.size(); j++) {
                Partido ida = new Partido(clasificacion.get(i), clasificacion.get(j));
                Partido vuelta = new Partido(clasificacion.get(j), clasificacion.get(i));
                calendario.add(ida);
                calendario.add(vuelta);
            }
        }
    }
}
