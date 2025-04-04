package Logico;

import java.io.Serializable;

public class StatsJugador implements Serializable {

    private static final long serialVersionUID = 1L;

    private int partidosJugados = 0;
    private int puntos;
    private int asistencias;
    private int rebotes;
    private int robos;
    private int bloqueos;
    private int perdidas;
    private int dobles;
    private int triples;
    private int tirosLibres;
    private int faltas;
    private int minutos;
    private Jugador jugador;

    public StatsJugador(Jugador jugador) {
        this.partidosJugados = 0;
        this.puntos  = 0;
        this.asistencias = 0;
        this.rebotes = 0;
        this.robos = 0;
        this.bloqueos = 0;
        this.perdidas = 0;
        this.dobles = 0;
        this.triples = 0;
        this.tirosLibres = 0;
        this.faltas = 0;
        this.jugador = jugador;
    }

    public double valoracion() {
        return puntos + rebotes + asistencias + robos + bloqueos - perdidas;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados += partidosJugados;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos += puntos;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias += asistencias;
    }

    public int getRebotes() {
        return rebotes;
    }

    public void setRebotes(int rebotes) {
        this.rebotes += rebotes;
    }

    public int getRobos() {
        return robos;
    }

    public void setRobos(int robos) {
        this.robos += robos;
    }

    public int getBloqueos() {
        return bloqueos;
    }

    public void setBloqueos(int bloqueos) {
        this.bloqueos += bloqueos;
    }

    public int getPerdidas() {
        return perdidas;
    }

    public void setPerdidas(int perdidas) {
        this.perdidas += perdidas;
    }

    public int getDobles() {
        return dobles;
    }

    public void setDobles(int dobles) {
        this.dobles += dobles;
    }

    public int getTriples() {
        return triples;
    }

    public void setTriples(int triples) {
        this.triples += triples;
    }

    public int getTirosLibres() {
        return tirosLibres;
    }

    public void setTirosLibres(int tirosLibres) {
        this.tirosLibres += tirosLibres;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas += faltas;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos += minutos;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}
