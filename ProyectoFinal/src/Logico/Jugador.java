package Logico;

import java.sql.Date;

public class Jugador {
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
	private Date fechaNacimiento;
	
	
	public Jugador(String nombre, int edad, String nacionalidad, Lesion lesion, Equipo equipo, StatsJugador stats, int dorsal, int altura, int peso, String posicion, Date fechaNacimiento) {
		super();
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
		this.fechaNacimiento = fechaNacimiento;
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
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public StatsJugador getStats() {
		return stats;
	}
	public void setStats(StatsJugador stats) {
		this.stats = stats;
	}
	
}

