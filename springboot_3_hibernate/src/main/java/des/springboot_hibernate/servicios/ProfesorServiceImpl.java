package des.springboot_hibernate.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import des.springboot_hibernate.dao.EmailDao;
import des.springboot_hibernate.dao.ModuloDao;
import des.springboot_hibernate.dao.ProfesorDao;
import des.springboot_hibernate.entidades.Email;
import des.springboot_hibernate.entidades.Modulo;
import des.springboot_hibernate.entidades.Profesor;

@Transactional
@Service
public class ProfesorServiceImpl implements ProfesorServicio {

	@Autowired
	private ProfesorDao profesorDao;

	@Autowired
	private EmailDao emailDao;

	@Autowired
	private ModuloDao moduloDao;

	@Override
	public Profesor crearPorfesor(Profesor profesor) {
		return profesorDao.crear(profesor);
	}

	@Override
	public void eliminarPorfesor(long idProfesor) {
		profesorDao.borrar(idProfesor);
	}

	@Override
	public List<Profesor> listarProfesores() {
		return profesorDao.listarProfesores();
	}

	@Override
	public Profesor obtenerProfesor(long idProfesor) {
		return profesorDao.buscar(idProfesor);
	}

	@Override
	public Profesor anadirEmail(long idProfesor, Email email) {
		return profesorDao.anadirEmail(idProfesor, email);
	}

	@Override
	public void eliminarEmail(long idProfesor, Email email) {
		Profesor p = profesorDao.buscar(idProfesor);
		p.eliminarEmails(email);
		emailDao.actualizar(email);

	}

	@Override
	public Profesor modificarProfesor(Profesor profesor) {
		profesorDao.actualizar(profesor);
		return null;
	}

	@Override
	public List<Profesor> listarPorfesoresQueNoImparten(Long idModulo) {
		Modulo modulo = moduloDao.buscar(idModulo);
		List<Profesor> profesores = new ArrayList<Profesor>(modulo.getProfesores());
		return profesorDao.listarPorfesoresQueNoImparten(profesores);
	}

}