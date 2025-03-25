package Logico;

public abstract class Atacante extends Jugador{
	protected int puntos;
	protected int asistencias;
	protected int tiros;
	protected int perdidas;
	protected int robos;
	public Atacante(String nombre, int edad, String nacionalidad, Lesion lesion, Equipo equipo, int dorsal, int altura, int puntos,
			int asistencias, int tiros, int perdidas, int robos) {
		super(nombre, edad, nacionalidad, lesion, equipo, dorsal, altura);
		this.puntos = puntos;
		this.asistencias = asistencias;
		this.tiros = tiros;
		this.perdidas = perdidas;
		this.robos = robos;
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
	public int getRobos() {
		return robos;
	}
	public void setRobos(int robos) {
		this.robos = robos;
	}
	@Override
	public abstract float puntosMvp(int minutos);
	public abstract float eficienciaOf();
	public abstract int puntosGenerados();
	public abstract float eFGPercent();
	



}

