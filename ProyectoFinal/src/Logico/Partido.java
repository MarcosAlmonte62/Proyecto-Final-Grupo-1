package Logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Partido implements Serializable {

    private static final long serialVersionUID = 1L;

    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int puntosLocal;
    private int puntosVisitante;
    private Date fecha;
    private String ubicacion;
    private boolean jugado = false;

    private ArrayList<StatsJugador> statsJugadores;

    public Partido(Equipo equipoLocal, Equipo equipoVisitante) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.statsJugadores = new ArrayList<>();
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public int getPuntosLocal() {
        return puntosLocal;
    }

    public void setPuntosLocal(int puntosLocal) {
        this.puntosLocal = puntosLocal;
    }

    public int getPuntosVisitante() {
        return puntosVisitante;
    }

    public void setPuntosVisitante(int puntosVisitante) {
        this.puntosVisitante = puntosVisitante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public ArrayList<StatsJugador> getStatsJugadores() {
        return statsJugadores;
    }

    public void setStatsJugadores(ArrayList<StatsJugador> statsJugadores) {
        this.statsJugadores = statsJugadores;
    }

    public boolean isJugado() {
        return jugado;
    }

    public void setJugado(boolean jugado) {
        this.jugado = jugado;
    }
}
