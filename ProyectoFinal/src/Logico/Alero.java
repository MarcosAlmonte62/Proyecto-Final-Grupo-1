package Logico;

public class Alero extends Atacante{
	private int bloqueos;
	private int rebotes;
	private int faltas;
	public Alero(String nombre, int edad, String nacionalidad, Lesion lesion, Equipo equipo, int dorsal, int altura, int puntos,
	        int asistencias, int tiros, int perdidas, int robos, int triples, int bloqueos, int rebotes, int faltas) {
	    super(nombre, edad, nacionalidad, lesion, equipo, dorsal, altura, puntos, asistencias, tiros, perdidas, robos);
	    this.bloqueos = bloqueos;
	    this.rebotes = rebotes;
	    this.faltas = faltas;
	}

	public int getBloqueos() {
		return bloqueos;
	}
	public void setBloqueos(int bloqueos) {
		this.bloqueos = bloqueos;
	}
	public int getRebotes() {
		return rebotes;
	}
	public void setRebotes(int rebotes) {
		this.rebotes = rebotes;
	}
	public int getFaltas() {
		return faltas;
	}

	public void setFaltas(int faltas) {
		this.faltas = faltas;
	} 

	@Override
	public float puntosMvp(int minutos) {
		int tirosFallados = puntosGenerados() - tiros;
		if(tirosFallados < 0) {
			tirosFallados *= -1;
		}
		float valoracion = (puntosGenerados() + asistencias + rebotes + bloqueos) - (tirosFallados + perdidas + faltas);
		return valoracion/minutos;
	}

	@Override
	public float eficienciaOf() {
	    float posesiones = tiros + perdidas;
	    if (posesiones == 0){ 
	    	return 0;
	    }
	    return (puntosGenerados() / posesiones)*100;
	}

	@Override
	public int puntosGenerados() {
		return puntos;
	}

	@Override
	public float eFGPercent() {
		float valoracion = puntos/tiros;
		return valoracion*100;
	}

}