package modelo;

public class EstadoReconocedor extends AbstractEstado {

	public final static char[] valoresAceptacion = { '0', '1' };

	private char aceptacion;

	public EstadoReconocedor(char representacion, char aceptacion) {

		super(representacion);

		this.aceptacion = aceptacion;

		this.invariantesReconocedor();

	}

	public char getAceptacion() {
		return aceptacion;
	}

	public void setAceptacion(char aceptacion) {
		this.aceptacion = aceptacion;
	}

	private void invariantesReconocedor() {

		assert aceptacion == valoresAceptacion[0]
				|| aceptacion == valoresAceptacion[1] : "Si es de aceptación debe ser 1 sino debe ser 0. No se aceptan otros valores.";

	}
	
	public String toString(){
		
		return "( "+ representacion +","+aceptacion+" )";
		
	}

}
