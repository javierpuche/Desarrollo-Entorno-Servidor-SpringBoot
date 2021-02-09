package des.springboot_hibernate_security.dao;

import java.util.List;

import des.springboot_hibernate_security.entidades.Email;
import des.springboot_hibernate_security.entidades.Modulo;
import des.springboot_hibernate_security.entidades.Profesor;

public interface ProfesorDao  extends DaoGenerico<Profesor>{

	public Profesor buscarPorEmail (String email);
	
	public Profesor buscarPorNombre(String username);
	
	public Profesor anadirEmail(long idProfesor,Email email);

	public List<Profesor> listarProfesores();
	
	public List<Profesor>listarPorfesoresQueNoImparten(List<Profesor> lprofesores);
	
	public Modulo desmatricularProfesor(Modulo modulo, Profesor profesor);
}
