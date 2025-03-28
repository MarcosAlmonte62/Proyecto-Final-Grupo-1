package Logico;

public class StatsEquipo {
    private int puntos;
    private int dobles;
    private int rebotes;
    private int tiros;
    private int puntosContra;
    private int victorias;
    private int derrotas;
    private int empates;
    private int asistencias;
    private int robos;
    private int tapones;
    private int perdidas;
    private int tirosLibres;
    private int tirosLibresAcert;
    private int triples;
    private int partidosJugados;
    private Equipo equipo;

    public StatsEquipo(int puntos, int dobles, int rebotes, int tiros, int puntosContra, int victorias, int derrotas, int empates, 
                       int asistencias, int robos, int tapones, int perdidas, int tirosLibres, int tirosLibresAcert, int triples, int partidosJugados, Equipo equipo) {
        this.puntos = puntos;
        this.dobles = dobles;
        this.rebotes = rebotes;
        this.tiros = tiros;
        this.puntosContra = puntosContra;
        this.victorias = victorias;
        this.derrotas = derrotas;
        this.empates = empates;
        this.asistencias = asistencias;
        this.robos = robos;
        this.tapones = tapones;
        this.perdidas = perdidas;
        this.tirosLibres = tirosLibres;
        this.tirosLibresAcert = tirosLibresAcert;
        this.triples = triples;
        this.partidosJugados = partidosJugados;
        this.equipo = equipo;
    }

	public int getPuntos() {
		return puntos;
	}

	public void setDobles(int dobles) {
		this.dobles = dobles;
	}
	public int getDobles() {
		return dobles;
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

	public int getTiros() {
		return tiros;
	}

	public void setTiros(int tiros) {
		this.tiros = tiros;
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

	public int getAsistencias() {
		return asistencias;
	}

	public void setAsistencias(int asistencias) {
		this.asistencias = asistencias;
	}

	public int getRobos() {
		return robos;
	}

	public void setRobos(int robos) {
		this.robos = robos;
	}

	public int getTapones() {
		return tapones;
	}

	public void setTapones(int tapones) {
		this.tapones = tapones;
	}

	public int getPartidosJugados() {
		return partidosJugados;
	}

	public void setPartidosJugados(int partidosJugados) {
		this.partidosJugados = partidosJugados;
	}
	public int getPerdidas() {
		return perdidas;
	}

	public void setPerdidas(int perdidas) {
		this.perdidas = perdidas;
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

	public int getTriples() {
		return triples;
	}

	public void setTriples(int triples) {
		this.triples = triples;
	}
	public void actualizarStats(int dobles, int rebotes, int asistencias, int robos, int tapones, int tirosLibres, int tirosLibresAcert, int triples) {
        this.dobles += dobles;
        this.rebotes += rebotes;
        this.asistencias += asistencias;
        this.robos += robos;
        this.tapones += tapones;
        this.tirosLibres += tirosLibres;
        this.tirosLibresAcert += tirosLibresAcert;
        this.triples += triples;
	}
	
	public int puntosGenerados() {
		return dobles*2 + triples*3 + tirosLibresAcert;
	}

	public float getPromedioPuntos() {
        return puntos / partidosJugados;
    }

    public float getPorcentajeVictorias() {
        return victorias / partidosJugados * 100;
    }

    public float getDiferenciaPuntos() {
        return puntos - puntosContra;
    }
    public Jugador mejorDelEquipo() {
	    Jugador mejor = null;
	    float maxPuntos = 0;
	    
	    for (Jugador aux : equipo.getNomina()) {
	        float puntosTotales = aux.getStats().puntosMvp(); 
	        if (puntosTotales > maxPuntos) {
	            maxPuntos = puntosTotales;
	            mejor = aux;
	        }
	    }
	    return mejor;
    }
    public Jugador jugadorMasPuntos() {
	    Jugador mejor = null;
	    int maxPuntos = 0;
	    
	    for (Jugador aux : equipo.getNomina()) {
	        int puntosTotales = aux.getStats().puntosGenerados(); 
	        if (puntosTotales > maxPuntos) {
	            maxPuntos = puntosTotales;
	            mejor = aux;
	        }
	    }
	    return mejor;
	}
    public Jugador mejorTirador() {
	    Jugador mejor = null;
	    float maxPuntos = 0;
	    
	    for (Jugador aux : equipo.getNomina()) {
	        float puntosTotales = aux.getStats().eTSPercent(); 
	        if (puntosTotales > maxPuntos) {
	            maxPuntos = puntosTotales;
	            mejor = aux;
	        }
	    }
	    return mejor;
    }
    public Jugador maxAsistidor() {
	    Jugador mejor = null;
	    int maxPuntos = 0;
	    
	    for (Jugador aux : equipo.getNomina()) {
	        int puntosTotales = aux.getStats().getAsistencias(); 
	        if (puntosTotales > maxPuntos) {
	            maxPuntos = puntosTotales;
	            mejor = aux;
	        }
	    }
	    return mejor;
    }
    public Jugador mejorAtacante() {
	    Jugador mejor = null;
	    float maxPuntos = 0;
	    
	    for (Jugador aux : equipo.getNomina()) {
	        float puntosTotales = aux.getStats().efOfensiva(); 
	        if (puntosTotales > maxPuntos) {
	            maxPuntos = puntosTotales;
	            mejor = aux;
	        }
	    }
	    return mejor;
    }
    public Jugador mejorDefensa() {
	    Jugador mejor = null;
	    float maxPuntos = 0;
	    
	    for (Jugador aux : equipo.getNomina()) {
	        float puntosTotales = aux.getStats().efDefensiva(); 
	        if (puntosTotales > maxPuntos) {
	            maxPuntos = puntosTotales;
	            mejor = aux;
	        }
	    }
	    return mejor;
    }
    public Jugador jugMayorPosesion() {
	    Jugador mejor = null;
	    float maxPuntos = 0;
	    
	    for (Jugador aux : equipo.getNomina()) {
	        float puntosTotales = aux.getStats().posesiones(); 
	        if (puntosTotales > maxPuntos) {
	            maxPuntos = puntosTotales;
	            mejor = aux;
	        }
	    }
	    return mejor;
    }
    public float eTSTeam() {
		float valoracion = (puntosGenerados() + 0.5f*triples)/tiros;
		return valoracion*100;
    }
    public float tripleRateTeam() {
    	return (tiros/triples)*100;
    }
    
    
}
