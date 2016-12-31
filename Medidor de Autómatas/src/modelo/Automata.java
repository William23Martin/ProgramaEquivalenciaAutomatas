package modelo;

import java.util.HashMap;

public abstract class Automata {

	public final static int NUMERO_MAXIMO_ESTADOS = 10;

	public final static char[] alfabetoEntrada = { 'a', 'b' };

	public final static char[] alfabetoSalida = { '0', '1' };

	protected HashMap<Character, AbstractEstado> tablaEstados;

	protected AbstractEstado estadoInicial;

	public Automata() {

		tablaEstados = new HashMap<Character, AbstractEstado>();

	}

	public HashMap<Character, AbstractEstado> getTablaEstados() {

		return tablaEstados;

	}

	public void setTablaEstados(HashMap<Character, AbstractEstado> tablaEstados) {
		this.tablaEstados = tablaEstados;
	}

}
