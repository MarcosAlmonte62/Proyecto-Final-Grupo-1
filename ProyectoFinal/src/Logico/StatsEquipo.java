package Logico;

public class StatsEquipo {
	private int puntos;
	private int rebotes;
	private int puntosContra;
	private int victorias;
	private int derrotas;
	private int empates;
	public StatsEquipo(int puntos, int rebotes, int puntosContra, int victorias, int derrotas, int empates) {
		super();
		this.puntos = puntos;
		this.rebotes = rebotes;
		this.puntosContra = puntosContra;
		this.victorias = victorias;
		this.derrotas = derrotas;
		this.empates = empates;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public int getRebotes() {
		return rebotes;
	}
	public void setRebotes(int rebotes) {
		this.rebotes = rebotes;
	}
	public int getPuntosContra() {
		return puntosContra;
	}
	public void setPuntosContra(int puntosContra) {
		this.puntosContra = puntosContra;
	}
	public int getVictorias() {
		return victorias;
	}
	public void setVictorias(int victorias) {
		this.victorias = victorias;
	}
	public int getDerrotas() {
		return derrotas;
	}
	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}
	public int getEmpates() {
		return empates;
	}
	public void setEmpates(int empates) {
		this.empates = empates;
	}

}
