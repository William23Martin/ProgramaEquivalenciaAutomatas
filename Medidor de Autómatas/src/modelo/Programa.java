package modelo;

import javax.swing.JTable;

public class Programa {

	public final static char[] tipos={'M','R'};
	
	private Automata automata1, automata2;

	private JTable tablaEstados1, tablaEstados2;

	public Programa(JTable tablaEstados1, JTable tablaEstados2) {

		this.tablaEstados1 = tablaEstados1;

		this.tablaEstados2 = tablaEstados2;

	}

	public Automata getAutomata1() {
		return automata1;
	}

	public void setAutomata1(Automata automata1) {
		this.automata1 = automata1;
	}

	public Automata getAutomata2() {
		return automata2;
	}

	public void setAutomata2(Automata automata2) {
		this.automata2 = automata2;
	}

	public JTable getTablaEstados1() {
		return tablaEstados1;
	}

	public void setTablaEstados1(JTable tablaEstados1) {
		this.tablaEstados1 = tablaEstados1;
	}

	public JTable getTablaEstados2() {
		return tablaEstados2;
	}

	public void setTablaEstados2(JTable tablaEstados2) {
		this.tablaEstados2 = tablaEstados2;
	}

	private void agregarAutomataMealy() {

	}

	private void agregarAutomataReconocedor() {

	}

	public void llenarAutomata1(char tipo) {

	}

	public void llenarAutomata2(char tipo) {

	}

	public JTable reducirAutomata1() {

		return null;

	}

	public JTable reducirAutomata2() {

		return null;

	}

	public boolean equivalenciaAutomatas() {

		return false;

	}

	public String cadenaDiferenciacion() {

		return "";

	}

}
