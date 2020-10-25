package des.springboot_hibernate.servicios;

import java.util.List;

import des.springboot_hibernate.entidades.Email;
import des.springboot_hibernate.entidades.Modulo;
import des.springboot_hibernate.entidades.Profesor;

public interface ProfesorService {

public Profesor crearPorfesor(Profesor porfesor);
	
	public Profesor obtenerProfesor(long idProfesor);
	
	public List<Profesor> listarProfesores();
	
	public Profesor anadirEmail(long idProfesor, Email email);
	
	public void eliminarPorfesor (long idProfesor);
	
	public List<Modulo> listarModulosNombre(String nombreModulo);
	
	public List<Modulo> listarModulosProfesor (long idProfesor);
	
	public List<Modulo> listarModulos();
	
	public Email crearEmail(Email email);
	
	public void eliminarEmail(long idProfesor, Email email);
	
	public Profesor findByUsername(String username);
	
	public Profesor modificarProfesor(Profesor profesor);
	
}
