package Logico;

public class AlaPivot extends Defensa{
	private int tirosMedia;

	public AlaPivot(String nombre, int edad, String nacionalidad, Lesion lesion, Equipo equipo, int dorsal, int rebotes,
			int bloqueos, int tapones, int tirosCampo, int robos, int tirosMedia) {
		super(nombre, edad, nacionalidad, lesion, equipo, dorsal, rebotes, bloqueos, tapones, tirosCampo, robos);
		this.tirosMedia = tirosMedia;
	}

	public int getTirosMedia() {
		return tirosMedia;
	}

	public void setTirosMedia(int tirosMedia) {
		this.tirosMedia = tirosMedia;
	}
}
