package modelo;

public class EstadoMealy extends AbstractEstado{

	public EstadoMealy(char representacion) {
	
		super(representacion);
	
	}

	public String toString(){
		
		return "( "+ representacion +" )";
		
	}
	
	
	
}
