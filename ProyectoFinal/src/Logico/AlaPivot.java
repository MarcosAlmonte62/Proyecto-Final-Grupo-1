
package Logico;

public class AlaPivot extends Defensa{
	private int tiros;
	private int puntos;
	private int asistencias;

	public AlaPivot(String nombre, int edad, String nacionalidad, Lesion lesion, Equipo equipo, int dorsal, int altura, int rebotes,
	        int bloqueos, int tirosConcedidos, int puntosConcedidos, int faltas, int tiros, int puntos) {
		super(nombre, edad, nacionalidad, lesion, equipo, dorsal, altura, rebotes, bloqueos, tirosConcedidos, puntosConcedidos, faltas);
	    this.tiros = tiros;
	}	

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getTiros() {
		return tiros;
	}

	public void setTiros(int tiros) {
		this.tiros = tiros;
	}

	public int getAsistencias() {
		return asistencias;
	}

	public void setAsistencias(int asistencias) {
		this.asistencias = asistencias;
	}

	@Override
	public float puntosMvp(int minutos) {
		int tirosAcertados = (puntos + tiros) / 2;
		int tirosFallados = tiros - tirosAcertados;
		float valoracion = (rebotes + bloqueos + puntos + asistencias) - (faltas + tirosFallados); 
		return valoracion/minutos;
	}

	@Override
	public float eficienciaDef(int minutos) {
		return (puntosConcedidos*100)/posesionRival();
	}
	
	public float posesionRival() {
		float valoracion = tirosConcedidos + bloqueos;
		return valoracion * 100;
	}
	
	public float eFGPercent() {
		float valoracion = puntos/tiros;
		return valoracion*100;
	}

}
