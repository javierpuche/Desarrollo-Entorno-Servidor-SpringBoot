package des.springboot_hibernate_security.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import des.springboot_hibernate_security.dao.ModuloDao;
import des.springboot_hibernate_security.dao.ProfesorDao;
import des.springboot_hibernate_security.entidades.Modulo;
import des.springboot_hibernate_security.entidades.Profesor;

@Transactional
@Service
public class ModuloServicioImpl implements ModuloServicio {

	@Autowired
	ModuloDao moduloDao;

	@Autowired
	ProfesorDao profesorDao;

	@Override
	public List<Modulo> listarModulos() {
		return moduloDao.listarModulos();
	}

	@Override
	public List<Modulo> listarModulosPorNombre(String nombreModulo) {
		return moduloDao.listarModulosPorNombre(nombreModulo);
	}

	@Override
	public Modulo obtenerModulo(long idModulo) {
		return moduloDao.buscar(idModulo);
	}

	@Override
	public Modulo matricularProfesor(long idModulo, long idProfesor) {

		Modulo m = moduloDao.buscar(idModulo);

		for (Profesor p : m.getProfesores()) {
			if (p.getIdProfesor() == idProfesor) {
				return null;
			}
		}
		Profesor profesor = profesorDao.buscar(idProfesor);
		Modulo modulo = moduloDao.agregarProfesor(idModulo, profesor);

		return modulo;
	}

	@Override
	public Modulo desmatricularProfesor(long idModulo, long idProfesor) {

		Profesor profesor = profesorDao.buscar(idProfesor);
		Modulo m = moduloDao.buscar(idModulo);

		profesorDao.desmatricularProfesor(m, profesor);

		return null;
	}

	@Override
	public Modulo crearModulo(Modulo modulo) {
		return moduloDao.crear(modulo);
	}

	@Override
	public Modulo matricularProfesores(long idModulo, List<Long> idProfesores) {
		Modulo m = moduloDao.buscar(idModulo);

		for (Profesor p : m.getProfesores()) {
			if (idProfesores.contains(p.getIdProfesor())) {
				return null;
			}
		}
		for (Long idProfesor : idProfesores) {
			Profesor profesor = profesorDao.buscar(idProfesor);
			m = moduloDao.agregarProfesor(idModulo, profesor);
		}

		return m;
	}

	@Override
	public void eliminarModulo(long idModulo) {

		Modulo modulo = moduloDao.buscar(idModulo);
		if (!modulo.getProfesores().isEmpty()) {
			List<Profesor> lProfesores = new ArrayList<Profesor>(modulo.getProfesores());
			for (Profesor p : lProfesores) {
				profesorDao.desmatricularProfesor(modulo, p);
			}
		}
		modulo.setProfesores(null);

		moduloDao.borrar(modulo.getIdModulo());
	}

}