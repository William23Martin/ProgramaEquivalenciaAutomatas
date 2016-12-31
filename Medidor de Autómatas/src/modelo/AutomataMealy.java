package modelo;

public class AutomataMealy extends Automata implements InterfaceAutomataMealy {

	@Override
	public void agregarEstado(char representacion) throws AutomataException {

		if (tablaEstados.size() == NUMERO_MAXIMO_ESTADOS) {

			throw new AutomataException("Máximo sólo se pueden agregar"
					+ NUMERO_MAXIMO_ESTADOS + "estados ");

		} else if (tablaEstados.isEmpty()) {

			estadoInicial = new EstadoMealy(representacion);

			this.tablaEstados.put(representacion, estadoInicial);

		} else {

			if (tablaEstados.containsKey(representacion)) {

				throw new AutomataException(
						"El estado con esa representación ya existe.");

			} else {

				EstadoMealy estadoNuevo = new EstadoMealy(representacion);

				this.tablaEstados.put(representacion, estadoNuevo);

			}

		}
	}

	@Override
	public void agregarTransicion(char representacionEstadoPartida,
			char entrada, char salida, char representacionEstadoLlegada)
			throws AutomataException {

		if (!tablaEstados.containsKey(representacionEstadoPartida)) {

			agregarEstado(representacionEstadoPartida);

		}
		if (!tablaEstados.containsKey(representacionEstadoLlegada)) {

			agregarEstado(representacionEstadoLlegada);

		} else {

			tablaEstados.get(representacionEstadoPartida).agregarTransicion(
					entrada, salida,
					tablaEstados.get(representacionEstadoLlegada));

		}

	}

}
