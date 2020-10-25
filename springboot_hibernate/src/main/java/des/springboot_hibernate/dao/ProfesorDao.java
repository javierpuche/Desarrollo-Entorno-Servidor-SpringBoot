package des.springboot_hibernate.dao;

import java.util.List;

import des.springboot_hibernate.entidades.Email;
import des.springboot_hibernate.entidades.Profesor;

public interface ProfesorDao  extends DaoGenerico<Profesor>{

	public Profesor create (Profesor profesor);
	
	public Profesor buscarPorEmail (String email);
	
	public List<Profesor> listarPorfesores();
	
	public Profesor anadirEmail(long idProfesor,Email email);
	
	public Profesor findByUsername(String username);
	
}
