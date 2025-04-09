package Logico;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class Equipo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String ciudad;
    private String estadio;
    private String entrenador;
    private String propietario;
    private int palmares; // cantidad de trofeos
    private Date fundacion;
    private ArrayList<Jugador> nomina;
    private ArrayList<Partido> historial;
    private StatsEquipo stats;

    // Constructor completo
    public Equipo(String nombre, String ciudad, String estadio, String entrenador, String propietario, int palmares, Date fundacion) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.estadio = estadio;
        this.entrenador = entrenador;
        this.propietario = propietario;
        this.palmares = palmares;
        this.fundacion = fundacion;
        this.nomina = new ArrayList<>();
        this.historial = new ArrayList<>();
        this.stats = new StatsEquipo(this);
    }

    // Constructor vacío para flexibilidad con setters
    public Equipo() {
        this.nomina = new ArrayList<>();
        this.historial = new ArrayList<>();
        this.stats = null; // Se puede setear luego con setStats()
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(String entrenador) {
        this.entrenador = entrenador;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public int getPalmares() {
        return palmares;
    }

    public void setPalmares(int palmares) {
        this.palmares = palmares;
    }

    public Date getFundacion() {
        return fundacion;
    }

    public void setFundacion(Date fundacion) {
        this.fundacion = fundacion;
    }

    public ArrayList<Jugador> getNomina() {
        return nomina;
    }

    public void setNomina(ArrayList<Jugador> nomina) {
        this.nomina = nomina;
    }

    public ArrayList<Partido> getHistorial() {
        return historial;
    }

    public void setHistorial(ArrayList<Partido> historial) {
        this.historial = historial;
    }

    public StatsEquipo getStats() {
        return stats;
    }

    public void setStats(StatsEquipo stats) {
        this.stats = stats;
    }

    public void agregarJugador(Jugador j) {
        nomina.add(j);
    }

    public void agregarAlHistorial(Partido p) {
        historial.add(p);
    }
}
