package interfaz;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelReducido extends JPanel{
	
	private JLabel[][] matrizTraduccion;
	
	public PanelReducido(PanelAutomata automata){
		setLayout(new GridLayout(1, 2));
		automata.deshabilitar();
		automata.bloquearBotones();
		add(automata);
		//tendra un tamanio mayor en numero y el primero sera
		//la descripcion
		matrizTraduccion = new JLabel[11][2];
		
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(11,2));
		
		matrizTraduccion[0][0] = new JLabel("Estado");
		matrizTraduccion[0][1] = new JLabel("Renombrado");
		aux.add(matrizTraduccion[0][0]);
		aux.add(matrizTraduccion[0][1]);
		for (int i = 1; i < matrizTraduccion.length; i++) {
			matrizTraduccion[i][0] = new JLabel();
			matrizTraduccion[i][1] = new JLabel();
			aux.add(matrizTraduccion[i][0]);
			aux.add(matrizTraduccion[i][1]);
		}
		add(aux);
	}
	
	public void actualizarConversion(String[][] matriz){
		for (int i = 1; i < matrizTraduccion.length; i++) {
			matrizTraduccion[i][0].setText(matriz[i][0]);
			matrizTraduccion[i][1].setText(matriz[i][1]);
		}
	}
	
}
