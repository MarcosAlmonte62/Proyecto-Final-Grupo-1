package Logico;
import java.util.Date;

public class Partido {

	private int id;
    private int marcadorLocal;
    private int marcadorVisit;
    private Equipo equipoLocal;
    private Equipo equipoVisit;
    private StatsEquipo statsLocal;
    private StatsEquipo statsVisit;
    private Date fechaPartido;
    private String ubicacion;
    private boolean finalizado = false;

    public int getId() {
		return id;
	}

	public int getMarcadorLocal() {
        return marcadorLocal;
    }

    public int getMarcadorVisit() {
        return marcadorVisit;
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

    public Date getFechaPartido() {
        return fechaPartido;
    }

    public void setFechaPartido(Date fechaPartido) {
        this.fechaPartido = fechaPartido;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }


    public Partido(Equipo equipoLocal, Equipo equipoVisit, Date fechaPartido, String ubicacion) {
        this.equipoLocal = equipoLocal;
        this.equipoVisit = equipoVisit;
        this.fechaPartido = fechaPartido;
        this.ubicacion = ubicacion;
        for (Jugador j : equipoLocal.getNomina()) {
        	this.marcadorLocal += j.getStats().puntosGenerados();
        }
        for (Jugador j : equipoVisit.getNomina()) {
        	this.marcadorVisit += j.getStats().puntosGenerados();
        }
    }
    public void finalizarPartido() {
        if (marcadorLocal > marcadorVisit) {
            statsLocal.setVictorias(statsLocal.getVictorias() + 1);
            statsVisit.setDerrotas(statsVisit.getDerrotas() + 1);
        } else if (marcadorVisit > marcadorLocal) {
            statsVisit.setVictorias(statsVisit.getVictorias() + 1);
            statsLocal.setDerrotas(statsLocal.getDerrotas() + 1);
        } else {
            statsLocal.setEmpates(statsLocal.getEmpates() + 1);
            statsVisit.setEmpates(statsVisit.getEmpates() + 1);
        }
        
        for(Jugador aux : equipoLocal.getNomina()) {
        	StatsJugador stats = aux.getStats();
        	stats.actualizarStats(stats.getDobles(), stats.getRebotes(), stats.getAsistencias(), stats.getTriples(), stats.getBloqueos(), stats.getTiros(), stats.getTirosLibres(), stats.getTirosLibresAcert(), stats.getTirosAcert(), stats.getRobos(), stats.getPerdidas());
        }
        for(Jugador aux : equipoVisit.getNomina()) {
        	StatsJugador stats = aux.getStats();
        	stats.actualizarStats(stats.getDobles(), stats.getRebotes(), stats.getAsistencias(), stats.getTriples(), stats.getBloqueos(), stats.getTiros(), stats.getTirosLibres(), stats.getTirosLibresAcert(), stats.getTirosAcert(), stats.getRobos(), stats.getPerdidas());
        }
        StatsEquipo stats = equipoLocal.getStats();
        stats.actualizarStats(stats.getDobles(), stats.getRebotes(), stats.getAsistencias(), stats.getRobos(), stats.getTapones(), stats.getTirosLibres(), stats.getTirosLibresAcert(), stats.getTriples());
        stats = equipoVisit.getStats();
        stats.actualizarStats(stats.getDobles(), stats.getRebotes(), stats.getAsistencias(), stats.getRobos(), stats.getTapones(), stats.getTirosLibres(), stats.getTirosLibresAcert(), stats.getTriples());
        this.finalizado = true;
    }
    
    public void setMarcador(int marcadorLocal, int marcadorVisit) {
        this.marcadorLocal = marcadorLocal;
        this.marcadorVisit = marcadorVisit;
    }

    public Equipo obtenerGanador() {
        return marcadorLocal > marcadorVisit ? equipoLocal : 
            marcadorVisit > marcadorLocal ? equipoVisit : null;
    }
    public boolean isFinalizado() {
        return finalizado;
    }
    
    public boolean esEmpate() {
        return marcadorLocal == marcadorVisit;
    }

}