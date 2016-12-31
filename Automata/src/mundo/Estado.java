package mundo;

public class Estado implements Comparable<Estado>{
	
	public final static int ACEPTACION = 1;
	public final static int RECHAZO = 0;
	
	private char nombre;
	private int aceptacion;//tambn es la salida
	
	public Estado(char name, int acepta){
		nombre = name;
		aceptacion = acepta;
	}
	
	@Override
	public int hashCode(){
		return nombre;
	}
	
	public char getNombre() {
		return nombre;
	}

	public void setNombre(char nombre) {
		this.nombre = nombre;
	}

	public int getAceptacion() {
		return aceptacion;
	}

	public void setAceptacion(int aceptacion) {
		this.aceptacion = aceptacion;
	}
	
	public String toString(){
		return nombre+" "+aceptacion;
	}

	@Override
	public int compareTo(Estado o) {
		int retorno = nombre-o.nombre;
		if (retorno==0) {
			retorno = aceptacion-o.aceptacion;
		}
		return retorno;
	}

	
}
