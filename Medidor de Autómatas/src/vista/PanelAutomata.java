package vista;

import java.awt.BorderLayout;

import javax.swing.*;

public class PanelAutomata extends JPanel {

	private static final long serialVersionUID = 1L;

	private PanelOpcionesAutomata panelOpcionesAutomata;

	private JPanel panelAuxiliar;

	public PanelAutomata() {

		panelAuxiliar = new JPanel();

		panelOpcionesAutomata = new PanelOpcionesAutomata();
		
		this.setLayout(new BorderLayout());
		
		this.add(panelAuxiliar, BorderLayout.CENTER);
		
		this.add(panelOpcionesAutomata,BorderLayout.SOUTH);

	}

	public PanelOpcionesAutomata getPanelOpcionesAutomata() {
		return panelOpcionesAutomata;
	}

	public void setPanelOpcionesAutomata(
			PanelOpcionesAutomata panelOpcionesAutomata) {
		this.panelOpcionesAutomata = panelOpcionesAutomata;
	}

	public JPanel getPanelAuxiliar() {
		return panelAuxiliar;
	}

	public void setPanelAuxiliar(JPanel panelAuxiliar) {
		this.panelAuxiliar = panelAuxiliar;
	}

}
