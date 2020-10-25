package des.springboot_hibernate.servicios;

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
public class ProfesorServiceImpl implements ProfesorService {

	@Autowired
	private ProfesorDao profesorDao;

	@Autowired
	private EmailDao emailDao;

	@Autowired
	private ModuloDao moduloDao;

	@Override
	public Profesor crearPorfesor(Profesor profesor) {

		return profesorDao.create(profesor);
	}

	@Override
	public void eliminarPorfesor(long idProfesor) {

		profesorDao.borrar(idProfesor);
	}

	@Override
	public List<Modulo> listarModulosNombre(String nombreModulo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Modulo> listarModulosProfesor(long idProfesor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Profesor> listarProfesores() {
		return profesorDao.listarPorfesores();
	}

	@Override
	public Profesor obtenerProfesor(long idProfesor) {
		return profesorDao.buscar(idProfesor);
	}

	@Override
	public List<Modulo> listarModulos() {
		return moduloDao.listarModulos();
	}

	@Override
	public Profesor anadirEmail(long idProfesor, Email email) {

		return profesorDao.anadirEmail(idProfesor, email);
	}

	@Override
	public Email crearEmail(Email email) {

		return emailDao.crear(email);
	}

	@Override
	public void eliminarEmail(long idProfesor, Email email) {

		Profesor p = profesorDao.buscar(idProfesor);
		p.removeEmails(email);
		emailDao.actualizar(email);

	}

	@Override
	public Profesor findByUsername(String username) {

		return profesorDao.findByUsername(username);
	}

	@Override
	public Profesor modificarProfesor(Profesor profesor) {

		profesorDao.actualizar(profesor);

		return null;
	}

}