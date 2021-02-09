package des.springboot_eagerlazy.servicios;

import java.util.List;

import des.springboot_eagerlazy.entidades.Email;
import des.springboot_eagerlazy.entidades.Modulo;
import des.springboot_eagerlazy.entidades.Profesor;

public interface ProfesorServicio {

	public Profesor crearPorfesor(Profesor porfesor);
	
	public void eliminarPorfesor (long idProfesor);

	public Profesor obtenerProfesor(long idProfesor);
	
	public Profesor modificarProfesor(Profesor profesor);

	public Iterable<Profesor> listarProfesores();
	
	public List<Profesor> listarPorfesoresQueNoImparten(Long idModulo);

	public Profesor anadirEmail(long idProfesor, Email email);

	public void eliminarEmail(long idProfesor, Long idEmail);
	
	public Profesor buscarPorNombreUsuario(String username);

}
