package des.springboot_hibernate_security.dao;

import java.util.List;

import des.springboot_hibernate_security.entidades.Modulo;
import des.springboot_hibernate_security.entidades.Profesor;

public interface ModuloDao extends DaoGenerico<Modulo>{

	public List<Modulo> listarModulos();
	
	public List<Modulo> listarModulosPorNombre(String nombreModulo);
	
	public Modulo agregarProfesor(long idModulo, Profesor profesor);
	
	public Modulo eliminarProfesor(long idModulo, Profesor profesor);

}