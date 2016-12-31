package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.MarshalException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import mundo.Automata;
import mundo.Estado;

public class PanelAutomata extends JPanel implements 
ActionListener{
	
	public final static String MEALY = "Mealy";
	public final static String MOORE = "Moore";
	public final static String NUEVO = "Nuevo";
	
	private JLabel[] labEstados;
	private JTextField[][] txtEstados;
	private JButton butAutomataReducidoMealy, butNuevo, 
					butAutomataReducidoMoore;
	private InterfazPrincipal principal;
	private int numeroAutomata;
	private char[] alfabeto;
	
	public PanelAutomata(char[] alfabate, InterfazPrincipal iP,
			int numberAutomate){
		setSize(300, 400);//ancho, alto
		setPreferredSize(new Dimension(300, 400));
		TitledBorder border = 
				BorderFactory.createTitledBorder("Automata");
		setBorder(border);
		setLayout(new GridLayout(12, 4));
		
		alfabeto = alfabate;
		principal = iP;
		numeroAutomata = numberAutomate;
		butAutomataReducidoMealy = new JButton(MEALY);
		butAutomataReducidoMealy.setActionCommand(MEALY);
		butAutomataReducidoMealy.addActionListener(this);
		
		butAutomataReducidoMoore = new JButton(MOORE);
		butAutomataReducidoMoore.setActionCommand(MOORE);
		butAutomataReducidoMoore.addActionListener(this);
		
		butNuevo = new JButton(NUEVO);
		butNuevo.setActionCommand(NUEVO);
		butNuevo.addActionListener(this);
		
		labEstados = new JLabel[10];
		txtEstados = new JTextField[10][3];
		for (int i = 0; i < 10; i++) {
			txtEstados[i][0] = new JTextField();
			txtEstados[i][0].setEditable(true);
			txtEstados[i][1] = new JTextField();
			txtEstados[i][1].setEditable(true);
			txtEstados[i][2] = new JTextField();
			txtEstados[i][2].setEditable(true);
			labEstados[i] = new JLabel(alfabeto[i]+"");
		}
		add(new JLabel(""));
		add(new JLabel("a"));
		add(new JLabel("b"));
		add(new JLabel("Salida"));
		for (int i = 0; i < txtEstados.length; i++) {
			add(labEstados[i]);
			add(txtEstados[i][0]);
			add(txtEstados[i][1]);
			add(txtEstados[i][2]);
		}
		add(butAutomataReducidoMealy);
		add(butAutomataReducidoMoore);
		add(butNuevo);
		add(new JLabel(""));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals(MOORE)||comando.equals(MEALY)) {
			//FALTA SI ES DE MEALY
			String[][] matriz = new String[10][3];
			int tamanio = 0;//hasta donde ira la matriz
			for (int i = 0; i < txtEstados.length; i++) {
				String text0 = txtEstados[i][0].getText();
				String text1 = txtEstados[i][1].getText();
				String text2 = txtEstados[i][2].getText();
				//si alguna de las demas esta vacia termine
				if (text0==null || text0.equals("") || 
						text1==null || text1.equals("") || 
						text2==null || text2.equals("")) {
					tamanio = i;//esta bien
					break;
				}
				matriz[i][0] = text0;
				matriz[i][1] = text1;
				matriz[i][2] = text2;
			}
			//true si es de moore, false si es de mealy
			boolean tipo = comando.equals(MOORE);
			try {
				verificar(matriz, tamanio, tipo);
				deshabilitar();
				principal.automataReducido(matriz, tamanio,
						tipo, numeroAutomata);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, 
						e2.getMessage());
			}
		}else if (comando.equals(NUEVO)) {
			reiniciar();
			principal.reiniciar(numeroAutomata);
		}
	}
	
	public void reiniciar(){
		for (int i = 0; i < txtEstados.length; i++) {
			txtEstados[i][0].setEditable(true);
			txtEstados[i][0].setText("");
			txtEstados[i][1].setEditable(true);
			txtEstados[i][1].setText("");
			txtEstados[i][2].setEditable(true);
			txtEstados[i][2].setText("");
		}
	}
	
	public void deshabilitar(){
		for (int i = 0; i < txtEstados.length; i++) {
			txtEstados[i][0].setEditable(false);
			txtEstados[i][1].setEditable(false);
			txtEstados[i][2].setEditable(false);
		}
	}

	private void verificar(String[][] matriz, int tamanio, 
			boolean tipo) throws Exception {
		char[] entrada = new char[tamanio];
		for (int i = 0; i < tamanio; i++) {
			entrada[i] = alfabeto[i];
		}
		for (int i = 0; i < tamanio; i++) {
			String str1 = matriz[i][0];
			String str2 = matriz[i][1];
			String str3 = matriz[i][2];
			int size = 1;
			if (!tipo) {
				size = 3;
			}
			if (str1.length()!=1 || str2.length()!=1 || 
					str3.length()!=size) {
				String mensaje = "El tamaño"
						+ " de la cadena en cada casilla no "
						+ " puede sobrepasar de 1, a menos que "
						+ "sea automata de mealy, cuya salida "
						+ "debe ser 3";
				throw new Exception(mensaje);
			}
			if (!estaAlfabeto(entrada, str1.charAt(0)) ||
					!estaAlfabeto(entrada, str2.charAt(0))){
				String mensaje = "Alguno de "
						+ "los caracteres no corresponde a las"
						+ "entradas de los automatas";
				throw new Exception(mensaje);
			}
			char[] salida = new char[2];
			salida[0] = '0';
			salida[1] = '1';
			if (tipo) {//moore
				if (!estaAlfabeto(salida, str3.charAt(0))) {
					String mensaje = "La salida no corresponde";
					throw new Exception(mensaje);
				}
			}else{//mealy
				if (!estaAlfabeto(salida, str3.charAt(2)) || 
						!estaAlfabeto(salida, str3.charAt(0))){
					String mensaje = "La salida no corresponde";
					throw new Exception(mensaje);
				}
			}
		}
		
	}

	private boolean estaAlfabeto(char[] alfabet, 
			char comparar) {
		for (int i = 0; i < alfabet.length; i++) {
			if (alfabet[i]==comparar) 
				return true;
		}
		return false;
	}
	
	public void cammbiarPanel(String[][] matriz){
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				txtEstados[i][j].setText(matriz[i][j]);
			}
		}
	}
	
	public void bloquearBotones(){
		butAutomataReducidoMealy.setEnabled(false);
		butAutomataReducidoMoore.setEnabled(false);
		butNuevo.setEnabled(false);
	}
	
}
