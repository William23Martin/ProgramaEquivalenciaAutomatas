package modelo;

public interface InterfaceAutomataReconocedor {

	public void agregarEstado(char representacion, char aceptacion)
			throws AutomataException;

	public abstract void agregarTransicion(char representacionEstadoPartida, char aceptacionEstadoPartida,
			char entrada, char salida, char representacionEstadoLlegada, char aceptacionEstadoLlegada) throws AutomataException;
	
}
