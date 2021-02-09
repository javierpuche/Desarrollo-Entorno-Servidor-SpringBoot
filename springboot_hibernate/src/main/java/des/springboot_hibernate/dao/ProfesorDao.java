package des.springboot_hibernate.dao;

import java.util.List;

import des.springboot_hibernate.entidades.Email;
import des.springboot_hibernate.entidades.Modulo;
import des.springboot_hibernate.entidades.Profesor;

public interface ProfesorDao  extends DaoGenerico<Profesor>{

	public Profesor buscarPorEmail (String email);
	
	public Profesor buscarPorNombre(String username);
	
	public Profesor anadirEmail(long idProfesor,Email email);

	public List<Profesor> listarProfesores();
	
	public List<Profesor>listarPorfesoresQueNoImparten(List<Profesor> lprofesores);
	
	public Modulo desmatricularProfesor(Modulo modulo, Profesor profesor);
}
