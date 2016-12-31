package interfaz;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import mundo.Equivalencia;

public class InterfazPrincipal extends JFrame{
	
	//TODO
	//explicar la forma de usar el programa, por ejemplo
	//que si es de mealy la salida la debe meter como "0,1"
	//sin las comillas, donde 0 sera la salida del primero 
	//y 1 sera la salida del segundo
	
	private Equivalencia equivalencia;
	private PanelAutomata panelAutomata1, panelAutomata2;
	private PanelAutomata panelAutomataRed1, panelAutomataRed2;
	private PanelAutomata panelAutoEquivalente;
	private PanelReducido panelReducido1, panelReducido2;
	private PanelEquivalencia panelequivalencia;
	
	public InterfazPrincipal(){
		setTitle("Equivalencia de automatas");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(630, 400);
		
		JTabbedPane pestania = new JTabbedPane();
		
		//estos son los paneles que seran editados
		panelAutomata1 = new 
				PanelAutomata(Equivalencia.ESTADOS_AUTOMATA_1, 
						this, 1);
		panelAutomata2 = new 
				PanelAutomata(Equivalencia.ESTADOS_AUTOMATA_2, 
						this, 2);
		
		//estas seran las respuestas
		panelAutomataRed1 = new 
				PanelAutomata(Equivalencia.ESTADOS_AUTOMATA_1, 
						this, 3);
		panelAutomataRed2 = new 
				PanelAutomata(Equivalencia.ESTADOS_AUTOMATA_2, 
						this, 4);
		
		
		panelReducido1 = new PanelReducido(panelAutomataRed1);
		panelReducido2 = new PanelReducido(panelAutomataRed2);
		
		panelAutoEquivalente = new 
				PanelAutomata(equivalencia.ESTADOS_AUTOMATA,
						this, 5);
		panelequivalencia = new PanelEquivalencia(panelAutoEquivalente, this);
		
//		equivalencia = new Equivalencia();
		//paneles (2 del mismo)
		
		JPanel auxiliar = new JPanel();
		auxiliar.setLayout(new GridLayout(1, 2));
		auxiliar.add(panelAutomata1);
		auxiliar.add(panelAutomata2);
		
		pestania.add("Automatas", auxiliar);
		pestania.add("Automata 1", panelReducido1);
		pestania.add("Automata 2", panelReducido2);
		pestania.add("Equivalencia", panelequivalencia);
		add(pestania);
	}
	
	public void mostrarMatriz(int automata){
		System.out.println("2.3");
		//pide la matriz a equi
		//tamaño 10x3
		String[][] matrizAutomata = 
				equivalencia.generarMatrizAutomata(automata);
		//tamaño 10x2
		String[][] matrizTraduccion = 
				equivalencia.geneararTraduccion(automata);
		
		System.out.println("2.6");
		panelAutomataRed1.cammbiarPanel(matrizAutomata);
		panelReducido1.actualizarConversion(matrizTraduccion);
		
		
	}
	
	public void equivalencia(){
		boolean retorno = equivalencia.sonEquivalentes();
		if (retorno) {
			equivalencia.getTraduccion();
		}else{
			String cadena = equivalencia.cadenaLogitudMinima();
		}
	}
	
	public static void main(String[] args) {
		InterfazPrincipal ventana = new InterfazPrincipal();
		ventana.setVisible(true);
	}

	public void automataReducido(String[][] matriz, 
			int tamanio, boolean tipo, int numeroAutomata) {
		equivalencia = new Equivalencia();
		//mostrar equivalencias
		//saber si se eliminaron estados
		//mostrar en matriz de un tamanio de 10
		System.out.println("0");
		for (int i = 0; i < tamanio; i++) {
			int acept = 0;
			if (tipo) 
				acept = Integer.parseInt(matriz[i][2]);
			equivalencia.agregarEstado(numeroAutomata, 
					acept);
		}
		System.out.println("1");
		equivalencia.generarMatriz(numeroAutomata, tipo);
		System.out.println("1.0");
		for (int i = 0; i < tamanio; i++) {
			String tran1 = matriz[i][0];
			String tran2 = matriz[i][1];
			if (tipo) {//moore
				//el charSalida no sera tomado en cuenta
				equivalencia.agregarTransicion(i, 
						tran1.charAt(0), numeroAutomata,
						'a', '0');
				equivalencia.agregarTransicion(i, 
						tran2.charAt(0), numeroAutomata,
						'b', '0');
			}else{
				String mealy = matriz[i][2];
				equivalencia.agregarTransicion(i, 
						tran1.charAt(0), numeroAutomata,
						'a', mealy.charAt(0));
				equivalencia.agregarTransicion(i, 
						tran2.charAt(0), numeroAutomata,
						'b', mealy.charAt(2));
			}
		}
		System.out.println("2");
		equivalencia.automataReducido(numeroAutomata);
		System.out.println("3");
		mostrarMatriz(numeroAutomata);
		System.out.println("4");
	}

	public void reiniciar(int numeroAutomata) {
		if (equivalencia!=null)
			equivalencia.reiniciarAutomata(numeroAutomata);
		
		if (numeroAutomata==1) {
			panelAutomataRed1.reiniciar();
			panelAutomataRed1.deshabilitar();
		}else{
			panelAutomataRed2.reiniciar();
			panelAutomataRed2.deshabilitar();
		}
	}

	public void cadenaLogitudMinima() {
		// TODO Auto-generated method stub
		
	}

}
