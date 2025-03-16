package Logico;

public class Lesion {
	private String tipoLesion;
	private int duracion;
	private boolean lesionado;
	public Lesion(String tipoLesion, int duracion, boolean lesionado) {
		super();
		this.tipoLesion = tipoLesion;
		this.duracion = duracion;
		this.lesionado = lesionado;
	}
	public String getTipoLesion() {
		return tipoLesion;
	}
	public void setTipoLesion(String tipoLesion) {
		this.tipoLesion = tipoLesion;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public boolean isLesionado() {
		return lesionado;
	}
	public void setLesionado(boolean lesionado) {
		this.lesionado = lesionado;
	}

}
