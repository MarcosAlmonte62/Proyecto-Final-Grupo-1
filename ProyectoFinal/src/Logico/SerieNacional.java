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
    private static final String FILE_NAME = "datos.dat";
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
    
    public Jugador maximoAnotador() {
        Jugador mejor = null;
        double mejorValor = 0;

        for (Jugador j : todosLosJugadores) {
            if (j.getStats() != null) {
                int eficiencia = j.getStats().puntosGenerados();
                if (eficiencia > mejorValor) {
                    mejorValor = eficiencia;
                    mejor = j;
                }
            }
        }

        return mejor;
    }
    public Jugador maximoAsistidor() {
        Jugador mejor = null;
        double mejorValor = 0;

        for (Jugador j : todosLosJugadores) {
            if (j.getStats() != null) {
                int eficiencia = j.getStats().getAsistencias();
                if (eficiencia > mejorValor) {
                    mejorValor = eficiencia;
                    mejor = j;
                }
            }
        }

        return mejor;
    }
    public Jugador mejorLanzadorFaltas() {
        Jugador mejor = null;
        double mejorValor = 0;

        for (Jugador j : todosLosJugadores) {
            if (j.getStats() != null) {
                float eficiencia = j.getStats().eFTARate();
                if (eficiencia > mejorValor) {
                    mejorValor = eficiencia;
                    mejor = j;
                }
            }
        }

        return mejor;
    }

    public int contarPartidosJugados() {
        int contador = 0;
        for (Partido partido : calendario) {
            if (partido.isJugado()) {
                contador++;
            }
        }
        return contador;
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
    public void guardarDatos() {
        try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(this);
            System.out.println("Datos guardados exitosamente en '" + FILE_NAME + "'");
        } catch (IOException e) {
            System.out.println("Error al guardar datos.");
            e.printStackTrace();
        }
    }
    public void cargarDatos() {
        File archivoDatos = new File(FILE_NAME);
        
        // Si no existe se crea
        if (!archivoDatos.exists()) {
            try {
                archivoDatos.createNewFile();
                System.out.println("El archivo '" + FILE_NAME + "' no existía, pero se ha creado.");
            } catch (IOException e) {
                System.out.println("Error al crear el archivo '" + FILE_NAME + "'.");
                e.printStackTrace();
                return;
            }
        } 

        // Si existe carga los datos
        try (FileInputStream fileIn = new FileInputStream(archivoDatos);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            // Lee datos
            SerieNacional instanciaCargada = (SerieNacional) objectIn.readObject();
            // Copia los datos a la instancia actual
            this.clasificacion = instanciaCargada.clasificacion;
            this.calendario = instanciaCargada.calendario;
            this.todosLosJugadores = instanciaCargada.todosLosJugadores;
            //mensajes para saber si todo esta okay
            System.out.println("Datos cargados exitosamente desde '" + FILE_NAME + "'");
        } catch (FileNotFoundException e) {
            System.out.println("El archivo '" + FILE_NAME + "' no se encontró.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer los datos desde el archivo '" + FILE_NAME + "'");
            e.printStackTrace();
        }
    }
   

}
