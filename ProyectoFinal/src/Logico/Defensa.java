package Logico;

public class Defensa extends Jugador{
	protected int rebotes;
	protected int bloqueos;
	protected int tapones;
	protected int tirosCampo;
	protected int robos;
	public Defensa(String nombre, int edad, String nacionalidad, Lesion lesion, Equipo equipo, int dorsal, int rebotes,
			int bloqueos, int tapones, int tirosCampo, int robos) {
		super(nombre, edad, nacionalidad, lesion, equipo, dorsal);
		this.rebotes = rebotes;
		this.bloqueos = bloqueos;
		this.tapones = tapones;
		this.tirosCampo = tirosCampo;
		this.robos = robos;
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
	public int getTapones() {
		return tapones;
	}
	public void setTapones(int tapones) {
		this.tapones = tapones;
	}
	public int getTirosCampo() {
		return tirosCampo;
	}
	public void setTirosCampo(int tirosCampo) {
		this.tirosCampo = tirosCampo;
	}
	public int getRobos() {
		return robos;
	}
	public void setRobos(int robos) {
		this.robos = robos;
	}
	

}
