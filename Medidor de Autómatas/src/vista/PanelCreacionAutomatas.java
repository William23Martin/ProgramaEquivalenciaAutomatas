package vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class PanelCreacionAutomatas extends JPanel {

	private static final long serialVersionUID = 1L;

	private PanelAutomata panelAutomataNorte, panelAutomataSur;

	public PanelCreacionAutomatas() {

		this.setLayout(new GridLayout(2, 1));

		this.panelAutomataNorte = new PanelAutomata();
		
		TitledBorder border1=BorderFactory.createTitledBorder("Autómata 1");
		
		TitledBorder border2=BorderFactory.createTitledBorder("Autómata 2");
		
		panelAutomataNorte.setBorder(border1);

		this.panelAutomataSur = new PanelAutomata();

		panelAutomataSur.setBorder(border2);
		
		this.add(panelAutomataNorte);
		
		this.add(panelAutomataSur);
		
	}

	public PanelAutomata getPanelAutomataNorte() {
		return panelAutomataNorte;
	}

	public void setPanelAutomataNorte(PanelAutomata panelAutomataNorte) {
		this.panelAutomataNorte = panelAutomataNorte;
	}

	public PanelAutomata getPanelAutomataSur() {
		return panelAutomataSur;
	}

	public void setPanelAutomataSur(PanelAutomata panelAutomataSur) {
		this.panelAutomataSur = panelAutomataSur;
	}

}
