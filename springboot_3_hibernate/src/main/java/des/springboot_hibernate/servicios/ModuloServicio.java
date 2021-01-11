package des.springboot_hibernate.servicios;

import java.util.List;

import des.springboot_hibernate.entidades.Modulo;

public interface ModuloServicio {
	
	public Modulo crearModulo(Modulo modulo);

	public Modulo obtenerModulo(long idModulo);
	
	public void eliminarModulo(long idModulo);

	public List<Modulo> listarModulos();
	
	public List<Modulo> listarModulosPorNombre(String nombreModulo);
	
	public Modulo matricularProfesor(long idModulo, long idProfesor);

	public Modulo matricularProfesores(long idModulo, List<Long>idProfesores);
	
	public Modulo desmatricularProfesor(long idModulo, long idProfesor);
}
