package des.springboot_hibernate.servicios;

import java.util.List;

import des.springboot_hibernate.entidades.Email;
import des.springboot_hibernate.entidades.Modulo;
import des.springboot_hibernate.entidades.Profesor;

public interface ProfesorServicio {

	public Profesor crearPorfesor(Profesor porfesor);
	
	public void eliminarPorfesor (long idProfesor);

	public Profesor obtenerProfesor(long idProfesor);
	
	public Profesor modificarProfesor(Profesor profesor);

	public List<Profesor> listarProfesores();
	
	public List<Profesor> listarPorfesoresQueNoImparten(Long idModulo);

	public Profesor anadirEmail(long idProfesor, Email email);

	public void eliminarEmail(long idProfesor, Email email);
}
