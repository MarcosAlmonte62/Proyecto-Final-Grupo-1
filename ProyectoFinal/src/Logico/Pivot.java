package Logico;

public class Pivot extends Defensa{
	int tirosLibresGenerados;

	public Pivot(String nombre, int edad, String nacionalidad, Lesion lesion, Equipo equipo, int dorsal, int rebotes,
			int bloqueos, int tapones, int tirosCampo, int robos, int tirosLibresGenerados) {
		super(nombre, edad, nacionalidad, lesion, equipo, dorsal, rebotes, bloqueos, tapones, tirosCampo, robos);
		this.tirosLibresGenerados = tirosLibresGenerados;
	}

	public int getTirosLibresGenerados() {
		return tirosLibresGenerados;
	}

	public void setTirosLibresGenerados(int tirosLibresGenerados) {
		this.tirosLibresGenerados = tirosLibresGenerados;
	}

}
