package modelo;

import java.util.*;

public class AbstractEstado implements InterfaceEstado {

	public final static char[] representaciones = { 'A', 'B', 'C', 'D', 'E',
			'F', 'G', 'H', 'I', 'J', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
			'T', 'U' };

	protected char representacion;

	protected ArrayList<Transicion> transiciones;

	public AbstractEstado(char representacion) {

		this.representacion = representacion;

		transiciones = new ArrayList<Transicion>();

		this.invariantes();

	}

	public char getRepresentacion() {
		return representacion;
	}

	public void setRepresentacion(char representacion) {
		this.representacion = representacion;
	}

	public ArrayList<Transicion> getTransiciones() {
		return transiciones;
	}

	public void setTransiciones(ArrayList<Transicion> transiciones) {
		this.transiciones = transiciones;
	}

	@Override
	public void agregarTransicion(char entrada, char salida,
			AbstractEstado estadoLlegada) {

		transiciones.add(new Transicion(entrada, salida, estadoLlegada));

	}

	private boolean representacionValida(char representacion) {

		boolean esValido = false;

		int i = 0;

		while (i < representaciones.length) {

			if (representaciones[i] == representacion) {

				esValido = true;

				break;

			} else {

				i++;

			}

		}

		return esValido;

	}

	protected void invariantes() {

		assert representacionValida(representacion) : "Para representar un estado debe ser con las letras disponibles en el programa.";

	}

}
