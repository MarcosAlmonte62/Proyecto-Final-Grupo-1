package Logico;

import java.io.Serializable;
import java.util.Date;

public class Jugador implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private int edad;
    private String nacionalidad;
    private Lesion lesion;
    private Equipo equipo;
    private StatsJugador stats;
    private int dorsal;
    private int altura;
    private int peso;
    private String posicion;
    private Date fechaRegistro;
    private float rating;

    public Jugador(String nombre, int edad, String nacionalidad, Lesion lesion, Equipo equipo,
                   StatsJugador stats, int dorsal, int altura, int peso, String posicion, Date fechaRegistro) {
        this.nombre = nombre;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
        this.lesion = lesion;
        this.equipo = equipo;
        this.stats = stats;
        this.dorsal = dorsal;
        this.altura = altura;
        this.peso = peso;
        this.posicion = posicion;
        this.fechaRegistro = fechaRegistro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Lesion getLesion() {
        return lesion;
    }

    public void setLesion(Lesion lesion) {
        this.lesion = lesion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public StatsJugador getStats() {
        return stats;
    }

    public void setStats(StatsJugador stats) {
        this.stats = stats;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}