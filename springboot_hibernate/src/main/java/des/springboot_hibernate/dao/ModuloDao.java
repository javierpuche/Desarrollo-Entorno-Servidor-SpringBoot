package des.springboot_hibernate.dao;

import java.util.List;

import des.springboot_hibernate.entidades.Modulo;
import des.springboot_hibernate.entidades.Profesor;

public interface ModuloDao extends DaoGenerico<Modulo>{

	public List<Modulo> listarModulos();
	
	public List<Modulo> listarModulosPorNombre(String nombreModulo);
	
	public Modulo agregarProfesor(long idModulo, Profesor profesor);
	
	public Modulo eliminarProfesor(long idModulo, Profesor profesor);

}