package vista;

import javax.swing.*;
import java.awt.*;

public class VistaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private PanelCreacionAutomatas panelCreacionAutomatas;

	private PanelDesicion panelDesicion;

	public VistaPrincipal() {

		this.setSize(1000, 700);

		this.setTitle("Programa Equivalencia entre Autómatas");

		this.setResizable(true);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setLocationRelativeTo(null);

		this.setLayout(new GridLayout(1, 2));

		panelCreacionAutomatas = new PanelCreacionAutomatas();

		panelDesicion = new PanelDesicion();

		this.add(panelCreacionAutomatas);

		this.add(panelDesicion);

	}

	public PanelCreacionAutomatas getPanelCreacionAutomatas() {
		return panelCreacionAutomatas;
	}

	public void setPanelCreacionAutomatas(
			PanelCreacionAutomatas panelCreacionAutomatas) {
		this.panelCreacionAutomatas = panelCreacionAutomatas;
	}

	public PanelDesicion getPanelDesicion() {
		return panelDesicion;
	}

	public void setPanelDesicion(PanelDesicion panelDesicion) {
		this.panelDesicion = panelDesicion;
	}

}
