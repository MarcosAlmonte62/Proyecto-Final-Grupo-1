package Logico;

public class Atacante extends Jugador{
	protected int puntos;
	protected int asistencias;
	protected int tiros;
	protected int perdidas;
	protected int triples;
	public Atacante(String nombre, int edad, String nacionalidad, Lesion lesion, Equipo equipo, int dorsal, int puntos,
			int asistencias, int tiros, int perdidas, int triples) {
		super(nombre, edad, nacionalidad, lesion, equipo, dorsal);
		this.puntos = puntos;
		this.asistencias = asistencias;
		this.tiros = tiros;
		this.perdidas = perdidas;
		this.triples = triples;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public int getAsistencias() {
		return asistencias;
	}
	public void setAsistencias(int asistencias) {
		this.asistencias = asistencias;
	}
	public int getTiros() {
		return tiros;
	}
	public void setTiros(int tiros) {
		this.tiros = tiros;
	}
	public int getPerdidas() {
		return perdidas;
	}
	public void setPerdidas(int perdidas) {
		this.perdidas = perdidas;
	}
	public int getTriples() {
		return triples;
	}
	public void setTriples(int triples) {
		this.triples = triples;
	}
	



}

