package des.springboot_hibernate.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import des.springboot_hibernate.dao.ModuloDao;
import des.springboot_hibernate.dao.ProfesorDao;
import des.springboot_hibernate.entidades.Modulo;
import des.springboot_hibernate.entidades.Profesor;

@Transactional
@Service
public class ModuloServicioImpl implements ModuloServicio {

	@Autowired
	ModuloDao moduloDao;

	@Autowired
	ProfesorDao ProfesorDao;

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
	public Modulo agregarProfesor(long idModulo, long idProfesor) {

		Modulo m = moduloDao.buscar(idModulo);

		for (Profesor p : m.getProfesores()) {
			if (p.getIdProfesor() == idProfesor) {
				return null;
			}
		}
		Profesor profesor = ProfesorDao.buscar(idProfesor);
		Modulo modulo = moduloDao.agregarProfesor(idModulo, profesor);

		return modulo;
	}

	@Override
	public Modulo desmatricularProfesor(long idModulo, long idProfesor) {

		Profesor profesor = ProfesorDao.buscar(idProfesor);
		Modulo m = moduloDao.buscar(idModulo);

		ProfesorDao.desmatricularProfesor(m, profesor);
		
		return null;
	}

	@Override
	public Modulo crearModulo(Modulo modulo) {
		return moduloDao.crear(modulo);
	}

	@Override
	public Modulo agregarProfesores(long idModulo, List<Long> idProfesores) {
		Modulo m = moduloDao.buscar(idModulo);

		for (Profesor p : m.getProfesores()) {
			if (idProfesores.contains(p.getIdProfesor())) {
				return null;
			}
		}
		for (Long idProfesor : idProfesores) {
			Profesor profesor = ProfesorDao.buscar(idProfesor);
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
				ProfesorDao.desmatricularProfesor(modulo, p);
			}
		}
		modulo.setProfesores(null);

		moduloDao.borrar(modulo.getIdModulo());
	}

}