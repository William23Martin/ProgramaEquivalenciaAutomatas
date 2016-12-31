package vista;

import javax.swing.*;

public class PanelEquivalencia extends JPanel {

	private static final long serialVersionUID = 1L;

	public final static String BOTON_EQUIVALENCIA = "EQUIVALENCIA";

	private JButton botonEquivalencia;

	public PanelEquivalencia() {

		botonEquivalencia = new JButton(BOTON_EQUIVALENCIA);

		this.add(botonEquivalencia);

	}

	public JButton getBotonEquivalencia() {
		return botonEquivalencia;
	}

	public void setBotonEquivalencia(JButton botonEquivalencia) {
		this.botonEquivalencia = botonEquivalencia;
	}

}
