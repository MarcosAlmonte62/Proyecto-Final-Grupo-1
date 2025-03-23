
package Logico;

public class Escolta extends Atacante{
	private int tirosLibres;
	private int tirosLibresScored;
	private int triples;
	public Escolta(String nombre, int edad, String nacionalidad, Lesion lesion, Equipo equipo, int dorsal, int altura, int puntos,
			int asistencias, int tiros, int perdidas, int triples, int tirosLibres, int tirosLibresScored, int robos) {
		super(nombre, edad, nacionalidad, lesion, equipo, dorsal, altura, puntos, asistencias, tiros, perdidas, robos);

		this.tirosLibres = tirosLibres;
		this.tirosLibresScored = tirosLibresScored;
	    this.robos = robos;
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
	public int getTriples() {
		return triples;
	}
	public void setTriples(int triples) {
		this.triples = triples;
	}

	@Override
	public float puntosMvp(int minutos) {
		int tirosLibresFailed = tirosLibres - tirosLibresScored;
		int valoracion = (puntosGenerados() + asistencias) - (tirosLibresFailed + perdidas);
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
		return tirosLibresScored + triples*3 + puntos;
	}
	
	public float eTSPercent() {
		float valoracion = puntos / 2*(tiros + 0.5f*tirosLibres);
		return valoracion*100;
	}
	@Override
	public float eFGPercent() {
		float valoracion = (puntos + 0.5f*triples)/tiros;
		return valoracion*100;
	}
	public float FTARate() {
		return (tiros/tirosLibres)*100;
	}
	public float tripleRate() {
		return (tiros/triples)*100;
	}

	

}
