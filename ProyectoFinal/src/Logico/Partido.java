package Logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Partido implements Serializable {

    private static final long serialVersionUID = 1L;

    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private Equipo equipoConPosesion;
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
    public void setDuracion(long duracion) {
        this.duracion = duracion;
    }

    public void inicializarStats() {
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

    public void simularPartido() {
        inicializarStats();
        this.puntosLocal = 0;
        this.puntosVisitante = 0;
        if (generarRandom(1,50) >= 49) {
            this.equipoConPosesion = equipoLocal;
        } else {
            this.equipoConPosesion = equipoVisitante;
        }
        
        System.out.println("\n=== INICIO DEL PARTIDO ===");
        System.out.println("Saque inicial para: " + equipoConPosesion.getNombre());

        long inicio = System.currentTimeMillis();
        int posesionCount = 0;
        int accion = 1;
        Jugador antecesor = null;

        while ((System.currentTimeMillis() - inicio) < duracion) {
            posesionCount++;
            Jugador poseedor = obtenerJugador(equipoConPosesion);
            switch (accion) {
                case 1: // Pase exitoso
                    Jugador receptor = obtenerJugador(equipoConPosesion);
                    registrarPase(poseedor, receptor);
                    break;

                case 2: // Falta en defensa
                	Jugador defensor;
                	if (equipoConPosesion == equipoLocal) {
                	    defensor = obtenerJugador(equipoVisitante);
                	} else {
                	    defensor = obtenerJugador(equipoLocal);
                	}
                	registrarFalta(defensor, poseedor);
                    break;
                case 3: // Falta en ataque
                    registrarFaltaAtaque(poseedor);
                    break;

                case 4: // Anotaci�n
                	int puntos;
                	if (generarRandom(1, 100) <= 65) {
                	    puntos = 2;
                	} else {
                	    puntos = 3;
                	}
                    registrarAnotacion(poseedor, antecesor, puntos);
                    break;

                case 5: // Tiro fallado
                    registrarTiroFallado(poseedor);
                    break;

                case 6: // Robo de bal�n
                    registrarRobo(poseedor);
                    break;

                case 7: // Bal�n fuera
                    registrarBalonFuera();
                    break;

                case 8: // Bloqueo
                    registrarBloqueo(poseedor);
                    break;

                default: // P�rdida com�n
                    registrarPerdida(poseedor);
                    break;
            }
            pausa(1000);
            antecesor = poseedor;
            accion = determinarAccion();
        }
        finalizarPartido();
    }

    private void registrarPase(Jugador emisor, Jugador receptor) {
        StatsJugador statsEmisor = buscarJugador(emisor);
        StatsJugador statsReceptor = buscarJugador(receptor);
        
        System.out.printf("\n[Pase] %s -> %s (%s)", 
            emisor.getNombre(), receptor.getNombre(), equipoConPosesion.getNombre());
    }

    private void registrarFalta(Jugador defensor, Jugador atacante) {
        StatsJugador statsDefensor = buscarJugador(defensor);
        StatsJugador statsAtacante = buscarJugador(atacante);
        
        statsDefensor.setFaltas(1);
        statsAtacante.setTirosLibres(1);
        
        System.out.printf("\n[Falta] %s comete falta sobre %s", 
            defensor.getNombre(), atacante.getNombre());

        if (generarRandom(1, 100) <= 65) {
            statsAtacante.setTirosLibresAcert(1);
            if (equipoConPosesion == equipoLocal) puntosLocal++;
            else puntosVisitante++;
            System.out.printf("\n[Tiros libres] %s anota 1 punto", atacante.getNombre());
        }
        
        cambiarPosesion();
    }

    private void registrarFaltaAtaque(Jugador atacante) {
        StatsJugador stats = buscarJugador(atacante);
        stats.setFaltas(1);
        stats.setPerdidas(1);
        
        System.out.printf("\n[Falta en ataque] %s pierde la posesi�n", atacante.getNombre());
        cambiarPosesion();
    }

    private void registrarAnotacion(Jugador anotador, Jugador asistente, int puntos) {
        StatsJugador stats = buscarJugador(anotador);
        StatsJugador asistidor = buscarJugador(asistente);
        
        String tipoTiro;
        if (puntos == 2) {
            tipoTiro = "doble";
        } else {
            tipoTiro = "triple";
        }
        stats.setTiros(1);
        stats.setTirosAcert(1);
        if (puntos == 2) stats.setDobles(1);
        else stats.setTriples(1);
        asistidor.setAsistencias(1);
        
        if (equipoConPosesion == equipoLocal) puntosLocal += puntos;
        else puntosVisitante += puntos;
        
        System.out.printf("\n[Anotaci�n] %s anota un %s (%d pts) para %s", 
            anotador.getNombre(), tipoTiro, puntos, equipoConPosesion.getNombre());
        
        cambiarPosesion();
        siguienteAccionPase();
    }

    private void registrarTiroFallado(Jugador tirador) {
        StatsJugador stats = buscarJugador(tirador);
        stats.setTiros(1);
        
        System.out.printf("\n[Tiro fallado] %s erra el tiro", tirador.getNombre());
        
        if (generarRandom(1, 100) <= 30) {
            Jugador reboteador = obtenerJugador(equipoConPosesion);
            StatsJugador statsReboteador = buscarJugador(reboteador);
            statsReboteador.setRebotes(1);
            System.out.printf("\n[Rebote ofensivo] %s lo recupera", reboteador.getNombre());
        } else {
            cambiarPosesion();
            Jugador reboteador = obtenerJugador(equipoConPosesion);
            StatsJugador statsReboteador = buscarJugador(reboteador);
            statsReboteador.setRebotesDef(1);
            System.out.printf("\n[Rebote defensivo] %s lo toma para %s", 
                reboteador.getNombre(), equipoConPosesion.getNombre());
        }
    }

    private void registrarRobo(Jugador victima) {
    	Equipo equipoDefensor;
    	if (equipoConPosesion == equipoLocal) {
    	    equipoDefensor = equipoVisitante;
    	} else {
    	    equipoDefensor = equipoLocal;
    	}
    	Jugador ladron = obtenerJugador(equipoDefensor);
    	StatsJugador statsLadron = buscarJugador(ladron);
    	StatsJugador statsVictima = buscarJugador(victima);
        
        statsLadron.setRobos(1);
        statsVictima.setPerdidas(1);
        
        System.out.printf("\n[Robo] %s le roba el bal�n a %s", 
            ladron.getNombre(), victima.getNombre());
        
        cambiarPosesion();
    }

    private void registrarBalonFuera() {
        System.out.printf("\n[Bal�n fuera] %s pierde la posesi�n", equipoConPosesion.getNombre());
        cambiarPosesion();
        siguienteAccionPase();
    }

    private void registrarBloqueo(Jugador tirador) {
    	Equipo equipoBloqueador;
    	if (equipoConPosesion == equipoLocal) {
    	    equipoBloqueador = equipoVisitante;
    	} else {
    	    equipoBloqueador = equipoLocal;
    	}

    	Jugador bloqueador = obtenerJugador(equipoBloqueador);
        StatsJugador statsBloqueador = buscarJugador(bloqueador);
        StatsJugador statsTirador = buscarJugador(tirador);
        
        statsBloqueador.setBloqueos(1);
        
        System.out.printf("\n[Bloqueo] %s bloquea a %s", 
            bloqueador.getNombre(), tirador.getNombre());
        
        if (generarRandom(1, 100) <= 50) {
            Jugador reboteador = obtenerJugador(equipoConPosesion);
            StatsJugador statsReboteador = buscarJugador(reboteador);
            statsReboteador.setRebotes(1);
            System.out.printf("\n[Recuperaci�n] %s mantiene la posesi�n", reboteador.getNombre());
        } else {
            cambiarPosesion();
            Jugador reboteador = obtenerJugador(equipoConPosesion);
            StatsJugador statsReboteador = buscarJugador(reboteador);
            statsReboteador.setRebotesDef(1);
            System.out.printf("\n[Recuperaci�n] %s gana la posesi�n", reboteador.getNombre());
        }
    }

    private void registrarPerdida(Jugador perdedor) {
        StatsJugador stats = buscarJugador(perdedor);
        stats.setPerdidas(1);
        
        System.out.printf("\n[P�rdida] %s pierde el bal�n", perdedor.getNombre());
        cambiarPosesion();
    }

    private void siguienteAccionPase() {
        Jugador poseedor = obtenerJugador(equipoConPosesion);
        Jugador receptor = obtenerJugador(equipoConPosesion);
        registrarPase(poseedor, receptor);
    }

    private void finalizarPartido() {
        this.jugado = true;
        System.out.println("\n\n=== FINAL DEL PARTIDO ===");
        System.out.printf("Resultado: %s %d - %d %s\n", 
            equipoLocal.getNombre(), puntosLocal, puntosVisitante, equipoVisitante.getNombre());
        
        actualizarStatsEquipo();
    }

    private void actualizarStatsEquipo() {
        StatsEquipo statsLocal = new StatsEquipo(equipoLocal);
        StatsEquipo statsVisitante = new StatsEquipo(equipoVisitante);
        
        for (StatsJugador stats : statsJugadores) {
            if (stats.getJugador().getEquipo() == equipoLocal) {
                statsLocal.actualizarStats(
                    stats.getDobles(), stats.getRebotes(), stats.getAsistencias(),
                    stats.getRobos(), stats.getBloqueos(), stats.getTirosLibres(),
                    stats.getTirosLibresAcert(), stats.getTriples()
                );
            } else {
                statsVisitante.actualizarStats(
                    stats.getDobles(), stats.getRebotes(), stats.getAsistencias(),
                    stats.getRobos(), stats.getBloqueos(), stats.getTirosLibres(),
                    stats.getTirosLibresAcert(), stats.getTriples()
                );
            }
        }
        
        if (puntosLocal > puntosVisitante) {
            statsLocal.setVictorias(1);
            statsVisitante.setDerrotas(1);
        } else if (puntosVisitante > puntosLocal) {
            statsVisitante.setVictorias(1);
            statsLocal.setDerrotas(1);
        }
    }

    public int determinarAccion() {
        int num = generarRandom(1, 100);
        if (num >= 1 && num <= 15) 
        	return 1;   // Pase
        else if (num >= 16 && num <= 20) 
        	return 2;  // Falta en defensa
        else if (num >= 21 && num <= 25) 
        	return 3;  // Falta en ataque
        else if (num >= 26 && num <= 45) 
        	return 4;  // Anotaci�n
        else if (num >= 46 && num <= 65) 
        	return 5;  // Tiro fallado
        else if (num >= 66 && num <= 75) 
        	return 6;  // Robo
        else if (num >= 76 && num <= 85) 
        	return 7;  // Bal�n fuera
        else if (num >= 86 && num <= 95) 
        	return 8;  // Bloqueo
        else 
        	return 0;  // P�rdida com�n
    }

    public Jugador obtenerJugador(Equipo equipo) {
        int indice = generarRandom(0, equipo.getNomina().size() - 1);
        return equipo.getNomina().get(indice);
    }
    
    public StatsJugador buscarJugador(Jugador jugador) {
        for (StatsJugador stats : statsJugadores) {
            if (stats.getJugador().equals(jugador)) {
                return stats;
            }
        }
        return null;
    }
    
    private void cambiarPosesion() {
    	if (equipoConPosesion == equipoLocal) {
    	    equipoConPosesion = equipoVisitante;
    	} else {
    	    equipoConPosesion = equipoLocal;
    	}
    }
    
    private void pausa(int milisegundos) {
        try {
            Thread.sleep(milisegundos); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}