package Logico;

public class Lesion {
	private String tipoLesion;
	private int duracion;
	private boolean lesionado;
	private int gravedad;
	private String descripcion;
	private String tratamiento;
	public Lesion(String tipoLesion, int duracion, boolean lesionado, int gravedad, String descripcion, String tratamiento) {
		super();
		this.tipoLesion = tipoLesion;
		this.duracion = duracion;
		this.lesionado = lesionado;
		this.gravedad = gravedad;
		this.descripcion = descripcion;
		this.tratamiento = tratamiento;
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
	
	public int getGravedad() {
		return gravedad;
	}
	public void setGravedad(int gravedad) {
		this.gravedad = gravedad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTratamiento() {
		return tratamiento;
	}
	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}
	
	public int duracionLesion(String tipo) {
		switch(tipo) {
		case "Esguince de Tobillo":
			this.duracion = 2 * gravedad;
			break;
		case "Capsulitis":
			this.duracion = 1 * gravedad;
			break;
		case "Meniscos":
			this.duracion = 3 * gravedad;
			break;
		case "Ligamentos Cruzados":
			this.duracion = 5 * gravedad;
			break;
		}
		
		return duracion;	
	}
	public void disminuirDuracion(int duracion) {
		this.duracion = duracion - 1;
		if(this.duracion == 0) {
			lesionado = false;
		}
	}

}
