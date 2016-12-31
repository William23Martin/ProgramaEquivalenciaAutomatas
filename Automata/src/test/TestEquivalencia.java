package test;

import static org.junit.Assert.*;
import mundo.Equivalencia;

import org.junit.Test;

public class TestEquivalencia {
	
	private Equivalencia equivalencia;
	
	public void setUpEscenario1(){
		equivalencia = new Equivalencia();
		equivalencia.agregarEstado(1, 1);
		equivalencia.agregarEstado(1, 0);
		equivalencia.agregarEstado(1, 1);
		equivalencia.agregarEstado(1, 0);
		equivalencia.agregarEstado(1, 1);
		equivalencia.agregarEstado(1, 1);
		
		equivalencia.generarMatriz(1, true);
		
		equivalencia.agregarTransicion(0, 'C', 1, 'a', '0');
		equivalencia.agregarTransicion(0, 'B', 1, 'b', '0');
		
		equivalencia.agregarTransicion(1, 'C', 1, 'a', '0');
		equivalencia.agregarTransicion(1, 'E', 1, 'b', '0');
		
		equivalencia.agregarTransicion(2, 'B', 1, 'a', '0');
		equivalencia.agregarTransicion(2, 'E', 1, 'b', '0');
		
		equivalencia.agregarTransicion(3, 'A', 1, 'a', '0');
		equivalencia.agregarTransicion(3, 'B', 1, 'b', '0');
		
		equivalencia.agregarTransicion(4, 'E', 1, 'a', '0');
		equivalencia.agregarTransicion(4, 'A', 1, 'b', '0');
		
		
		equivalencia.agregarEstado(0, 1);
		equivalencia.agregarEstado(0, 1);
		equivalencia.agregarEstado(0, 1);
		equivalencia.agregarEstado(0, 1);
		equivalencia.agregarEstado(0, 1);
		
		equivalencia.generarMatriz(0, false);
		
		equivalencia.agregarTransicion(0, 'N', 0, 'a', '0');
		equivalencia.agregarTransicion(0, 'M', 0, 'b', '1');
		
		equivalencia.agregarTransicion(1, 'N', 0, 'a', '1');
		equivalencia.agregarTransicion(1, 'P', 0, 'b', '0');
		
		equivalencia.agregarTransicion(2, 'M', 0, 'a', '0');
		equivalencia.agregarTransicion(2, 'P', 0, 'b', '0');
		
		equivalencia.agregarTransicion(3, 'L', 0, 'a', '1');
		equivalencia.agregarTransicion(3, 'M', 0, 'b', '0');
		
		equivalencia.agregarTransicion(4, 'P', 0, 'a', '1');
		equivalencia.agregarTransicion(4, 'L', 0, 'b', '1');
		
	}
	
//	@Test
	public void testAutomataReducido1() {
		setUpEscenario1();
		equivalencia.automataReducido(1);
		equivalencia.automataReducido(0);
	}
	
	public void setUpEscenario2(){
		equivalencia = new Equivalencia();
		equivalencia.agregarEstado(0, 0);
		equivalencia.agregarEstado(0, 1);
		equivalencia.agregarEstado(0, 1);
		equivalencia.agregarEstado(0, 0);
		equivalencia.agregarEstado(0, 1);
		
		equivalencia.generarMatriz(0, true);
		
		equivalencia.agregarTransicion(0, 'O', 0, 'a', '0');
		equivalencia.agregarTransicion(0, 'M', 0, 'b', '1');
		
		equivalencia.agregarTransicion(1, 'M', 0, 'a', '1');
		equivalencia.agregarTransicion(1, 'N', 0, 'b', '0');
		
		equivalencia.agregarTransicion(2, 'N', 0, 'a', '0');
		equivalencia.agregarTransicion(2, 'N', 0, 'b', '0');
		
		equivalencia.agregarTransicion(3, 'P', 0, 'a', '1');
		equivalencia.agregarTransicion(3, 'M', 0, 'b', '0');
		
		equivalencia.agregarTransicion(4, 'O', 0, 'a', '1');
		equivalencia.agregarTransicion(4, 'N', 0, 'b', '1');
		
	}
	
	@Test
	public void testAutomataReducido2() {
		setUpEscenario2();
		equivalencia.automataReducido(0);
	}
	
//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}

}
