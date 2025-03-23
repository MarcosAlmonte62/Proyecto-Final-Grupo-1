
package Logico;

public class Pivot extends Defensa{
	int tirosLibresGenerados;
	int recuperaciones;
	int rebotesConcedidos;

	public Pivot(String nombre, int edad, String nacionalidad, Lesion lesion, Equipo equipo, int dorsal, int altura, int rebotes,
	        int bloqueos, int tapones, int tirosConcedidos, int puntosConcedidos, int faltas, int tirosLibresGenerados, int recuperaciones, int rebotesConcedidos) {
	    super(nombre, edad, nacionalidad, lesion, equipo, dorsal, altura, rebotes, bloqueos, tirosConcedidos, puntosConcedidos, faltas);
	    this.tirosLibresGenerados = tirosLibresGenerados;
	    this.recuperaciones = recuperaciones;
	    this.rebotesConcedidos = rebotesConcedidos;
	}


	public int getTirosLibresGenerados() {
		return tirosLibresGenerados;
	}

	public void setTirosLibresGenerados(int tirosLibresGenerados) {
		this.tirosLibresGenerados = tirosLibresGenerados;
	}


	@Override
	public float puntosMvp(int minutos) {
		float valoracion = (rebotes + bloqueos + recuperaciones) - (faltas + tirosLibresGenerados);
		return valoracion/minutos;
	}


	@Override
	public float eficienciaDef(int minutos) {
		return (puntosConcedidos*100)/posesionRival();
	}
	
	public float posesionRival() {
		float posesion = tirosConcedidos + 0.44f*tirosLibresGenerados + recuperaciones - rebotesConcedidos;
		return posesion * 100;
	}
	
	
	public float bloqueoPercent() {
		return (bloqueos/tirosConcedidos)*100;
	}
	
	public float rebotesPercent(int rebotesTotales) {
		return (rebotes/rebotesTotales)*100;
	}

}
