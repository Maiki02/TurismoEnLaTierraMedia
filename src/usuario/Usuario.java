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
<<<<<<< HEAD
	
=======
	public int holaGrupo() {
		System.out.println("Lunes martes miercoles");
		return 0;
	}
>>>>>>> 17495f9aeb57f3ccd8fa2d06031ca9b12c358fa2
}
