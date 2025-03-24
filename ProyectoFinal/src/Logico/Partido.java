package Logico;

import java.util.ArrayList;
import java.util.Date;

public class Partido {
    private int marcadorLocal; 
    private int marcadorVisit;
    private Equipo equipoLocal;
    private Equipo equipoVisit;
    private StatsEquipo statsLocal;
    private StatsEquipo statsVisit;
    private Date fechaPartido;
    private String ubicacion;
    private ArrayList<Jugador> jugadoresDestacados;
    private int periodoActual;
    private boolean partidoFinalizado;
    
    public Partido(Equipo equipoLocal, Equipo equipoVisit, Date fechaPartido, String ubicacion) {
        super();
        this.equipoLocal = equipoLocal;
        this.equipoVisit = equipoVisit;
        this.fechaPartido = fechaPartido;
        this.ubicacion = ubicacion;
        this.marcadorLocal = 0;
        this.marcadorVisit = 0;
        this.statsLocal = new StatsEquipo(0, 0, 0, 0, 0, 0);
        this.statsVisit = new StatsEquipo(0, 0, 0, 0, 0, 0);
        this.jugadoresDestacados = new ArrayList<Jugador>();
        this.periodoActual = 1;
        this.partidoFinalizado = false;
    }
    
    public void anotarPuntosLocal(int puntos) {
        if (!partidoFinalizado) {
            this.marcadorLocal += puntos;
            this.statsLocal.setPuntos(this.statsLocal.getPuntos() + puntos);
            this.statsVisit.setPuntosContra(this.statsVisit.getPuntosContra() + puntos);
        }
    }
    
    public void anotarPuntosVisitante(int puntos) {
        if (!partidoFinalizado) {
            this.marcadorVisit += puntos;
            this.statsVisit.setPuntos(this.statsVisit.getPuntos() + puntos);
            this.statsLocal.setPuntosContra(this.statsLocal.getPuntosContra() + puntos);
        }
    }
    
    public void finalizarPartido() {
        this.partidoFinalizado = true;
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
        
        equipoLocal.getStats().setPuntos(equipoLocal.getStats().getPuntos() + statsLocal.getPuntos());
        equipoLocal.getStats().setRebotes(equipoLocal.getStats().getRebotes() + statsLocal.getRebotes());
        equipoLocal.getStats().setPuntosContra(equipoLocal.getStats().getPuntosContra() + statsLocal.getPuntosContra());
        
        equipoVisit.getStats().setPuntos(equipoVisit.getStats().getPuntos() + statsVisit.getPuntos());
        equipoVisit.getStats().setRebotes(equipoVisit.getStats().getRebotes() + statsVisit.getRebotes());
        equipoVisit.getStats().setPuntosContra(equipoVisit.getStats().getPuntosContra() + statsVisit.getPuntosContra());
    }
    
    public void agregarJugadorDestacado(Jugador jugador) {
        if (!jugadoresDestacados.contains(jugador)) {
            jugadoresDestacados.add(jugador);
        }
    }
    
    public void avanzarPeriodo() {
        if (!partidoFinalizado && periodoActual < 4) {
            periodoActual++;
        }
    }
    
    public Equipo obtenerGanador() {
        if (!partidoFinalizado) return null;
        if (marcadorLocal > marcadorVisit) {
            return equipoLocal;
        } else if (marcadorVisit > marcadorLocal) {
            return equipoVisit;
        }
        return null;
    }
    
    public String generarResumen() {
        StringBuilder resumen = new StringBuilder();
        resumen.append("Partido: ").append(equipoLocal.getNombre()).append(" vs ").append(equipoVisit.getNombre()).append("\n");
        resumen.append("Resultado: ").append(marcadorLocal).append(" - ").append(marcadorVisit).append("\n");
        resumen.append("Ubicación: ").append(ubicacion).append("\n");
        resumen.append("Fecha: ").append(fechaPartido.toString()).append("\n");
        
        if (partidoFinalizado) {
            Equipo ganador = obtenerGanador();
            if (ganador != null) {
                resumen.append("Ganador: ").append(ganador.getNombre()).append("\n");
            } else {
                resumen.append("Partido terminó en empate\n");
            }
        } else {
            resumen.append("Partido en progreso - Periodo: ").append(periodoActual).append("\n");
        }
        
        resumen.append("\nJugadores destacados:\n");
        for (Jugador jugador : jugadoresDestacados) {
            resumen.append("- ").append(jugador.getNombre()).append(" (").append(jugador.getEquipo().getNombre()).append(")\n");
        }
        
        return resumen.toString();
    }

    public int getMarcadorLocal() {
        return marcadorLocal;
    }

    public void setMarcadorLocal(int marcadorLocal) {
        this.marcadorLocal = marcadorLocal;
    }

    public int getMarcadorVisit() {
        return marcadorVisit;
    }

    public void setMarcadorVisit(int marcadorVisit) {
        this.marcadorVisit = marcadorVisit;
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

    public ArrayList<Jugador> getJugadoresDestacados() {
        return jugadoresDestacados;
    }

    public void setJugadoresDestacados(ArrayList<Jugador> jugadoresDestacados) {
        this.jugadoresDestacados = jugadoresDestacados;
    }

    public int getPeriodoActual() {
        return periodoActual;
    }

    public void setPeriodoActual(int periodoActual) {
        this.periodoActual = periodoActual;
    }

    public boolean isPartidoFinalizado() {
        return partidoFinalizado;
    }

    public void setPartidoFinalizado(boolean partidoFinalizado) {
        this.partidoFinalizado = partidoFinalizado;
    }
}
