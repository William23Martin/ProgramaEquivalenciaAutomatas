package vista;

import javax.swing.*;
import java.awt.*;

public class PanelOpcionesAutomata extends JPanel {

	private static final long serialVersionUID = 1L;

	public final static String[] botonesOpcionesAutomata = { "MEALY",
			"RECONOCEDOR" };

	private JButton botonMealy, botonReconocedor;

	public PanelOpcionesAutomata() {

		this.setLayout(new GridLayout(1, 2));

		this.botonMealy = new JButton(botonesOpcionesAutomata[0]);

		this.botonReconocedor = new JButton(botonesOpcionesAutomata[1]);

		this.add(botonMealy);
		this.add(botonReconocedor);

	}

	public JButton getBotonMealy() {
		return botonMealy;
	}

	public void setBotonMealy(JButton botonMealy) {
		this.botonMealy = botonMealy;
	}

	public JButton getBotonReconocedor() {
		return botonReconocedor;
	}

	public void setBotonReconocedor(JButton botonReconocedor) {
		this.botonReconocedor = botonReconocedor;
	}

}
