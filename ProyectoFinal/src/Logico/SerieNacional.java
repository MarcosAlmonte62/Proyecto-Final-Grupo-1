package Logico;

import java.util.ArrayList;

public class SerieNacional {
	
	private ArrayList<Equipo> clasificacion;
	private ArrayList<Partido> partidosJugados;
	public SerieNacional() {
		super();
		this.clasificacion = new ArrayList<Equipo>();
		this.partidosJugados = new ArrayList<Partido>();
	}

	public ArrayList<Equipo> getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(ArrayList<Equipo> clasificacion) {
		this.clasificacion = clasificacion;
	}
	public ArrayList<Partido> getPartidosJugados() {
		return partidosJugados;
	}
	public void setPartidosJugados(ArrayList<Partido> partidosJugados) {
		this.partidosJugados = partidosJugados;
	}
}
