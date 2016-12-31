package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelEquivalencia extends JPanel implements ActionListener{
	
	public final static String EQUIVALENCIA = "Son equivalentes";
	public final static String CADENA_LONG_MINIMA = "Cadena logitud minima";
	
	private JButton butEquivalencia, butLongitud;
	private Boolean equivalentes;
	private JLabel labEquivalentes;
	private InterfazPrincipal principal;
	
	public PanelEquivalencia(PanelAutomata automata, 
			InterfazPrincipal interfaz){
		principal = interfaz;
//		setLayout(new GridLayout(1, 2));
		setLayout(new BorderLayout());
		automata.deshabilitar();
		automata.bloquearBotones();
		add(automata, BorderLayout.CENTER);
//		add(automata);
		
		labEquivalentes = new JLabel("No son equivalentes");
		equivalentes = false;
		butEquivalencia = new JButton(EQUIVALENCIA);
		butEquivalencia.setActionCommand(EQUIVALENCIA);
		butEquivalencia.addActionListener(this);
		
		butLongitud = new JButton(CADENA_LONG_MINIMA);
		butLongitud.setActionCommand(CADENA_LONG_MINIMA);
		butLongitud.addActionListener(this);
		butLongitud.setEnabled(false);
		
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(2, 2));
		
		aux.add(butEquivalencia);
		aux.add(butLongitud);
		aux.add(labEquivalentes);
		aux.add(new JLabel(""));
		
		add(aux, BorderLayout.SOUTH);
//		add(aux);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.endsWith(CADENA_LONG_MINIMA)) {
			principal.cadenaLogitudMinima();
		}else if (comando.equals(EQUIVALENCIA)) {
			principal.equivalencia();
		}
	}
	
	public void sonEquivalentes(){
		//la tabla
		labEquivalentes.setText("Son equivalentes");
	}
	
	public void noSonEquivalentes(String cadena){
		butLongitud.setEnabled(true);
		JOptionPane.showMessageDialog(this, cadena);
	}
	
}
