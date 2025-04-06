package Logico;

import java.io.Serializable;

public class StatsJugador implements Serializable {

    private static final long serialVersionUID = 1L;

    private int partidosJugados = 0;
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
        this.partidosJugados = 0;
        this.tiros  = 0;
        this.asistencias = 0;
        this.rebotes = 0;
        this.rebotesDef = 0;
        this.robos = 0;
        this.bloqueos = 0;
        this.perdidas = 0;
        this.dobles = 0;
        this.triples = 0;
        this.tirosLibres = 0;
        this.faltas = 0;
        this.minutos = 0;
        this.jugador = jugador;
    }

    public double valoracion() {
        return tiros + rebotes + asistencias + robos + bloqueos - perdidas;
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
        this.triples += triples;
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
		return dobles*2 + triples*3 + tirosLibresAcert;
	}
	
	public int tirosFallados() {
		return tiros - tirosAcert;
	}
	public int tirosLibresFallados() {
		return tirosLibres - tirosLibresAcert;
	}
	public float puntosMvp() {
		float valoracion = (puntosGenerados() + asistencias + robos + bloqueos + rebotes + rebotesDef) - (tirosFallados() + perdidas + tirosLibresFallados() + faltas);
		if(valoracion < 0)
			return 0;
		return valoracion;
	}
	public float eFGPercent() {
	    if(tiros == 0) 
	    	return 0;
	    float valoracion = (puntosGenerados() + 0.5f * triples) / tiros;
	    return valoracion * 100;
	}

	public float eTSPercent() {
	    float lanzados = 2 * (tiros + 0.5f * tirosLibres);
	    if(lanzados == 0) 
	    	return 0;
	    float valoracion = puntosGenerados() / lanzados;
	    return valoracion * 100;
	}

	public float assistPerLost() {
	    if(perdidas == 0) 
	    	return 0;
	    return (asistencias / perdidas) * 100;
	}

	public float eFTARate() {
	    if(tiros == 0) 
	    	return 0;
	    return (tirosLibres / tiros) * 100;
	}

	public float tripleRate() {
	    return (triples / tiros) * 100;
	}

	public float posesiones() {
		float valoracion = tiros - rebotes + perdidas + (0.44f*tirosLibres);
		if(valoracion < 0)
		return 0;
		
		return valoracion;
	}
	public float efOfensiva() {
		if(posesiones() != 0)
		return (puntosGenerados()/posesiones())*100;
		return 0;
	}
	public float impactoDefensivo() {
		return robos + bloqueos + rebotesDef - (faltas * 0.5f);
	}
	
}
