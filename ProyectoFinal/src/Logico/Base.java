package Logico;

public class Base extends Atacante{
	private int triples;
	private int tirosLibres;
	private int tirosLibresScored;
	private int robos;
	public Base(String nombre, int edad, String nacionalidad, Lesion lesion, Equipo equipo, int dorsal, int altura, int puntos,
	        int asistencias, int tiros, int perdidas, int triples, int tirosLibres, int tirosLibresScored, int robos) {
	    super(nombre, edad, nacionalidad, lesion, equipo, dorsal, altura, puntos, asistencias, tiros, perdidas, robos);
	    this.triples = triples;
	    this.tirosLibres = tirosLibres;
	    this.tirosLibresScored = tirosLibresScored;
	    this.robos = robos;
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
		this.tirosLibres = tirosLibres;
	}

	public int getTirosLibresScored() {
		return tirosLibresScored;
	}

	public void setTirosLibresScored(int tirosLibresScored) {
		this.tirosLibresScored = tirosLibresScored;
	}

	@Override
	public float puntosMvp(int minutos) {
		int tirosFallados = puntosGenerados() - tiros;
		if(tirosFallados < 0) {
			tirosFallados *= -1;
		}
		float valoracion = (puntosGenerados() + asistencias + robos) - (tirosFallados + perdidas);
		return valoracion/minutos;
	
	}

	@Override
	public float eficienciaOf() {
	    float posesiones = tiros + 0.44f * tirosLibres + perdidas;
	    if (posesiones == 0){ 
	    	return 0;
	    }
	    return (puntosGenerados() / posesiones)*100;
	}

	@Override
	public int puntosGenerados() {
		return triples*3 + puntos;
	}

	@Override
	public float eFGPercent() {
		float valoracion = (puntos + 0.5f*triples)/tiros;
		return valoracion*100;
	}
	
	public float AssistPerLost() {
		return (asistencias/perdidas)*100;
	}
	
	


}