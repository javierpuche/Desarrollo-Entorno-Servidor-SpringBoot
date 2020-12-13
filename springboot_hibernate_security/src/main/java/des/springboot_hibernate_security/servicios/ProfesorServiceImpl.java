package des.springboot_hibernate_security.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import des.springboot_hibernate_security.dao.EmailDao;
import des.springboot_hibernate_security.dao.ModuloDao;
import des.springboot_hibernate_security.dao.ProfesorDao;
import des.springboot_hibernate_security.entidades.Email;
import des.springboot_hibernate_security.entidades.Modulo;
import des.springboot_hibernate_security.entidades.Profesor;

@Transactional
@Service
public class ProfesorServiceImpl implements ProfesorServicio {

	@Autowired
	private ProfesorDao profesorDao;

	@Autowired
	private EmailDao emailDao;

	@Autowired
	private ModuloDao moduloDao;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Profesor crearPorfesor(Profesor profesor) {
		profesor.setPassword(bCryptPasswordEncoder.encode(profesor.getPassword()));

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
		
		if (profesores.isEmpty()) {
			return profesorDao.listarProfesores();
			
		}else {
			return profesorDao.listarPorfesoresQueNoImparten(profesores);	
		}
		
		
	}

	@Override
	public Profesor buscarPorNombreUsuario(String username) {
		return profesorDao.buscarPorNombre(username);
	}

}