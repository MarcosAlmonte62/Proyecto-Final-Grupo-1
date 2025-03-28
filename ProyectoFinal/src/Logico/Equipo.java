package Logico;

import java.util.ArrayList;

public class Equipo {
    private String nombre;
    private String ciudad;
    private ArrayList<Jugador> nomina;
    private ArrayList<Partido> historial;
    private StatsEquipo stats;

    // Getters y Setters necesarios
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