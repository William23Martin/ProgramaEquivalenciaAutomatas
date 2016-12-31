package modelo;

public class AutomataReconocedor extends Automata implements InterfaceAutomataReconocedor {

	@Override
	public void agregarEstado(char representacion, char aceptacion)
			throws AutomataException {

		if (tablaEstados.size() == NUMERO_MAXIMO_ESTADOS) {

			throw new AutomataException("Máximo sólo se pueden agregar"
					+ NUMERO_MAXIMO_ESTADOS + "estados ");

		} else if (tablaEstados.isEmpty()) {

			estadoInicial=new EstadoReconocedor(representacion, aceptacion);
			
		}else {

			if (tablaEstados.containsKey(representacion)) {

				throw new AutomataException(
						"El estado con esa representación ya existe.");

			} else {

				EstadoReconocedor estadoNuevo = new EstadoReconocedor(representacion,
						aceptacion);

				this.tablaEstados.put(representacion, estadoNuevo);

			}

		}

	}

	@Override
	public void agregarTransicion(char representacionEstadoPartida,
			char aceptacionEstadoPartida, char entrada, char salida,
			char representacionEstadoLlegada, char aceptacionEstadoLlegada)
			throws AutomataException {
	

		if (!tablaEstados.containsKey(representacionEstadoPartida)) {

			agregarEstado(representacionEstadoPartida, aceptacionEstadoPartida);

		}if (!tablaEstados.containsKey(representacionEstadoLlegada)) {

			agregarEstado(representacionEstadoLlegada, aceptacionEstadoLlegada);

		}  else {

			tablaEstados.get(representacionEstadoPartida).agregarTransicion(
					entrada, salida,
					tablaEstados.get(representacionEstadoLlegada));

		}
		
		
	}

	

}
