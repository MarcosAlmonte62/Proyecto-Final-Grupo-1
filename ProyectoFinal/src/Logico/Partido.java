package Logico;

import java.util.ArrayList;
import java.util.Date;

public class Partido {

    private int marcadorLocal;
    private int marcadorVisit;
    private Equipo equipoLocal;
    private Equipo equipoVisit;
    private StatsEquipo statsLocal;
    private StatsEquipo statsVisit;
    private Date fechaPartido;
    private String ubicacion;
    private ArrayList<Jugador> jugadoresDestacados;
    private int periodoActual;
    private boolean partidoFinalizado;

    public int getMarcadorLocal() {
        return marcadorLocal;
    }

    public void setMarcadorLocal(int marcadorLocal) {
        this.marcadorLocal = marcadorLocal;
    }

    public int getMarcadorVisit() {
        return marcadorVisit;
    }

    public void setMarcadorVisit(int marcadorVisit) {
        this.marcadorVisit = marcadorVisit;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisit() {
        return equipoVisit;
    }

    public void setEquipoVisit(Equipo equipoVisit) {
        this.equipoVisit = equipoVisit;
    }

    public StatsEquipo getStatsLocal() {
        return statsLocal;
    }

    public void setStatsLocal(StatsEquipo statsLocal) {
        this.statsLocal = statsLocal;
    }

    public StatsEquipo getStatsVisit() {
        return statsVisit;
    }

    public void setStatsVisit(StatsEquipo statsVisit) {
        this.statsVisit = statsVisit;
    }

    public Date getFechaPartido() {
        return fechaPartido;
    }

    public void setFechaPartido(Date fechaPartido) {
        this.fechaPartido = fechaPartido;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public ArrayList<Jugador> getJugadoresDestacados() {
        return jugadoresDestacados;
    }

    public void setJugadoresDestacados(ArrayList<Jugador> jugadoresDestacados) {
        this.jugadoresDestacados = jugadoresDestacados;
    }

    public int getPeriodoActual() {
        return periodoActual;
    }

    public void setPeriodoActual(int periodoActual) {
        this.periodoActual = periodoActual;
    }

    public boolean isPartidoFinalizado() {
        return partidoFinalizado;
    }

    public void setPartidoFinalizado(boolean partidoFinalizado) {
        this.partidoFinalizado = partidoFinalizado;
    }

    public Partido(Equipo equipoLocal, Equipo equipoVisit, Date fechaPartido, String ubicacion) {
        this.equipoLocal = equipoLocal;
        this.equipoVisit = equipoVisit;
        this.fechaPartido = fechaPartido;
        this.ubicacion = ubicacion;
        this.marcadorLocal = 0;
        this.marcadorVisit = 0;
        this.statsLocal = new StatsEquipo(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, equipoLocal);
        this.statsVisit = new StatsEquipo(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, equipoVisit);
        this.jugadoresDestacados = new ArrayList<Jugador>();
        this.periodoActual = 1;
        this.partidoFinalizado = false;
        for (Jugador j : equipoLocal.getNomina()) {
            j.setStats(new StatsJugador(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, j));
            j.getStats().getJugados().add(this); 
        }
        for (Jugador j : equipoVisit.getNomina()) {
            j.setStats(new StatsJugador(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, j));
            j.getStats().getJugados().add(this);
        }
    }
    public void finalizarPartido() {
        this.partidoFinalizado = true;
        if (marcadorLocal > marcadorVisit) {
            statsLocal.setVictorias(statsLocal.getVictorias() + 1);
            statsVisit.setDerrotas(statsVisit.getDerrotas() + 1);
        } else if (marcadorVisit > marcadorLocal) {
            statsVisit.setVictorias(statsVisit.getVictorias() + 1);
            statsLocal.setDerrotas(statsLocal.getDerrotas() + 1);
        } else {
            statsLocal.setEmpates(statsLocal.getEmpates() + 1);
            statsVisit.setEmpates(statsVisit.getEmpates() + 1);
        }
        
        for(Jugador aux : equipoLocal.getNomina()) {
        	StatsJugador stats = aux.getStats();
        	stats.actualizarStats(stats.getDobles(), stats.getRebotes(), stats.getAsistencias(), stats.getTriples(), stats.getBloqueos(), stats.getTiros(), stats.getTirosLibres(), stats.getTirosLibresAcert(), stats.getTirosAcert(), stats.getRobos(), stats.getPerdidas());
        }
        for(Jugador aux : equipoVisit.getNomina()) {
        	StatsJugador stats = aux.getStats();
        	stats.actualizarStats(stats.getDobles(), stats.getRebotes(), stats.getAsistencias(), stats.getTriples(), stats.getBloqueos(), stats.getTiros(), stats.getTirosLibres(), stats.getTirosLibresAcert(), stats.getTirosAcert(), stats.getRobos(), stats.getPerdidas());
        }
        StatsEquipo stats = equipoLocal.getStats();
        stats.actualizarStats(stats.getDobles(), stats.getRebotes(), stats.getAsistencias(), stats.getRobos(), stats.getTapones(), stats.getTirosLibres(), stats.getTirosLibresAcert(), stats.getTriples());
        stats = equipoVisit.getStats();
        stats.actualizarStats(stats.getDobles(), stats.getRebotes(), stats.getAsistencias(), stats.getRobos(), stats.getTapones(), stats.getTirosLibres(), stats.getTirosLibresAcert(), stats.getTriples());
    }
  
    public void agregarJugadorDestacado(Jugador jugador) {
        if (!jugadoresDestacados.contains(jugador)) {
            jugadoresDestacados.add(jugador);
        }
    }

    public Equipo obtenerGanador() {
        if (!partidoFinalizado) return null;
        return marcadorLocal > marcadorVisit ? equipoLocal : 
            marcadorVisit > marcadorLocal ? equipoVisit : null;
    }
}