package Logico;

import java.util.ArrayList;
import java.util.Date;

public class SerieNacional {
	
	private ArrayList<Equipo> clasificacion;
	private ArrayList<Partido> calendario;
	private static SerieNacional torneo;
	private static int idPartido;
	private SerieNacional() {
		super();
		this.clasificacion = new ArrayList<Equipo>();
		this.calendario = new ArrayList<Partido>();
		idPartido = 1;
	}
	
	public static SerieNacional getInstance() {
		if(torneo == null) {
			torneo = new SerieNacional();
		}
		return torneo;
	}
	
	public static int getIdPartido() {
		return idPartido;
	}

	public ArrayList<Equipo> getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(ArrayList<Equipo> clasificacion) {
		this.clasificacion = clasificacion;
	}
	public ArrayList<Partido> getCalendario() {
		return calendario;
	}
	public void setCalendario(ArrayList<Partido> partidosJugados) {
		this.calendario = partidosJugados;
	}
	
	public void agregarEquipo(Equipo e) {
		clasificacion.add(e);
		agregarPartidos(e);
		
	}
	public int contarPartidos() {
		int partidos = 0;
		for(Equipo aux : clasificacion) {
			partidos++;
		}
		return partidos*2;
	}
	public void agregarPartidos(Equipo e) {
		for(Equipo aux : clasificacion) {
			if(!aux.equals(e)) {
			calendario.add(new Partido(e, aux, new Date(), e.getCiudad()));
			calendario.add(new Partido(aux, e, new Date(), aux.getCiudad()));
		  }
		}
	}
	
	public void modificarFechaPartido(Partido p, Date fecha) {
		Equipo local = p.getEquipoLocal();
		Equipo visit = p.getEquipoVisit();
		Partido modificado = new Partido(local, visit, fecha, local.getCiudad());
		for(Partido aux : calendario) {
			if(local.getNombre().equalsIgnoreCase(aux.getEquipoLocal().getNombre()) && visit.getNombre().equalsIgnoreCase(aux.getEquipoVisit().getNombre())){
				calendario.remove(aux);
				calendario.add(modificado);
			}
		}
	}
	public Equipo buscarEquipoNombre(String nombre) {
		for(Equipo aux : clasificacion) {
			if(aux.getNombre().equalsIgnoreCase(nombre)) {
				return aux;
			}
		}
		return null;
	}
	public Jugador buscarJugadorNombre(String nombre) {
		for(Equipo aux : clasificacion) {
			for(Jugador player : aux.getNomina()) {
			if(player.getNombre().equalsIgnoreCase(nombre)) {
				return player;
			}
			}
		}
		return null;
	}
	
	public Jugador mejorDelTorneo() {
	    Jugador mejor = null;
	    float maxPuntos = 0;
	    
	    for (Equipo e : clasificacion) {
	    	for(Jugador j : e.getNomina()) {
	        float puntosTotales = j.getStats().posesiones(); 
	        if (puntosTotales > maxPuntos) {
	            maxPuntos = puntosTotales;
	            mejor = j;
	        }
	    	}
	    }
	    return mejor;
	}
	
	
}
