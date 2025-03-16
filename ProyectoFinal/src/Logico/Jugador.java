package Logico;

public class Jugador {
	protected String nombre;
	protected int edad;
	protected String nacionalidad;
	protected Lesion lesion;
	protected Equipo equipo;
	protected int dorsal;
	
	public Jugador(String nombre, int edad, String nacionalidad, Lesion lesion, Equipo equipo, int dorsal) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.nacionalidad = nacionalidad;
		this.lesion = lesion;
		this.equipo = equipo;
		this.dorsal = dorsal;
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
	
}

