package des.springboot_hibernate.servicios;

import java.util.List;

import des.springboot_hibernate.entidades.Modulo;

public interface ModuloServicio {

	public List<Modulo> listarModulos();
	
	public List<Modulo> listarModulosPorNombre(String nombreModulo);

	public Modulo obtenerModulo(long idModulo);
	
	public Modulo agregarProfesor(long idModulo, long idProfesor);
	
	public Modulo eliminarProfesor(long idModulo, long idProfesor);

}
