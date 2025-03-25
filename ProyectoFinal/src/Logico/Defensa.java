package Logico;

public abstract class Defensa extends Jugador{
	protected int rebotes;
	protected int bloqueos;
	protected int tirosConcedidos;
	protected int puntosConcedidos;
	protected int faltas;
	public Defensa(String nombre, int edad, String nacionalidad, Lesion lesion, Equipo equipo, int dorsal, int altura, int rebotes,
			int bloqueos, int tirosConcedidos, int puntosConcedidos, int faltas) {
		super(nombre, edad, nacionalidad, lesion, equipo, dorsal, altura);
		this.rebotes = rebotes;
		this.bloqueos = bloqueos;
		this.tirosConcedidos = tirosConcedidos;
		this.puntosConcedidos = puntosConcedidos;
		this.faltas = faltas;
	}
	public int getRebotes() {
		return rebotes;
	}
	public void setRebotes(int rebotes) {
		this.rebotes = rebotes;
	}
	public int getBloqueos() {
		return bloqueos;
	}
	public void setBloqueos(int bloqueos) {
		this.bloqueos = bloqueos;
	}

	public int getTirosConcedidos() {
		return tirosConcedidos;
	}
	public void setTirosConcedidos(int tirosConcedidos) {
		this.tirosConcedidos = tirosConcedidos;
	}
	public int getPuntosConcedidos() {
		return puntosConcedidos;
	}
	public void setPuntosConcedidos(int puntosConcedidos) {
		this.puntosConcedidos = puntosConcedidos;
	}
	public int getFaltas() {
		return faltas;
	}
	public void setFaltas(int faltas) {
		this.faltas = faltas;
	}
	public abstract float puntosMvp(int minutos);
	public abstract float eficienciaDef(int minutos);
}

