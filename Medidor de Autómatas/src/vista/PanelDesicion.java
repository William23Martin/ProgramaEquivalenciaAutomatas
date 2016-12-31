package vista;

import javax.swing.*;
import java.awt.*;

public class PanelDesicion extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel panelRespuesta;

	private PanelEquivalencia panelEquivalencia;

	public PanelDesicion() {

		panelRespuesta = new JPanel();
		
		panelRespuesta.setBorder(BorderFactory.createTitledBorder("Respuesta")); 

		panelEquivalencia = new PanelEquivalencia();

		this.setLayout(new BorderLayout());

		this.add(panelRespuesta, BorderLayout.CENTER);

		this.add(panelEquivalencia, BorderLayout.SOUTH);

	}

	public JPanel getPanelRespuesta() {
		return panelRespuesta;
	}

	public void setPanelRespuesta(JPanel panelRespuesta) {
		this.panelRespuesta = panelRespuesta;
	}

	public PanelEquivalencia getPanelEquivalencia() {
		return panelEquivalencia;
	}

	public void setPanelEquivalencia(PanelEquivalencia panelEquivalencia) {
		this.panelEquivalencia = panelEquivalencia;
	}

}
