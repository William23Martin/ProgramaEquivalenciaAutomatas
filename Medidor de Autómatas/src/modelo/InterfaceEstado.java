package modelo;

public interface InterfaceEstado {

	public void agregarTransicion(char entrada, char salida,
			AbstractEstado estadoLlegada);

}
