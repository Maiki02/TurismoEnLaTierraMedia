package usuario;

public class Usuario {
	
	private String nombre;
	private double monedasDisponibles;
	
	public Usuario(String nombre, double monedasIniciales) {
		this.nombre=nombre;
		this.monedasDisponibles=monedasIniciales;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	public int holaGrupo() {
		return 0;
	}
}
