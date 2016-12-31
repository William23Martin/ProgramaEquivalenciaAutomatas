package modelo;

public class Transicion {

	private char[] representacion;

	private AbstractEstado estadoLLegada;

	public Transicion(char entrada, char salida, AbstractEstado llegada) {

		representacion = new char[2];

		representacion[0] = entrada;

		representacion[1] = salida;

		this.invariantes();

	}

	public char[] getRepresentacion() {
		return representacion;
	}

	public void setRepresentacion(char[] representacion) {
		this.representacion = representacion;
	}

	public AbstractEstado getEstadoLLegada() {
		return estadoLLegada;
	}

	public void setEstadoLLegada(AbstractEstado estadoLLegada) {
		this.estadoLLegada = estadoLLegada;
	}

	private void invariantes() {

		assert (representacion[0] == Automata.alfabetoEntrada[0] || representacion[0] == Automata.alfabetoEntrada[1])
				&& (representacion[1] == Automata.alfabetoSalida[0]
						|| representacion[1] == Automata.alfabetoSalida[1] || representacion[1] == ' ');

	}
	
	public String toString(){
		
		return "--"+representacion[0]+"|"+representacion[1]+"---->";
		
	}

}
