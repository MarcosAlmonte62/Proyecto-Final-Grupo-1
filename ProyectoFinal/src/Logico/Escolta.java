package Logico;

public class Escolta extends Atacante{
	private int tirosLibres;
	private int tirosLibresScored;
	public Escolta(String nombre, int edad, String nacionalidad, Lesion lesion, Equipo equipo, int dorsal, int puntos,
			int asistencias, int tiros, int perdidas, int triples, int tirosLibres, int tirosLibresScored) {
		super(nombre, edad, nacionalidad, lesion, equipo, dorsal, puntos, asistencias, tiros, perdidas, triples);
		this.tirosLibres = tirosLibres;
		this.tirosLibresScored = tirosLibresScored;
	}
	public int getTirosLibres() {
		return tirosLibres;
	}
	public void setTirosLibres(int tirosLibres) {
		this.tirosLibres = tirosLibres;
	}
	public int getTirosLibresScored() {
		return tirosLibresScored;
	}
	public void setTirosLibresScored(int tirosLibresScored) {
		this.tirosLibresScored = tirosLibresScored;
	}
	

}
