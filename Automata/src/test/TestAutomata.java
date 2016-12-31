package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import mundo.Automata;
import mundo.Equivalencia;
import mundo.Estado;

import org.junit.Test;

public class TestAutomata {

	private Automata automata;
	
	private void setUpEscenario1(){
		automata = new 
				Automata(Equivalencia.ESTADOS_AUTOMATA_2);
		automata.agregarEstado(0);
		automata.agregarEstado(1);
		automata.agregarEstado(1);
		automata.agregarEstado(0);
		automata.agregarEstado(1);
		
//		automata.agregarEstado(1);
//		automata.agregarEstado(0);
//		automata.agregarEstado(0);
//		automata.agregarEstado(1);
//		automata.agregarEstado(0);
		
		automata.generarMatriz(true);
	}
	
	private void setUpEscenario2(){
		automata = new 
				Automata(Equivalencia.ESTADOS_AUTOMATA_1);
		automata.agregarEstado(1);
		automata.agregarEstado(1);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(1);
		
		automata.generarMatriz(false);
	}

	private void setUpEscenario3(){
		automata = new 
				Automata(Equivalencia.ESTADOS_AUTOMATA);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(1);
		automata.agregarEstado(0);
		automata.agregarEstado(1);
		
		automata.generarMatriz(true);
		
		automata.agregarTransicion('a', 'b', 'a');
		automata.agregarTransicion('a', 'a', 'b');
		
		automata.agregarTransicion('b', 'c', 'a');
		automata.agregarTransicion('b', 'd', 'b');
		
		automata.agregarTransicion('c', 'e', 'a');
		automata.agregarTransicion('c', 'c', 'b');
		
		automata.agregarTransicion('d', 'f', 'a');
		automata.agregarTransicion('d', 'b', 'b');
		
		automata.agregarTransicion('e', 'g', 'a');
		automata.agregarTransicion('e', 'e', 'b');
		
		automata.agregarTransicion('f', 'h', 'a');
		automata.agregarTransicion('f', 'f', 'b');
		
		automata.agregarTransicion('g', 'i', 'a');
		automata.agregarTransicion('g', 'g', 'b');
		
		automata.agregarTransicion('h', 'j', 'a');
		automata.agregarTransicion('h', 'h', 'b');
		
		automata.agregarTransicion('i', 'a', 'a');
		automata.agregarTransicion('i', 'k', 'b');
		
		automata.agregarTransicion('j', 'k', 'a');
		automata.agregarTransicion('j', 'j', 'b');
		
		automata.agregarTransicion('k', 'a', 'a');
		automata.agregarTransicion('k', 'k', 'b');
		
	}
	
	private void setUpEscenario4(){
		setUpEscenario1();
		automata.agregarTransicion('L', 'O', 'a');
		automata.agregarTransicion('L', 'M', 'b');
		
		automata.agregarTransicion('M', 'M', 'a');
		automata.agregarTransicion('M', 'N', 'b');
		
		automata.agregarTransicion('N', 'N', 'a');
		automata.agregarTransicion('N', 'N', 'b');
		
		automata.agregarTransicion('O', 'P', 'a');
		automata.agregarTransicion('O', 'M', 'b');
		
		automata.agregarTransicion('P', 'O', 'a');
		automata.agregarTransicion('P', 'N', 'b');
	}
	
	private void setUpEscenario5(){
		automata = new 
				Automata(Equivalencia.ESTADOS_AUTOMATA_1);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(1);
		
		automata.generarMatriz(true);
		
		automata.agregarTransicion('A', 'B', 'a');
		automata.agregarTransicion('A', 'A', 'b');
		
		automata.agregarTransicion('B', 'C', 'a');
		automata.agregarTransicion('B', 'B', 'b');
		
		automata.agregarTransicion('C', 'D', 'a');
		automata.agregarTransicion('C', 'C', 'b');
		
		automata.agregarTransicion('D', 'E', 'a');
		automata.agregarTransicion('D', 'D', 'b');
		
		automata.agregarTransicion('E', 'F', 'a');
		automata.agregarTransicion('E', 'E', 'b');
		
		automata.agregarTransicion('F', 'B', 'a');
		automata.agregarTransicion('F', 'F', 'b');
	}
	
//	@Test //TODO
//	public void test() {
//		fail("Not yet implemented");
//	}
	
	
//	@Test
	public void testAgregarEstado1() {
		automata = new 
				Automata(Equivalencia.ESTADOS_AUTOMATA_1);
		automata.agregarEstado(0);
		automata.agregarEstado(1);
		automata.agregarEstado(1);
		automata.agregarEstado(0);
		automata.agregarEstado(1);
		assertEquals(5, automata.getEstados().size());
		assertEquals(5, automata.getCharToState().size());
		assertEquals(5, automata.getEstados().size());
	}
	
//	@Test
	public void testAgregarEstado2() {
		automata = new 
				Automata(Equivalencia.ESTADOS_AUTOMATA_2);
		automata.agregarEstado(1);
		automata.agregarEstado(1);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(1);
		assertEquals(6, automata.getEstados().size());
		assertEquals(6, automata.getCharToState().size());
		assertEquals(6, automata.getEstados().size());
	}
	
//	@Test
	public void testAgregarEstado3() {
		automata = new 
				Automata(Equivalencia.ESTADOS_AUTOMATA);
		automata.agregarEstado(1);
		automata.agregarEstado(1);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(1);
		automata.agregarEstado(1);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(1);
		automata.agregarEstado(1);
		automata.agregarEstado(1);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(1);
		automata.agregarEstado(0);
		automata.agregarEstado(1);
		
		assertEquals(18, automata.getEstados().size());
		assertEquals(18, automata.getCharToState().size());
		assertEquals(18, automata.getEstados().size());
	}
	
//	@Test
	public void testAgregarTransicion1() {
		//{'A','B','C','D','E','F'};
		setUpEscenario2();
		automata.agregarTransicion('A', 'B', 'a');
		automata.agregarTransicion('A', 'C', 'b');
		
		automata.agregarTransicion('B', 'C', 'a');
		automata.agregarTransicion('B', 'B', 'b');
		
		automata.agregarTransicion('C', 'B', 'a');
		automata.agregarTransicion('C', 'D', 'b');
		
		automata.agregarTransicion('D', 'E', 'a');
		automata.agregarTransicion('D', 'D', 'b');
		
		automata.agregarTransicion('E', 'D', 'a');
		automata.agregarTransicion('E', 'C', 'b');
		
		automata.agregarTransicion('F', 'F', 'a');
		automata.agregarTransicion('F', 'B', 'b');
		
		Estado[][] funcion = automata.getFuncion();
		String real = "B 1 C 0 C 0 B 1 B 1 D 0 E 0 D 0 D 0 "
				+ "C 0 F 1 B 1 ";
		String comparar = "";
		for (int i = 0; i < funcion.length; i++) {
			for (int j = 0; j < funcion[0].length; j++) {
				comparar+=funcion[i][j].toString()+" ";
			}
		}
		assertEquals(real, comparar);
	}
	
//	@Test
	public void testAgregarTransicion2() {
		//{'L','M','N','O','P'}
		setUpEscenario1();
		automata.agregarTransicion('L', 'O', 'a');
		automata.agregarTransicion('L', 'M', 'b');
		
		automata.agregarTransicion('M', 'M', 'a');
		automata.agregarTransicion('M', 'N', 'b');
		
		automata.agregarTransicion('N', 'P', 'a');
		automata.agregarTransicion('N', 'N', 'b');
		
		automata.agregarTransicion('O', 'N', 'a');
		automata.agregarTransicion('O', 'N', 'b');
		
		automata.agregarTransicion('P', 'O', 'a');
		automata.agregarTransicion('P', 'L', 'b');
		
		Estado[][] funcion = automata.getFuncion();
		String real = "O 0 M 1 M 1 N 1 P 1 N 1 N 1 "
				+ "N 1 O 0 L 0 ";
		String comparar = "";
		for (int i = 0; i < funcion.length; i++) {
			for (int j = 0; j < funcion[0].length; j++) {
				comparar+=funcion[i][j].toString()+" ";
			}
		}
		assertEquals(real, comparar);
	}
	
//	@Test
	public void testParejaContenida1() {
		char[] caracteres = Equivalencia.ESTADOS_AUTOMATA;
		automata = new Automata(caracteres);
		
	}
	
//	@Test //TODO
//	public void testParejaContenida2() {
//		
//	}
	
//	@Test
	public void testContained() {
		automata = new 
				Automata(Equivalencia.ESTADOS_AUTOMATA);
		assertTrue(automata.contained('a', true));
		assertTrue(automata.contained('b', true));
		assertFalse(automata.contained('1', true));
		assertFalse(automata.contained('0', true));
		
		assertFalse(automata.contained('a', false));
		assertFalse(automata.contained('b', false));
		assertTrue(automata.contained('1', false));
		assertTrue(automata.contained('0', false));
	}
	
//	@Test
	public void testPrimeraParticion1() {
		setUpEscenario4();
		int[] lista = 
				automata.primeraParticion();
		assertEquals(2, automata.tamanioParticion(lista));
		
		lista = automata.siguienteParticion(lista);
		assertEquals(4, automata.tamanioParticion(lista));
		
		lista = automata.siguienteParticion(lista);
		assertEquals(4, automata.tamanioParticion(lista));
	}
	
//	@Test
//	public void testPrimeraParticion2() {
//		
//	}
	
	
//	@Test
//	public void testPrimeraParticion3() {
//		setUpEscenario();
//	}
	
//	@Test
	public void testGrande(){
		setUpEscenario4();
		ArrayList<int[]> particiones = 
				automata.automataReducidoEquivalente();
		assertEquals(2, particiones.size());
	}
	
//	@Test
	public void testGrande2(){
		setUpEscenario5();
		ArrayList<int[]> particiones = 
				automata.automataReducidoEquivalente();
		assertEquals(5, particiones.size());
	}
	
//	@Test
	public void testGrande3(){
		setUpEscenario3();
		ArrayList<int[]> particiones = 
				automata.automataReducidoEquivalente();
		assertEquals(5, particiones.size());
//		for (int i = 0; i < particiones.size(); i++) {
//			int[] conjunto = particiones.get(i);
//			String mostrar = "";
//			for (int j = 0; j < conjunto.length; j++) {
//				mostrar+=conjunto[j]+",";
//			}
//			System.out.println(mostrar);
//		}
	}
	
//	@Test
	public void testGrande4(){
		setUpEscenario6();
		ArrayList<int[]> particiones = 
				automata.automataReducidoEquivalente();
		assertEquals(3, particiones.size());
	}
	
	private void setUpEscenario6(){
		automata = new 
				Automata(Equivalencia.ESTADOS_AUTOMATA_1);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		
		automata.generarMatriz(false);
		
		automata.agregarTransicion('A', 'C', 'a','1');
		automata.agregarTransicion('A', 'B', 'b','0');
		
		automata.agregarTransicion('B', 'C', 'a','1');
		automata.agregarTransicion('B', 'E', 'b','0');
		
		automata.agregarTransicion('C', 'B', 'a','1');
		automata.agregarTransicion('C', 'E', 'b','0');
		
		automata.agregarTransicion('D', 'D', 'a','0');
		automata.agregarTransicion('D', 'B', 'b','1');
		
		automata.agregarTransicion('E', 'E', 'a','0');
		automata.agregarTransicion('E', 'A', 'b','1');
	}
	
//	@Test
	public void testGrande5(){
		setUpEscenario7();
		ArrayList<int[]> particiones = 
				automata.automataReducidoEquivalente();
		assertEquals(2, particiones.size());
	}
	
	private void setUpEscenario7(){
		automata = new 
				Automata(Equivalencia.ESTADOS_AUTOMATA_1);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		
		automata.generarMatriz(false);
		
		automata.agregarTransicion('A', 'E', 'a','0');
		automata.agregarTransicion('A', 'D', 'b','0');
		
		automata.agregarTransicion('B', 'A', 'a','1');
		automata.agregarTransicion('B', 'F', 'b','0');
		
		automata.agregarTransicion('C', 'C', 'a','0');
		automata.agregarTransicion('C', 'A', 'b','1');
		
		automata.agregarTransicion('D', 'B', 'a','0');
		automata.agregarTransicion('D', 'A', 'b','0');
		
		automata.agregarTransicion('E', 'D', 'a','1');
		automata.agregarTransicion('E', 'C', 'b','0');
		
		automata.agregarTransicion('F', 'C', 'a','0');
		automata.agregarTransicion('F', 'D', 'b','1');
		
		automata.agregarTransicion('G', 'H', 'a','1');
		automata.agregarTransicion('G', 'G', 'b','1');
		
		automata.agregarTransicion('H', 'C', 'a','1');
		automata.agregarTransicion('H', 'B', 'b','1');
	}
	
//	@Test
	public void testGrande6(){
		setUpEscenario8();
		ArrayList<int[]> particiones = 
				automata.automataReducidoEquivalente();
		assertEquals(3, particiones.size());
	}
	
	private void setUpEscenario8(){
		automata = new 
				Automata(Equivalencia.ESTADOS_AUTOMATA_1);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		
		automata.generarMatriz(true);
		
		automata.agregarTransicion('A', 'B', 'a','0');
		automata.agregarTransicion('A', 'C', 'b','0');
		
		automata.agregarTransicion('B', 'G', 'a','0');
		automata.agregarTransicion('B', 'B', 'b','0');
		
		automata.agregarTransicion('C', 'D', 'a','0');
		automata.agregarTransicion('C', 'A', 'b','0');
		
		automata.agregarTransicion('D', 'G', 'a','0');
		automata.agregarTransicion('D', 'B', 'b','0');
		
		automata.agregarTransicion('E', 'B', 'a','0');
		automata.agregarTransicion('E', 'E', 'b','0');
		
		automata.agregarTransicion('F', 'A', 'a','0');
		automata.agregarTransicion('F', 'D', 'b','1');
		
		automata.agregarTransicion('G', 'A', 'a','0');
		automata.agregarTransicion('G', 'F', 'b','0');
	}
	
	private void setUpEscenario9(){
		automata = new 
				Automata(Equivalencia.ESTADOS_AUTOMATA_1);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		
		automata.generarMatriz(true);
		
		automata.agregarTransicion('A', 'C', 'a');
		automata.agregarTransicion('A', 'B', 'b');
		
		automata.agregarTransicion('B', 'C', 'a');
		automata.agregarTransicion('B', 'E', 'b');
		
		automata.agregarTransicion('C', 'B', 'a');
		automata.agregarTransicion('C', 'E', 'b');
		
		//inalcanzable
		automata.agregarTransicion('D', 'A', 'a');
		automata.agregarTransicion('D', 'B', 'b');
		
		automata.agregarTransicion('E', 'E', 'a');
		automata.agregarTransicion('E', 'A', 'b');
	}
	
//	@Test
	public void testEliminarInalcanzables1(){
		setUpEscenario9();
		automata = automata.eliminarNoAlcanzables();
		Estado[][] funcion = automata.getFuncion();
		//igual a 4
		for (int i = 0; i < funcion.length; i++) {
			String r = "";
			r+=funcion[i][0].getNombre();
			r+=funcion[i][1].getNombre();
			System.out.println(r);
		}
	}
	
	private void setUpEscenario10(){
		automata = new 
				Automata(Equivalencia.ESTADOS_AUTOMATA_1);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		
		automata.generarMatriz(true);
		
		automata.agregarTransicion('A', 'A', 'a');
		automata.agregarTransicion('A', 'C', 'b');

		//inalcanzable
		automata.agregarTransicion('B', 'C', 'a');
		automata.agregarTransicion('B', 'D', 'b');
		
		automata.agregarTransicion('C', 'D', 'a');
		automata.agregarTransicion('C', 'F', 'b');
		
		automata.agregarTransicion('D', 'C', 'a');
		automata.agregarTransicion('D', 'D', 'b');
		
		automata.agregarTransicion('E', 'A', 'a');
		automata.agregarTransicion('E', 'F', 'b');
		
		//inalcanzable
		automata.agregarTransicion('F', 'D', 'a');
		automata.agregarTransicion('F', 'A', 'b');
	}
	
//	@Test
	public void testEliminarInalcanzables2(){
		setUpEscenario10();
		automata = automata.eliminarNoAlcanzables();
		Estado[][] funcion = automata.getFuncion();
		//igual a 4
		for (int i = 0; i < funcion.length; i++) {
			String r = "";
			r+=funcion[i][0].getNombre();
			r+=funcion[i][1].getNombre();
			System.out.println(r);
		}
	}
	
	private void setUpEscenario11(){
		automata = new 
				Automata(Equivalencia.ESTADOS_AUTOMATA_1);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		automata.agregarEstado(0);
		
		automata.generarMatriz(false);
		
		automata.agregarTransicion('A', 'A', 'a','1');
		automata.agregarTransicion('A', 'C', 'b','0');

		//inalcanzable
		automata.agregarTransicion('B', 'C', 'a','1');
		automata.agregarTransicion('B', 'D', 'b','0');
		
		automata.agregarTransicion('C', 'D', 'a','1');
		automata.agregarTransicion('C', 'F', 'b','0');
		
		automata.agregarTransicion('D', 'C', 'a','0');
		automata.agregarTransicion('D', 'D', 'b','1');
		
		automata.agregarTransicion('E', 'A', 'a','0');
		automata.agregarTransicion('E', 'F', 'b','1');
		
		//inalcanzable
		automata.agregarTransicion('F', 'D', 'a','0');
		automata.agregarTransicion('F', 'A', 'b','1');
	}
	
	@Test
	public void testEliminarInalcanzables3(){
		setUpEscenario11();
		automata = automata.eliminarNoAlcanzables();
		Estado[][] funcion = automata.getFuncion();
		char[][] fn2 = automata.getMealy();
		//igual a 4
		for (int i = 0; i < funcion.length; i++) {
			String r = "";
			r+=funcion[i][0].getNombre()+" "+fn2[i][0];
			r+=funcion[i][1].getNombre()+" "+fn2[i][1];
			System.out.println(r);
		}
	}
	
}
