package Logico;

import java.util.ArrayList;

public class Equipo {
    private String nombre;
    private String ciudad;
    private ArrayList<Jugador> nomina;
    private ArrayList<Partido> historial;
    private StatsEquipo stats;

   
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
    
    public static Equipo equipoConMasPuntos(ArrayList<Equipo> equipos) {
        if (equipos == null || equipos.isEmpty()) {
            return null; 
        }
        Equipo equipoConMasPuntos = equipos.get(0);

        for (Equipo equipo : equipos) {
            if (equipo.getStats().getPuntos() > equipoConMasPuntos.getStats().getPuntos()) {
                equipoConMasPuntos = equipo;
            }
        }
        return equipoConMasPuntos;
    }
    
    public static Equipo equipoConMasRebotes(ArrayList<Equipo> equipos) {
        if (equipos == null || equipos.isEmpty()) {
            return null;
        }
        Equipo equipoConMasRebotes = equipos.get(0);

        for (Equipo equipo : equipos) {
            if (equipo.getStats().getRebotes() > equipoConMasRebotes.getStats().getRebotes()) {
                equipoConMasRebotes = equipo;
            }
        }
        return equipoConMasRebotes;
    }

}