package Logico;

public class Base extends Atacante{
	private int tripleDoble;

	public Base(String nombre, int edad, String nacionalidad, Lesion lesion, Equipo equipo, int dorsal, int puntos, int asistencias,
			int tiros, int perdidas, int triples, int tripleDoble) {
		super(nombre, edad, nacionalidad, lesion, equipo, dorsal, puntos, asistencias, tiros, perdidas, triples);
		this.tripleDoble = tripleDoble;
	}

	public int getTripleDoble() {
		return tripleDoble;
	}

	public void setTripleDoble(int tripleDoble) {
		this.tripleDoble = tripleDoble;
	}

}
