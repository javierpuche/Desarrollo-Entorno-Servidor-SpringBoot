package des.springboot_hibernate_security.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import des.springboot_hibernate_security.entidades.Email;
import des.springboot_hibernate_security.entidades.Modulo;
import des.springboot_hibernate_security.entidades.Profesor;

@Repository
@Component("ProfesorDao")
public class ProfesorDaoImpl extends DaoGenericoImpl<Profesor> implements ProfesorDao {

	@Override
	public Profesor buscarPorEmail(String email) {
		Query query = this.em.createQuery("select u FROM Profesor u where u.email= :email");
		query.setParameter("email", email);
		Profesor profesor = (Profesor) query.getSingleResult();

		if (profesor != null) {
			return profesor;
		}
		return null;
	}

	@Override
	public List<Profesor> listarProfesores() {
		Query query = this.em.createQuery("FROM Profesor");
		List<Profesor> lProfesor = query.getResultList();

		if (lProfesor != null) {
			return lProfesor;
		}
		return null;
	}

	@Override
	public Profesor anadirEmail(long idProfesor, Email email) {

		Profesor profesor = this.buscar(idProfesor);
		profesor.anadirEmails(email);

		return profesor;
	}

	@Override
	public Profesor buscarPorNombre(String username) {
		Query query = this.em.createQuery("FROM Profesor u where u.username= :username");
		query.setParameter("username", username);
		Profesor profesor = (Profesor) query.getSingleResult();

		if (profesor != null) {
			return profesor;
		}
		return null;
	}

	@Override
	public List<Profesor> listarPorfesoresQueNoImparten(List<Profesor> lprofesores) {
		Query query = this.em.createQuery("FROM Profesor u where u.idProfesor NOT IN :idProfesores");
		
		List<Long> ids= new ArrayList<Long>(); 
		for (Profesor profesor: lprofesores) {
			ids.add(profesor.getIdProfesor());
		}
		query.setParameter("idProfesores", ids);
		List<Profesor> lProfesor = query.getResultList();
		return lProfesor;
	}

	@Override
	public Modulo desmatricularProfesor(Modulo modulo, Profesor profesor) {
		
		profesor.eliminarModulo(modulo);
		this.actualizar(profesor);
		
		return null;
	}

}