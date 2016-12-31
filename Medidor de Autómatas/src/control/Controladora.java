package control;

import vista.VistaPrincipal;
import modelo.Programa;

public class Controladora {

	private static Programa modelo;
	
	private static VistaPrincipal vista;
	
	public Controladora(){
		
		vista=new VistaPrincipal();
		
		vista.setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
		
	
		Controladora control=new Controladora();
		
		
		
	}

}
