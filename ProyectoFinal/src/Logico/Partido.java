package Logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Partido extends Thread implements Serializable {

    private static final long serialVersionUID = 1L;

    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int puntosLocal;
    private int puntosVisitante;
    private Date fecha;
    private String ubicacion;
    private long duracion;
    private boolean jugado = false;

    private ArrayList<StatsJugador> statsJugadores;

    public Partido(Equipo equipoLocal, Equipo equipoVisitante) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.statsJugadores = new ArrayList<>();
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public int getPuntosLocal() {
        return puntosLocal;
    }

    public void setPuntosLocal(int puntosLocal) {
        this.puntosLocal = puntosLocal;
    }

    public int getPuntosVisitante() {
        return puntosVisitante;
    }

    public void setPuntosVisitante(int puntosVisitante) {
        this.puntosVisitante = puntosVisitante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public ArrayList<StatsJugador> getStatsJugadoresLocal() {
        return statsJugadores;
    }

    public void setStatsJugadoresLocal(ArrayList<StatsJugador> statsJugadoresLocal) {
        this.statsJugadores = statsJugadoresLocal;
    }

    public boolean isJugado() {
        return jugado;
    }

    public void setJugado(boolean jugado) {
        this.jugado = jugado;
    }
    public long getDuracion() {
    	return duracion;
    }
    public void setJugado(long duracion) {
        this.duracion = duracion;
    }
    
    public void inicializarStats() {
        
        StatsEquipo statsLocal = new StatsEquipo(equipoLocal);
        StatsEquipo statsVisitante = new StatsEquipo(equipoVisitante);
        
        for (Jugador jugador : equipoLocal.getNomina()) {
            statsJugadores.add(new StatsJugador(jugador));
        }

        for (Jugador jugador : equipoVisitante.getNomina()) {
            statsJugadores.add(new StatsJugador(jugador));
        }
    }

    
    public int generarRandom(int minimoValor, int maximoValor) {
    	return (int)(Math.random()*(maximoValor - minimoValor+1) + minimoValor);
    }
    
    public Equipo simularPartido(Equipo local, Equipo visit) {
    	int marcadorLocal = 0;
    	int marcadorVisit = 0;
    	Equipo ganador = null;
    	Equipo posesion = null;
    	Equipo contra = null;
    	Jugador poseedor = null;
    	Jugador antecesor = null;
    	int accion = 1;
    	if(generarRandom(1,50) >= 49) {
    		posesion = local;
    		contra = visit;
    	} else {
    		posesion = visit;
    		contra = local;
    	}
    	poseedor = obtenerJugador(posesion);
    	
    	while((System.currentTimeMillis() - duracion) / 1000 < duracion){
    		switch(determinarAccion()) {
    		case 1:
    			antecesor = poseedor;
    			poseedor = obtenerJugador(posesion);
    			break;
    		case 2:
    			if(posesion.equals(local)) {
    				buscarJugador(obtenerJugador(visit)).setFaltas(1);
    			}
    			else {
    				buscarJugador(obtenerJugador(local)).setFaltas(1);
    			}
    			break;
    		case 3:
    			buscarJugador(poseedor).setPuntos(1);
    			buscarJugador(antecesor).setAsistencias(1);
    			break;
    			default:
    			antecesor = poseedor;
    			buscarJugador(poseedor).setPerdidas(1);	
    			poseedor = obtenerJugador(contra);
    			
    			if(poseedor.equals(local)) {
    				local = posesion;
    				visit = contra;
    			}else {
    				visit = posesion;
    				local = contra;
    			}
    			break;
    		}
    		
    			
    	}
    	
    	return ganador;
    }
    
    public int determinarAccion() {
    	int num = generarRandom(1,100);
    	int accion;
    	if(num>=1 && num<=20) {
    		return 1; //pasar la pelota
    	}
    	else if(num>=21 && num<=30) {
    		return 2; //falta a favor
    	}
    	else if(num>=31 && num<=50) {
    		return 3; //tirar
    	}
    	else {
    		return 0; //perder posesion
    	}
    	
    }
    public Jugador obtenerJugador(Equipo posesion) {
        int indice = generarRandom(0, posesion.getNomina().size() - 1);
        return posesion.getNomina().get(indice);
    }
    
    public StatsJugador buscarJugador(Jugador juga) {
    	for(StatsJugador j : statsJugadores) {
    		if(j.equals(juga.getStats())) {
    			return j;
    		}	
    	}
    	return null;
    }
}