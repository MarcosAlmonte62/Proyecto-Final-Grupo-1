package Logico;

public class Jugador {
	private String nombre;
	private int edad;
	private String nacionalidad;
	private Lesion lesion;
	private Equipo equipo;
	private int dorsal;
	private int altura;
	private String posicion;
	
	
	public Jugador(String nombre, int edad, String nacionalidad, Lesion lesion, Equipo equipo, int dorsal, int altura, String posicion) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.nacionalidad = nacionalidad;
		this.lesion = lesion;
		this.equipo = equipo;
		this.dorsal = dorsal;
		this.altura = altura;
		this.setPosicion(posicion);
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
	
}

