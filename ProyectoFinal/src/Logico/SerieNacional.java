package Logico;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    public Equipo liderClasificacion() {
        int mayor = 0;
        Equipo lider = null;
        for (Equipo e : clasificacion) {
            if (mayor < e.getStats().puntuacion()) {
                mayor = e.getStats().puntuacion();
                lider = e;
            }
        }
        return lider;
    }

    public Jugador buscarJugadorPorNombre(String nombre) {
        for (Jugador jugador : todosLosJugadores) {
            if (jugador.getNombre().equalsIgnoreCase(nombre)) {
                return jugador;
            }
        }
        return null;
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
    public List<Equipo> getEquipos() {
        return getClasificacion();
    }
 // GUARDAR DATOS
    public void guardarDatos(String rutaArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(instance); // Guarda la instancia única
            System.out.println("Datos guardados exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // CARGAR DATOS
    public static void cargarDatos(String rutaArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            instance = (SerieNacional) ois.readObject(); // Reemplaza la instancia actual
            System.out.println("Datos cargados exitosamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
