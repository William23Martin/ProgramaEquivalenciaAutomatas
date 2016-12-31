package modelo;

public interface InterfaceAutomataMealy {

	public void agregarEstado(char representacion) throws AutomataException;

	public abstract void agregarTransicion(char representacionEstadoPartida,
			char entrada, char salida, char representacionEstadoLlegada) throws AutomataException;
	
}
