package mundo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class Equivalencia {
	
	public final static char[] ESTADOS_AUTOMATA_1 = {'A',
		'B','C','D','E','F','G','H','I','J'};
	public final static char[] ESTADOS_AUTOMATA_2 = {'L',
		'M','N','O','P','Q','R','S','T','U'};
	public final static char[] ESTADOS_AUTOMATA = {'a',
	'b','c','d','e','f','g','h','i','j','k','m','n','o',
	'p','q','r','s'};
	
	private Automata automata1;
	private HashMap<Character, Character> funcionA1;
	
	private Automata automata2;
	private HashMap<Character, Character> funcionA2;
	
	//true si es de moore
	//false si es de mealy
	
	public Equivalencia(){
		automata1 = new Automata(ESTADOS_AUTOMATA_1);
		funcionA1 = new HashMap<Character, Character>();
		automata2 = new Automata(ESTADOS_AUTOMATA_2);
		funcionA2 = new HashMap<Character, Character>();
	}
	
	public void agregarEstado(int automata, int acepta){
		if (automata==1) {
			automata1.agregarEstado(acepta);
		}else{
			automata2.agregarEstado(acepta);
		}
	}
	
	//charEntrada sera el x en la forma de la funcion 
	//f(estado,x)
	//charSalida sera opcional, dependiendo del tipo del 
	//automata
	public void agregarTransicion(int estadoEntrada, 
			char estadoSalida, int automata,
			char charEntrada, char charSalida){
		if (automata==1) {
			boolean tipoAutomata = automata1.isTipoAutomata();
			if (tipoAutomata) {//moore
				automata1.agregarTransicion(automata1.
						getNombreEstados()[estadoEntrada], 
						estadoSalida, charEntrada);
			}else{
				automata1.agregarTransicion(automata1.
						getNombreEstados()[estadoEntrada], 
						estadoSalida, charEntrada, charSalida);
			}
		}else{
			boolean tipoAutomata = automata2.isTipoAutomata();
			if (tipoAutomata) {//moore
				automata2.agregarTransicion(automata2.
						getNombreEstados()[estadoEntrada], 
						estadoSalida, charEntrada);
			}else{
				automata2.agregarTransicion(automata2.
						getNombreEstados()[estadoEntrada], 
						estadoSalida, charEntrada, charSalida);
			}
		}
	}
	
	public String[][] geneararTraduccion(int automata) {
		String[][] retorno = new String[10][2];
		HashMap<Character, Character> actual = null;
		if (automata==1) {
			actual = funcionA1;
		}else{
			actual = funcionA2;
		}
		Iterator<Character> caracter = actual.keySet().iterator();
		int pos = 0;
		while (pos<10) {
			if (caracter.hasNext()) {
				char x = caracter.next();
				char y = actual.get(x);
				retorno[pos][0] = x+"";
				retorno[pos][1] = y+"";
				pos++;
			}else{
				retorno[pos][0] = "";
				retorno[pos][1] = "";
				pos++;
			}
		}
		
		return retorno;
	}
	
	public String[][] generarMatrizAutomata(int numAutomata){
		String[][] retorno = new String[10][3];
		Automata actual = null;
		if (numAutomata==1) {
			actual = automata1;
		}else{
			actual = automata2;
		}
		Estado[][] funcion = actual.getFuncion();
		int tamanioAutomata = actual.getPosEstado();
		for (int i = 0; i < retorno.length; i++) {
			if (i>=tamanioAutomata) {
				for (int j = 0; j < retorno[0].length; j++) {
					retorno[i][j] = "-";
				}
				continue;
			}else if (actual.isTipoAutomata()) {
				//moore, importa si es de aceptacion
				char estado = actual.getNombreEstados()[i];
				retorno[i][0] = funcion[i][0].getNombre()+"";
				retorno[i][1] = funcion[i][1].getNombre()+"";
				retorno[i][2] = actual.getCharToState().
						get(estado).getAceptacion()+"";
			}else{
				char[][] mealy = actual.getMealy();
				retorno[i][0] = funcion[i][0].getNombre()+"";
				retorno[i][1] = funcion[i][1].getNombre()+"";
				retorno[i][2] = mealy[i][0]+","+mealy[i][1];
			}
		}
		
		return retorno;
	}
	
	public void generarMatriz(int automata, 
			boolean tipoAutomata){
		//una vez terminado todos los estados genera la 
		//matriz
		if (automata==1) {
			automata1.generarMatriz(tipoAutomata);
		}else{
			automata2.generarMatriz(tipoAutomata);
		}
	}
	
	
	//creates the transition of the new automata where it
	//it changes the state with the one in the map
	public void generarAutomata2(Automata creado, 
			HashMap<Character, Character> mapa){
		boolean tipoAutomata = creado.isTipoAutomata();
		creado.generarMatriz(!tipoAutomata);//false si es de 
		//mealy, por tanto se niega para q quede de mealy
		//takes the states of both automatas to iterate over
		//them
		char[] estados1 = automata1.getNombreEstados();
		char[] estados2 = automata2.getNombreEstados();
		for (int i = 0; i < estados1.length; i++) {
			char entrada = estados1[i];
			//for one state, takes his function evaluate in 'a'
			//and 'b', to create the transition
			char simboloSalida1 = 
					automata1.simboloSalida(entrada, 'a');
			char simboloSalida2 = 
					automata1.simboloSalida(entrada, 'b'); 
			if (tipoAutomata) {
				//moore, if is a moore's automata, just put
				//the transition with the value in the map for
				//those states
				creado.agregarTransicion(mapa.get(entrada), 
						mapa.get(simboloSalida1), 'a');
				creado.agregarTransicion(mapa.get(entrada), 
						mapa.get(simboloSalida2), 'b');
			}else{
				//if is a mealy's automata, search for the out
				//and create the transition as the other one
				//but with the out for each transition
				char salida1 = automata1.salida(entrada, 'a');
				char salida2 = automata1.salida(entrada, 'b');
				
				creado.agregarTransicion(mapa.get(entrada), 
						mapa.get(simboloSalida1), 'a', salida1);
				creado.agregarTransicion(mapa.get(entrada), 
						mapa.get(simboloSalida2), 'b', salida2);
			}
		}
		for (int i = 0; i < estados2.length; i++) {
			char entrada = estados2[i];
			char simboloSalida1 = 
					automata2.simboloSalida(entrada, 'a');
			char simboloSalida2 = 
					automata2.simboloSalida(entrada, 'b'); 
			if (tipoAutomata) {
				//moore
				creado.agregarTransicion(mapa.get(entrada), 
						mapa.get(simboloSalida1), 'a');
				creado.agregarTransicion(mapa.get(entrada), 
						mapa.get(simboloSalida2), 'b');
			}else{
				char salida1 = automata2.salida(entrada, 'a');
				char salida2 = automata2.salida(entrada, 'b');
				
				creado.agregarTransicion(mapa.get(entrada), 
						mapa.get(simboloSalida1), 'a', salida1);
				creado.agregarTransicion(mapa.get(entrada), 
						mapa.get(simboloSalida2), 'b', salida2);
			}
		}
	}
	
	public boolean sonEquivalentes(){
		//se asume que el automata ya esta reducido
		HashMap<Character, Character> mapa = 
				new HashMap<Character, Character>();
		Automata automataGrande = generarAutomataGrande(mapa);
		generarAutomata2(automataGrande, mapa);
		automataGrande.automataReducidoEquivalente();
		//ya esta creado el automata
		ArrayList<int[]> respuesta = 
				automataGrande.automataReducidoEquivalente();
		//como ya esta reducido, no pueden haber mas de 2 en 
		//cada particion, es decir, deben haber estrictamente 2
		int numeroElementos = 
				automataGrande.tamanioParticion(respuesta.
				get(respuesta.size()-1));
		
		//si el tamaño esta bien, deberia ser igual al numero 
		//de elementos en el mapa/2
		if (numeroElementos==mapa.size()/2) {
			return true;
		}
		return false;
	}

	//creates a new automata with his number of states (that is
		//equal to the sum of the automata1 with the automata2)
		//and creates a map where the key is the original state 
		//and
		//the value is the state in the new automata
	public Automata generarAutomataGrande(HashMap<Character, 
			Character> mapa){
		//toma el tamaño de ambos automatas
		int tamanioAutomata1 = automata1.getPosEstado()+1;
		int tamanioAutomata2 = automata2.getPosEstado()+1;
		if (tamanioAutomata1!=tamanioAutomata2) {
			//no son equivalentes
		}
		char[] estados1 = automata1.getNombreEstados();
		char[] estados2 = automata2.getNombreEstados();
		boolean tipoAutomata1 = automata1.isTipoAutomata();
		boolean tipoAutomata2 = automata2.isTipoAutomata();
		Automata nuevo = new Automata(ESTADOS_AUTOMATA);
		int aux = tamanioAutomata1+tamanioAutomata2;
		for (int i = 0; i < ESTADOS_AUTOMATA.length || 
				i >= aux; aux++, i++) {
			int acept = 0;
			if (i<tamanioAutomata1) {
				if (tipoAutomata1) {//true si es de moore
					acept = automata1.
							getCharToState().
							get(estados1[i]).
							getAceptacion();
				}
				nuevo.agregarEstado(acept);
				mapa.put(estados1[i], 
						ESTADOS_AUTOMATA[i]);
			}else if(i-tamanioAutomata1<tamanioAutomata2){
				if (tipoAutomata2) {//true si es de moore
					acept = automata2.
							getCharToState().
							get(estados2[i-tamanioAutomata1]).
							getAceptacion();
				}
				nuevo.agregarEstado(acept);
				mapa.put(estados2[i-tamanioAutomata1], 
						ESTADOS_AUTOMATA[i]);
			}
		}
		return nuevo;
	}
	
	public void automataReducido(int numAutomata){
		System.out.println("2.1");
		if (numAutomata==1) {
			System.out.println("2.1.01");
			automata1 = automata1.eliminarNoAlcanzables();
			System.out.println("2.1.1");
			automata1 = automataReducido2(automata1, 
					numAutomata);
//			Automata reducido = automataReducido2(automata1, 
//					numAutomata);
//			return reducido;
			
			
//			ArrayList<int[]> particiones = 
//					automata1.automataReducidoEquivalente();
//			//particion ultima donde esta reducido
//			int[] ultima = particiones.get(particiones.size());
//			//nombres del alfabeto
//			char[] nombres = automata1.getNombreEstados();
//			int limite = automata1.getPosEstado();
//			int nuevo = -1;//representara el numero de la lista 
//			//nueva
//			//mapa de int a char donde el int sera si es igual
//			//y el char sera el estado al q corresponde
//			HashMap<Integer, Character> mapa = 
//					new HashMap<Integer, Character>();
//			//la funcion de char a char
//			HashMap<Character, Character> funcion = 
//					new HashMap<Character, Character>();
//			for (int i = 0; i < limite; i++) {
//				int num = ultima[i];
//				if(!mapa.containsKey(num)){//crea
//					mapa.put(num, nombres[nuevo]);
//					funcion.put(nombres[i], nombres[nuevo]);
//					nuevo++;
//				}else{//busca
//					funcion.put(nombres[i], mapa.get(num));
//				}
//			}
//			//termina los maps
//			//crea el automata
			
		}else{
			automata2 = automata2.eliminarNoAlcanzables();
			automata2 = automataReducido2(automata2, 
					numAutomata);
//			Automata reducido = automataReducido2(automata2, 
//					numAutomata);
//			return reducido;
		}
	}
	
	public Automata automataReducido2(Automata automata, 
			int numAutomata){
		System.out.println("2.2");
		ArrayList<int[]> particiones = 
				automata.automataReducidoEquivalente();
		//particion ultima donde esta reducido
		int[] ultima = particiones.get(particiones.size()-1);
		//nombres del alfabeto
		char[] nombres = automata.getNombreEstados();
		int nuevo = 0;//representara el numero de la lista 
		//nueva
		//mapa de int a char donde el int sera si es igual
		//y el char sera el estado al q corresponde
		HashMap<Integer, Character> mapa = 
				new HashMap<Integer, Character>();
		//la funcion de char a char
		HashMap<Character, Character> funcion = 
				new HashMap<Character, Character>();
		//recorre la ultima particion para hacer la funcion
		//de equivalencias
		for (int i = 0; i < ultima.length; i++) {
			int num = ultima[i];
			//toma los elementos de la particion
			//ultima y limite deberian tener el mismo tamaño
			if(!mapa.containsKey(num)){//crea
				mapa.put(num, nombres[nuevo]);
				funcion.put(nombres[i], nombres[nuevo]);
				nuevo++;
			}else{//busca
				funcion.put(nombres[i], mapa.get(num));
			}
		}
		//termina los maps
		//crea el automata
		
		//automata, funcion, mapa, nuevo(va a representar el
		//tamaño del nuevo automata)
		
		System.out.println("2.3");
		Automata retorno = automataReducido3(automata, funcion,
				mapa, nombres);
		System.out.println("2.5");
		if (numAutomata==1) {
			funcionA1 = funcion;
		}else{
			funcionA2 = funcion;
		}
		return retorno;
	}
	
	
	public Automata automataReducido3(Automata automata,
			HashMap<Character, Character> funcion,
			HashMap<Integer, Character> mapa, 
			char[] nombres) {
		System.out.println("2.4");
		Automata retorno = new Automata(nombres);
		boolean tipo = automata.isTipoAutomata();
		//mapa tendra el tamaño de los estados
		
		//agregar los estados y las transiciones
		HashSet<Character> agregados = new HashSet<Character>();
		int posActual = 0;
		if (tipo) {
			char[][] matriz = new char[mapa.size()][3];
			for (int i = 0; i < mapa.size(); i++) {
				char key = nombres[i];
				if (i==0 || 
						!agregados.contains(funcion.get(key))) {
					matriz[posActual][0] = key;
					matriz[posActual][1] = automata.
							simboloSalida(key, 'a');
					matriz[posActual][2] = automata.
							simboloSalida(key, 'b');
					retorno.agregarEstado(automata.
							getCharToState().get(key).
							getAceptacion());
					posActual++;
					agregados.add(key);
				}
			}
			//agrega las transiciones
			retorno.generarMatriz(tipo);
			for (int i = 0; i < matriz.length; i++) {
				System.out.println(i+"");
				retorno.agregarTransicion(matriz[i][0], 
						matriz[i][1], 'a');
				retorno.agregarTransicion(matriz[i][0], 
						matriz[i][2], 'b');
			}
		}else{
			char[][] matriz = new char[mapa.size()][5];
			for (int i = 0; i < mapa.size(); i++) {
				char key = nombres[i];
				if (i==0 || 
						!agregados.contains(funcion.get(key))) {
					matriz[posActual][0] = key;
					matriz[posActual][1] = automata.
							simboloSalida(key, 'a');
					matriz[posActual][2] = automata.
							simboloSalida(key, 'b');
					matriz[posActual][3] = automata.salida(key,
							'a');
					matriz[posActual][4] = automata.salida(key,
							'b');
					retorno.agregarEstado(0);
					posActual++;
					agregados.add(key);
				}
			}
			//agrega las transiciones
			retorno.generarMatriz(tipo);
			for (int i = 0; i < matriz.length; i++) {
				retorno.agregarTransicion(matriz[i][0], 
						matriz[i][1], 'a', matriz[i][3]);
				retorno.agregarTransicion(matriz[i][0], 
						matriz[i][2], 'b', matriz[i][4]);
			}
		}		
		return retorno;
	}

	public void setAutomata1(Automata automata1) {
		this.automata1 = automata1;
	}

	public void setAutomata2(Automata automata2) {
		this.automata2 = automata2;
	}

	public void reiniciarAutomata(int numeroAutomata) {
		if (numeroAutomata==1) {
			automata1 = new Automata(ESTADOS_AUTOMATA_1);
			funcionA1 = new HashMap<Character, Character>();
		}else{
			automata2 = new Automata(ESTADOS_AUTOMATA_2);
			funcionA2 = new HashMap<Character, Character>();
		}
	}

	public String cadenaLogitudMinima() {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] getTraduccion() {
		String[] retorno = new String[10];
		
		
		return retorno;
	}
	
}
