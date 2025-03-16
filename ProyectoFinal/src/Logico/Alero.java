package Logico;

public class Alero extends Atacante{
	private int bloqueos;
	private int rebotes;
	public Alero(String nombre, int edad, String nacionalidad, Lesion lesion, Equipo equipo, int dorsal, int puntos,
			int asistencias, int tiros, int perdidas, int triples, int bloqueos, int rebotes) {
		super(nombre, edad, nacionalidad, lesion, equipo, dorsal, puntos, asistencias, tiros, perdidas, triples);
		this.bloqueos = bloqueos;
		this.rebotes = rebotes;
	}
	public int getBloqueos() {
		return bloqueos;
	}
	public void setBloqueos(int bloqueos) {
		this.bloqueos = bloqueos;
	}
	public int getRebotes() {
		return rebotes;
	}
	public void setRebotes(int rebotes) {
		this.rebotes = rebotes;
	}
}