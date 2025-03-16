package Logico;

public class Partido {
	private int marcadorLocal; 
	private int marcadorVisit;
	private Equipo equipoLocal;
	private Equipo equipoVisit;
	private StatsEquipo statsLocal;
	private StatsEquipo statsVisit;
	public Partido(int marcadorLocal, int marcadorVisit, Equipo equipoLocal, Equipo equipoVisit, StatsEquipo statsLocal,
			StatsEquipo statsVisit) {
		super();
		this.marcadorLocal = marcadorLocal;
		this.marcadorVisit = marcadorVisit;
		this.equipoLocal = equipoLocal;
		this.equipoVisit = equipoVisit;
		this.statsLocal = statsLocal;
		this.statsVisit = statsVisit;
	}
	public int getMarcadorLocal() {
		return marcadorLocal;
	}
	public void setMarcadorLocal(int marcadorLocal) {
		this.marcadorLocal = marcadorLocal;
	}
	public int getMarcadorVisit() {
		return marcadorVisit;
	}
	public void setMarcadorVisit(int marcadorVisit) {
		this.marcadorVisit = marcadorVisit;
	}
	public Equipo getEquipoLocal() {
		return equipoLocal;
	}
	public void setEquipoLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}
	public Equipo getEquipoVisit() {
		return equipoVisit;
	}
	public void setEquipoVisit(Equipo equipoVisit) {
		this.equipoVisit = equipoVisit;
	}
	public StatsEquipo getStatsLocal() {
		return statsLocal;
	}
	public void setStatsLocal(StatsEquipo statsLocal) {
		this.statsLocal = statsLocal;
	}
	public StatsEquipo getStatsVisit() {
		return statsVisit;
	}
	public void setStatsVisit(StatsEquipo statsVisit) {
		this.statsVisit = statsVisit;
	}

}
