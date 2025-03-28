package Logico;
import java.util.ArrayList;

public class StatsJugador {
	private int dobles;
	private int rebotes;
	private int asistencias;
	private int triples;
	private int bloqueos;
	private int tiros;
	private int tirosLibres;
	private int tirosLibresAcert;
	private int tirosAcert;
	private int robos;
	private int perdidas;
	private Jugador jugador;
	private ArrayList<Partido> jugados;
	public StatsJugador(int dobles, int rebotes, int asistencias, int triples, int bloqueos, int tiros, int tirosLibres,
			int tirosLibresAcert, int tirosAcert, int robos, int perdidas, Jugador jugador) {
		super();
		this.dobles = dobles;
		this.rebotes = rebotes;
		this.asistencias = asistencias;
		this.triples = triples;
		this.bloqueos = bloqueos;
		this.tiros = tiros;
		this.tirosLibres = tirosLibres;
		this.tirosLibresAcert = tirosLibresAcert;
		this.tirosAcert = tirosAcert;
		this.robos = robos;
		this.perdidas = perdidas;
		this.jugador = jugador;
		this.setJugados(new ArrayList<Partido>());
	}
	public int getDobles() {
		return dobles;
	}
	public void setDobles(int dobles) {
		this.dobles = dobles;
	}
	public int getRebotes() {
		return rebotes;
	}
	public void setRebotes(int rebotes) {
		this.rebotes = rebotes;
	}
	public int getAsistencias() {
		return asistencias;
	}
	public void setAsistencias(int asistencias) {
		this.asistencias = asistencias;
	}
	public int getTriples() {
		return triples;
	}
	public void setTriples(int triples) {
		this.triples = triples;
	}
	public int getBloqueos() {
		return bloqueos;
	}
	public void setBloqueos(int bloqueos) {
		this.bloqueos = bloqueos;
	}
	public int getTiros() {
		return tiros;
	}
	public void setTiros(int tiros) {
		this.tiros = tiros;
	}
	public int getTirosLibres() {
		return tirosLibres;
	}
	public void setTirosLibres(int tirosLibres) {
		this.tirosLibres = tirosLibres;
	}
	public int getTirosLibresAcert() {
		return tirosLibresAcert;
	}
	public void setTirosLibresAcert(int tirosLibresAcert) {
		this.tirosLibresAcert = tirosLibresAcert;
	}
	public int getTirosAcert() {
		return tirosAcert;
	}
	public void setTirosAcert(int tirosAcert) {
		this.tirosAcert = tirosAcert;
	}
	public int getRobos() {
		return robos;
	}
	public void setRobos(int robos) {
		this.robos = robos;
	}
	public int getPerdidas() {
		return perdidas;
	}
	public void setPerdidas(int perdidas) {
		this.perdidas = perdidas;
	}
	public Jugador getJugador() {
		return jugador;
	}
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	public ArrayList<Partido> getJugados() {
		return jugados;
	}
	public void setJugados(ArrayList<Partido> jugados) {
		this.jugados = jugados;
	}
	public void actualizarStats(int dobles, int rebotes, int asistencias, int triples, int bloqueos, int tiros, int tirosLibres,
			int tirosLibresAcert, int tirosAcert, int robos, int perdidas) {
		this.dobles += dobles;
		this.rebotes += rebotes;
		this.asistencias += asistencias;
		this.triples += triples;
		this.bloqueos += bloqueos;
		this.tiros += tiros;
		this.tirosLibres += tirosLibres;
		this.tirosLibresAcert += tirosLibresAcert;
		this.tirosAcert += tirosAcert;
		this.robos += robos;
		this.perdidas += perdidas;
	}
	public int puntosGenerados() {
		return dobles*2 + triples*3 + tirosLibresAcert;
	}
	
	public int tirosFallados() {
		return tiros - tirosAcert;
	}
	public int tirosLibresFallados() {
		return tirosLibres - tirosLibresAcert;
	}
	
	public float puntosMvp() {
		float valoracion = (puntosGenerados() + asistencias + robos + bloqueos) - (tirosFallados() + perdidas + tirosLibresFallados());
		return valoracion;
	}
	public float eFGPercent() {
		float valoracion = (puntosGenerados() + 0.5f*triples)/tiros;
		return valoracion*100;
	}
	public float eTSPercent() {
		float valoracion = puntosGenerados() / 2*(tiros + 0.5f*tirosLibres);
		return valoracion*100;
	}
	public float assistPerLost() {
		return (asistencias/perdidas)*100;
	}
	public float eFTARate() {
		return (tiros/tirosLibres)*100;
	}
	public float tripleRate() {
		return (tiros/triples)*100;
	}
	public float posesiones() {
		return (tiros - rebotes) + perdidas + (0.44f*tirosLibres);
	}
	public float robosPercent() {
	    float totalPosesionesRival = 0;
	    for (Partido partido : jugados) {
	        float posesionesRival = 0;
	        if (partido.getEquipoLocal().equals(jugador.getEquipo())) {
	            posesionesRival = (partido.getStatsVisit().getTiros() - partido.getStatsVisit().getRebotes()) + partido.getStatsVisit().getPerdidas() + (0.44f * partido.getStatsVisit().getTirosLibres());
	        } else if (partido.getEquipoVisit().equals(jugador.getEquipo())) {
	            posesionesRival = (partido.getStatsLocal().getTiros() - partido.getStatsLocal().getRebotes()) + partido.getStatsLocal().getPerdidas() + (0.44f * partido.getStatsLocal().getTirosLibres());
	        }
	        totalPosesionesRival += posesionesRival;
	    }
	    return (robos / totalPosesionesRival) * 100;
	}

	public float bloqueosPercent() {
	    int totalTirosRival = 0;
	    for (Partido partido : jugados) {
	        if (partido.getEquipoLocal().equals(jugador.getEquipo())) {
	            totalTirosRival += partido.getStatsVisit().getTiros();
	        } else if (partido.getEquipoVisit().equals(jugador.getEquipo())) {
	            totalTirosRival += partido.getStatsLocal().getTiros();
	        }
	    }
	    return (bloqueos / totalTirosRival) * 100;
	}

	public float efDefensiva() {
	    int totalPuntosContra = 0;
	    float totalPosesionesRival = 0;

	    for (Partido partido : jugados) {
	        int puntosContraRival = 0;
	        float posesionesRival = 0;
	        if (partido.getEquipoLocal().equals(jugador.getEquipo())) {
	            puntosContraRival = partido.getStatsVisit().getPuntos();
	            posesionesRival = (partido.getStatsVisit().getTiros() - partido.getStatsVisit().getRebotes()) + partido.getStatsVisit().getPerdidas() + (0.44f * partido.getStatsVisit().getTirosLibres());
	        } else if (partido.getEquipoVisit().equals(jugador.getEquipo())) {
	            puntosContraRival = partido.getStatsLocal().getPuntos();
	            posesionesRival = (partido.getStatsLocal().getTiros() - partido.getStatsLocal().getRebotes()) + partido.getStatsLocal().getPerdidas() + (0.44f * partido.getStatsLocal().getTirosLibres());
	        }
	        totalPuntosContra += puntosContraRival;
	        totalPosesionesRival += posesionesRival;
	    }
	    return totalPuntosContra / totalPosesionesRival;
	}
	
	public float efOfensiva() {
		return (puntosGenerados()/posesiones()) * 100;
	}
	public void agregarPartido(Partido p) {
		jugados.add(p);
	}

}
