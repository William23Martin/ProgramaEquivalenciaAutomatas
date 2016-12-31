package mundo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
public class Automata {
	
	public final static char[] ALFABETO_ENTRADA = {'a','b'};
	public final static char[] ALFABETO_SALIDA = {'0','1'};
	
	private Estado[][] funcion;
	private HashMap<Estado, Integer> estados;
	private HashMap<Character, Estado> charToState;
	private char[] nombreEstados;
	private int posEstado;
	
	private char[][] mealy;//representara la transicion
	private boolean tipoAutomata;//true si es de moore

	public Automata(char[] nomEstados){
		nombreEstados = nomEstados;
		estados = new HashMap<Estado, Integer>();
		charToState = new HashMap<>();
		posEstado = 0;
		
		tipoAutomata = true;//representa que es de moore
//		mealy = null;//por este atributo se sabe si es de  
		//mealy, es decir, si es null es de moore, 
		//sino es de mealy
	}
	
	public int charToInt(char estadoChar){
		return estados.get(charToState.get(estadoChar));
	}
	
	public int simboloEntradaToInt(char simbolo){
		if (simbolo==ALFABETO_ENTRADA[0]) {
			return 0;
		}else{
			return 1;
		}
	}
	
	//se asume q la matriz ya se inicializo
	public char simboloSalida(char estadoEntrada, 
			char simboloEntrada){
		int x = charToInt(estadoEntrada);
		int y = simboloEntradaToInt(simboloEntrada);
		return funcion[x][y].getNombre();
	}
	
	public char salida(char estadoEntrada, char simboloEntrada){
		int x = charToInt(estadoEntrada);
		int y = simboloEntradaToInt(simboloEntrada);
		
		if (!tipoAutomata) {
			return mealy[x][y];
		}else{
			Estado estado = funcion[x][y];
			return ALFABETO_SALIDA[estado.getAceptacion()];
		}
	}
	
	public void agregarEstado(int acepta){
		char estado = nombreEstados[posEstado];
		//crea el estado llave
		Estado key = new Estado(estado, 
				acepta);
		//a un char le asigna el estado llave
		charToState.put(estado, key);
		//le asigna una posicion a la llave (en la funcion)
		estados.put(key, posEstado);
		posEstado++;
	}
	
	//charEntrada sera el x en la forma de la funcion 
	//f(estado,x)
	public void agregarTransicion(char estadoEntrada, 
			char estadoSalida, char charEntrada){
		//es necesario que se haya generado la matriz
		int pos = 
				estados.get(charToState.get(estadoEntrada));
		Estado llegada = charToState.get(estadoSalida);
		int caracterEntrada = darCaracter(charEntrada);
		funcion[pos][caracterEntrada] = llegada;
	}
	
	//charEntrada sera el x en la forma de la funcion 
	//f(estado,x)
	public void agregarTransicion(char estadoEntrada, 
			char estadoSalida, char charEntrada, 
			char charSalida){
		//es necesario que se haya generado la matriz
		int pos = 
				estados.get(charToState.get(estadoEntrada));
		Estado llegada = charToState.get(estadoSalida);
		int caracterEntrada = darCaracter(charEntrada);
		funcion[pos][caracterEntrada] = llegada;
		mealy[pos][caracterEntrada] = charSalida;
	}
	
	private int darCaracter(char charEntrada) {
		if (ALFABETO_ENTRADA[0]==charEntrada)
			return 0;
		else
			return 1;
	}

	public void generarMatriz(boolean esMoore){
		//cero será a y uno será b
		funcion = new Estado[estados.size()][2];
		if (!esMoore){
			mealy = new char[estados.size()][2];
			tipoAutomata = false;
		}
	}
	
	
	//es para hacer la verificacion de si algun estado 
	//ingresado o caracter pertenece al alfabeto
	//true si es el alfabeto de entrada false, 
	//en el de salida
	//en realidad no es necesario el de salida
	public boolean contained(char comparar, 
			boolean entrada){
		if (entrada) {
			for (int i = 0; i < ALFABETO_ENTRADA.length; 
					i++) {
				if (comparar==ALFABETO_ENTRADA[i]) {
					return true;
				}
			}
			return false;
		}else{
			for (int i = 0; i < ALFABETO_SALIDA.length; 
					i++) {
				if (comparar==ALFABETO_SALIDA[i]) {
					return true;
				}
			}
			return false;
		}
	}
	
	public ArrayList<int[]> 
			automataReducidoEquivalente(){
		ArrayList<int[]> particiones = new 
				ArrayList<int[]>();
		
		int[] particion = null;
		if (tipoAutomata){
			particion = primeraParticion();
		}else{
			particion = primeraParticionMealy();
		}
		
		particiones.add(particion);
		
		boolean siguienteParticion = true;
		
		int tamanio = 2;//debe ser igual
		
		while(siguienteParticion){
			int[] particionSiguiente = 
					siguienteParticion(particion);
			
			int tamanioNuevo = 
					tamanioParticion(particionSiguiente);
			
			siguienteParticion = tamanio!=tamanioNuevo;
			tamanio = tamanioNuevo;
			particion = particionSiguiente;
			if (siguienteParticion) {
				particiones.add(particionSiguiente);
			}
			//generar nuevo automata
		}
		return particiones;
	}

	public int tamanioParticion(int[] particion) {
		int retorno = 1;
		int actual = 0;
		for (Integer integer : particion) {
			if (integer>actual) {
				retorno++;
				actual = integer;
			}
		}
		return retorno;
	}

	public int[] siguienteParticion(int[] 
			particion) {
		int[][] particionSiguiente = new 
				int[particion.length][2];
		for (int i = 0; i < particionSiguiente.length; i++)
			particionSiguiente[i][0] = i;
		
		for (int i = 0; i < particion.length; i++){
			if (particionSiguiente[i][1]==1)
				continue;
			
			for (int j = i+1; j < 
					particionSiguiente.length; j++) {
				//no estan en el mismo coso
				if (particion[i]!=particion[j] || 
						particionSiguiente[j][1]==1)
					continue;
				else{
					Estado[] resultados = 
							funcionEvaluada(i, j);
					//comparar 1,3 y 0,2
					boolean parejaContenida1 = 
							parejaContenida(resultados[0], 
							resultados [2], particion);
					boolean parejaContenida2 = 
							parejaContenida(resultados[1], 
							resultados [3], particion);
					
					if (parejaContenida1&&parejaContenida2){
						particionSiguiente[j][0] = 
								particionSiguiente[i][0];
						particionSiguiente[j][1] = 1;
					}
				}
			}
			particionSiguiente[i][1]=1;
		}
		int tamanio = particionSiguiente.length;
		int[] retorno = new int[tamanio];
		for (int i = 0; i < tamanio; i++) {
			retorno[i] = particionSiguiente[i][0];
		}
		
		return retorno;
	}
	
	public boolean parejaContenida(Estado estado1, 
			Estado estado2, int[] particion) {
		return particion[estados.get(estado1)]==
				particion[estados.get(estado2)];
	}

	private Estado[] funcionEvaluada(int estado1, 
			int estado2) {
		//los 2 primeros estados son del estado1 y los otros
		//2 son del segundo estados
		Estado[] transiciones = new Estado[4];
		for (int i = 0, j = i+2; i < funcion[0].length; 
				i++, j++) {
			transiciones[i]=funcion[estado1][i];
			transiciones[j]=funcion[estado2][i];
		}
		return transiciones;
	}
	
	public int[] primeraParticionMealy(){
		//compara
		int[] retorno = 
				new int[estados.size()];
		Iterator<Estado> iter = estados.keySet().iterator();
		while (iter.hasNext()) {
			Estado estado = (Estado) iter.next();
			int pos = estados.get(estado);
			char[] respuesta = new char[2];
			respuesta[0] = mealy[pos][0];
			respuesta[1] = mealy[pos][1];
			
			if (respuesta[0]=='0') {
				if (respuesta[1]=='0') {
					retorno[pos] = 0;//00
				}else{
					retorno[pos] = 1;//01
				}
			}else{
				if (respuesta[1]=='0') {
					retorno[pos] = 2;//10
				}else{
					retorno[pos] = 3;//11
				}
			}
		}
		
		//organiza
		int pos00 = -1;
		int pos01 = -1;
		int pos10 = -1;
		int pos11 = -1;
		for (int i = 0; i < retorno.length; i++) {
			switch (retorno[i]) {
			case 0:
				if (pos00==-1) {
					pos00 = i;
				}
				retorno[i] = pos00;
				break;
			case 1:
				if (pos01==-1) {
					pos01 = i;
				}
				retorno[i] = pos01;
				break;
			case 2:
				if (pos10==-1) {
					pos10 = i;
				}
				retorno[i] = pos10;
				break;
			
			default:
				if (pos11==-1) {
					pos11 = i;
				}
				retorno[i] = pos11;
				break;
			}
		}
		return retorno;
	}	
	
	
	public int[] primeraParticion(){
		//compara
		int[] retorno = 
				new int[estados.size()];
		Iterator<Estado> iter = estados.keySet().iterator();
		while (iter.hasNext()) {
			Estado estado = (Estado) iter.next();
			if (estado.getAceptacion()==0) {
				retorno[estados.get(estado)] = 0;
			}else{
				retorno[estados.get(estado)] = 1;
			}
		}
		
		//organiza
		int pos0 = -1;
		int pos1 = -1;
		for (int i = 0; i < retorno.length; i++) {
			if (retorno[i]==0) {
				if (pos0==-1)
					pos0 = i;
				retorno[i] = pos0;
			}else{
				if (pos1==-1)
					pos1 = i;
				retorno[i] = pos1;
			}
		}
		return retorno;
	}
	
	
	public Automata eliminarNoAlcanzables(){
		Automata retorno = new Automata(nombreEstados);
		
		char[] visitados = new char[nombreEstados.length];
		for (int i = 0; i < posEstado; i++) {
			visitados[i] = '&';//significa no visitado
		}
		
		//recorre todos los estados
		Stack<Character> pila = new Stack<Character>();
		pila.push(nombreEstados[0]);
		while(!pila.empty()){
			char actual = pila.pop();
			//si no ha sido visitado
			int pos = charToInt(actual);
			if (visitados[pos]=='&') {
				//evualuar la funcion, marcarlo como visitado 
				//y meter las imagenes
				visitados[pos] = '%';//significa q esta visitado
				pila.push(simboloSalida(nombreEstados[pos], 
						'a'));
				pila.push(simboloSalida(nombreEstados[pos], 
						'b'));
			}
		}
		
		//recorre los char con visitado y agrega los q han sido
		//visitados
		ArrayList<Integer> enFuncion = 
				new ArrayList<Integer>();
		HashMap<Integer, Character> mapa = 
				new HashMap<Integer, Character>();
		HashMap<Character, Character> funcionToChar = 
				new HashMap<Character, Character>();
		int posNuevo = 0;
		for (int i = 0; i < posEstado; i++) {
			//ha sido visitado, sino simplemente q siga
			if (visitados[i]=='%') {
				//agrega el estado
				char agegar = nombreEstados[i];
				funcionToChar.put(agegar, 
						retorno.nombreEstados[posNuevo]);
				mapa.put(i, nombreEstados[posNuevo]);
				enFuncion.add(i);
				int acept = 0;
				if (tipoAutomata) {//TRUE SI ES DE MOORE
//					int salida = charToState.get(i).
//							getAceptacion();
					Estado out = charToState.get(agegar);
					int salida = out.getAceptacion();
					acept = salida;
				}
				retorno.agregarEstado(acept);
				posNuevo++;
			}
		}
		retorno.generarMatriz(tipoAutomata);
		//crea las transiciones
		for (Integer integer : enFuncion) {
			//cada int tiene su valor
			char estadoEntrada = mapa.get(integer);
			char estadoSalidaA = 
					funcion[integer][0].getNombre();
			char estadoSalidaB = 
					funcion[integer][1].getNombre();
			if (tipoAutomata) {
				retorno.agregarTransicion(estadoEntrada, 
						funcionToChar.get(estadoSalidaA), 'a');
				retorno.agregarTransicion(estadoEntrada, 
						funcionToChar.get(estadoSalidaB), 'b');
			}else{
				retorno.agregarTransicion(estadoEntrada, 
						funcionToChar.get(estadoSalidaA), 'a', 
						mealy[integer][0]);
				retorno.agregarTransicion(estadoEntrada, 
						funcionToChar.get(estadoSalidaB), 'b', 
						mealy[integer][1]);
			}
		}
		
		return retorno;
	}
	
	public Estado[][] getFuncion() {
		return funcion;
	}

	public char[][] getMealy() {
		return mealy;
	}
	
	public HashMap<Estado, Integer> getEstados() {
		return estados;
	}

	public HashMap<Character, Estado> getCharToState() {
		return charToState;
	}

	public char[] getNombreEstados() {
		return nombreEstados;
	}

	public int getPosEstado() {
		return posEstado;
	}
	
	public boolean isTipoAutomata() {
		return tipoAutomata;
	}
	
}
