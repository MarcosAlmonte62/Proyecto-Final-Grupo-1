package Logico;

import java.io.Serializable;

public class StatsJugador implements Serializable {

	private static final long serialVersionUID = 1L;

    private int partidosJugados;
    private int tiros;
    private int asistencias;
    private int rebotes;
    private int rebotesDef;
    private int robos;
    private int bloqueos;
    private int perdidas;
    private int dobles;
    private int triples;
    private int tirosLibres;
    private int tirosAcert;
    private int tirosLibresAcert;
    private int faltas;
    private float minutos;
    private Jugador jugador;

    public StatsJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public StatsJugador() {
    }

    public StatsJugador(int partidosJugados, int tiros, int asistencias, int rebotes, int rebotesDef,
                        int robos, int bloqueos, int perdidas, int dobles, int triples,
                        int tirosLibres, int tirosAcert, int tirosLibresAcert, int faltas,
                        float minutos, Jugador jugador) {
        this.partidosJugados = partidosJugados;
        this.tiros = tiros;
        this.asistencias = asistencias;
        this.rebotes = rebotes;
        this.rebotesDef = rebotesDef;
        this.robos = robos;
        this.bloqueos = bloqueos;
        this.perdidas = perdidas;
        this.dobles = dobles;
        this.triples = triples;
        this.tirosLibres = tirosLibres;
        this.tirosAcert = tirosAcert;
        this.tirosLibresAcert = tirosLibresAcert;
        this.faltas = faltas;
        this.minutos = minutos;
        this.jugador = jugador;
    }

    // =================== MÉTODOS DE LÓGICA ===================

    
    public void sumarStats(StatsJugador statsPartido) {
        this.partidosJugados += 1;
        this.tiros += statsPartido.getTiros();
        this.tirosAcert += statsPartido.getTirosAcert();
        this.dobles += statsPartido.getDobles();
        this.triples += statsPartido.getTriples();
        this.tirosLibres += statsPartido.getTirosLibres();
        this.tirosLibresAcert += statsPartido.getTirosLibresAcert();
        this.rebotes += statsPartido.getRebotes();
        this.rebotesDef += statsPartido.getRebotesDef();
        this.asistencias += statsPartido.getAsistencias();
        this.robos += statsPartido.getRobos();
        this.bloqueos += statsPartido.getBloqueos();
        this.faltas += statsPartido.getFaltas();
        this.perdidas += statsPartido.getPerdidas();
        this.minutos += statsPartido.getMinutos();
    }
    
    public int getPartidosJugados() {
		return partidosJugados;
	}

	public void setPartidosJugados(int partidosJugados) {
		this.partidosJugados += partidosJugados;
	}

	public int getTiros() {
		return tiros;
	}

	public void setTiros(int tiros) {
		this.tiros += tiros;
	}

	public int getAsistencias() {
		return asistencias;
	}

	public void setAsistencias(int asistencias) {
		this.asistencias += asistencias;
	}

	public int getRebotes() {
		return rebotes;
	}

	public void setRebotes(int rebotes) {
		this.rebotes += rebotes;
	}

	public int getRebotesDef() {
		return rebotesDef;
	}

	public void setRebotesDef(int rebotesDef) {
		this.rebotesDef += rebotesDef;
	}

	public int getRobos() {
		return robos;
	}

	public void setRobos(int robos) {
		this.robos += robos;
	}

	public int getBloqueos() {
		return bloqueos;
	}

	public void setBloqueos(int bloqueos) {
		this.bloqueos += bloqueos;
	}

	public int getPerdidas() {
		return perdidas;
	}

	public void setPerdidas(int perdidas) {
		this.perdidas += perdidas;
	}

	public int getDobles() {
		return dobles;
	}

	public void setDobles(int dobles) {
		this.dobles += dobles;
	}

	public int getTriples() {
		return triples;
	}

	public void setTriples(int triples) {
		this.triples = triples;
	}

	public int getTirosLibres() {
		return tirosLibres;
	}

	public void setTirosLibres(int tirosLibres) {
		this.tirosLibres += tirosLibres;
	}

	public int getTirosAcert() {
		return tirosAcert;
	}

	public void setTirosAcert(int tirosAcert) {
		this.tirosAcert += tirosAcert;
	}

	public int getTirosLibresAcert() {
		return tirosLibresAcert;
	}

	public void setTirosLibresAcert(int tirosLibresAcert) {
		this.tirosLibresAcert += tirosLibresAcert;
	}

	public int getFaltas() {
		return faltas;
	}

	public void setFaltas(int faltas) {
		this.faltas += faltas;
	}

	public float getMinutos() {
		return minutos;
	}

	public void setMinutos(float minutos) {
		this.minutos += minutos;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
    
    public int puntosGenerados() {
        return dobles * 2 + triples * 3 + tirosLibresAcert;
    }

    public int tirosFallados() {
        return tiros - tirosAcert;
    }

    public int tirosLibresFallados() {
        return tirosLibres - tirosLibresAcert;
    }

    public float puntosMvp() {
        float val = (puntosGenerados() + asistencias + robos + bloqueos + rebotes + rebotesDef) -
                    (tirosFallados() + perdidas + tirosLibresFallados() + faltas);
        return Math.max(val, 0);
    }

    public float valoracion() {
        return tiros + rebotes + asistencias + robos + bloqueos - perdidas;
    }

    public float eFGPercent() {
        if (tiros == 0) return 0;
        return (puntosGenerados() + 0.5f * triples) / tiros * 100;
    }

    public float eTSPercent() {
        float intentos = 2 * (tiros + 0.5f * tirosLibres);
        if (intentos == 0) return 0;
        return puntosGenerados() / intentos * 100;
    }

    public float assistPerLost() {
        return perdidas == 0 ? 0 : ((float) asistencias / perdidas) * 100;
    }

    public float eFTARate() {
        return tiros == 0 ? 0 : ((float) tirosLibres / tiros) * 100;
    }

    public float tripleRate() {
        return tiros == 0 ? 0 : ((float) triples / tiros) * 100;
    }

    public float posesiones() {
        float val = tiros - rebotes + perdidas + (0.44f * tirosLibres);
        return Math.max(val, 0);
    }

    public float efOfensiva() {
        float pos = posesiones();
        return pos == 0 ? 0 : (puntosGenerados() / pos) * 100;
    }

    public float impactoDefensivo() {
        return robos + bloqueos + rebotesDef - (faltas * 0.5f);
    }
}
