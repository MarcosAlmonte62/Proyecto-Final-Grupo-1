package Logico;

import java.io.Serializable;

public class StatsEquipo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int puntos;
    private int dobles;
    private int rebotes;
    private int tiros;
    private int puntosContra;
    private int victorias;
    private int derrotas;
    private int empates;
    private int asistencias;
    private int robos;
    private int tapones;
    private int perdidas;
    private int tirosLibres;
    private int tirosLibresAcert;
    private int triples;
    private int partidosJugados;
    private Equipo equipo;

    public StatsEquipo(Equipo equipo) {
        this.puntos = 0;
        this.dobles = 0;
        this.rebotes = 0;
        this.tiros = 0;
        this.puntosContra = 0;
        this.victorias = 0;
        this.derrotas = 0;
        this.empates = 0;
        this.asistencias = 0;
        this.robos = 0;
        this.tapones = 0;
        this.perdidas = 0;
        this.tirosLibres = 0;
        this.tirosLibresAcert = 0;
        this.triples = 0;
        this.partidosJugados = 0;
        this.equipo = equipo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos += puntos;
    }

    public int getDobles() {
        return dobles;
    }

    public void setDobles(int dobles) {
        this.dobles += dobles;
    }

    public int getRebotes() {
        return rebotes;
    }

    public void setRebotes(int rebotes) {
        this.rebotes += rebotes;
    }

    public int getTiros() {
        return tiros;
    }

    public void setTiros(int tiros) {
        this.tiros += tiros;
    }

    public int getPuntosContra() {
        return puntosContra;
    }

    public void setPuntosContra(int puntosContra) {
        this.puntosContra += puntosContra;
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias += victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas += derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates += empates;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias += asistencias;
    }

    public int getRobos() {
        return robos;
    }

    public void setRobos(int robos) {
        this.robos += robos;
    }

    public int getTapones() {
        return tapones;
    }

    public void setTapones(int tapones) {
        this.tapones += tapones;
    }

    public int getPerdidas() {
        return perdidas;
    }

    public void setPerdidas(int perdidas) {
        this.perdidas += perdidas;
    }

    public int getTirosLibres() {
        return tirosLibres;
    }

    public void setTirosLibres(int tirosLibres) {
        this.tirosLibres += tirosLibres;
    }

    public int getTirosLibresAcert() {
        return tirosLibresAcert;
    }

    public void setTirosLibresAcert(int tirosLibresAcert) {
        this.tirosLibresAcert += tirosLibresAcert;
    }

    public int getTriples() {
        return triples;
    }

    public void setTriples(int triples) {
        this.triples += triples;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados += partidosJugados;
    }

    public int puntuacion() {
        return victorias * 3 + empates;
    }

    public void actualizarStats(int dobles, int rebotes, int asistencias, int robos, int tapones,
                                int tirosLibres, int tirosLibresAcert, int triples) {
        this.dobles += dobles;
        this.rebotes += rebotes;
        this.asistencias += asistencias;
        this.robos += robos;
        this.tapones += tapones;
        this.tirosLibres += tirosLibres;
        this.tirosLibresAcert += tirosLibresAcert;
        this.triples += triples;
    }

    public int puntosGenerados() {
        return dobles * 2 + triples * 3 + tirosLibresAcert;
    }

    public float getPromedioPuntos() {
        return partidosJugados > 0 ? (float) puntos / partidosJugados : 0;
    }

    public float getPorcentajeVictorias() {
        return partidosJugados > 0 ? (float) victorias / partidosJugados * 100 : 0;
    }

    public float getDiferenciaPuntos() {
        return puntos - puntosContra;
    }

    public Jugador jugadorMasPuntos() {
    	Jugador mejor = null;
        int maxPuntos = 0;

        for (Jugador aux : equipo.getNomina()) {
            int puntosTotales = aux.getStats().puntosGenerados();
            if (puntosTotales > maxPuntos) {
                maxPuntos = puntosTotales;
                mejor = aux;
            }
        }

        return mejor;
    }

    public Jugador maxAsistidor() {
    	Jugador mejor = null;
        int maxAsist = 0;

        for (Jugador aux : equipo.getNomina()) {
            int asist = aux.getStats().getAsistencias();
            if (asist > maxAsist) {
                maxAsist = asist;
                mejor = aux;
            }
        }

        return mejor;
    }


    public Jugador mejorDelEquipo() {
    	Jugador mejor = null;
        double max = 0;
        for (Jugador j : equipo.getNomina()) {
            double val = j.getStats().valoracion();
            if (val > max) {
                max = val;
                mejor = j;
            }
        }
        return mejor;
    }

    public float eTSTeam() {
        if (tiros == 0) return 0;
        return ((float) puntosGenerados() + 0.5f * triples) / tiros * 100;
    }

    public float tripleRateTeam() {
        return triples == 0 ? 0 : (float) triples / tiros * 100;
    }

    public Jugador getJugadorMayorPosesion() {
      
        return null;
    }

    public Jugador mejorTirador() {
        return null;
    }

    public Jugador mejorAtacante() {
      
        return null;
    }

    public Jugador mejorDefensa() {
        
        return null;
    }
}