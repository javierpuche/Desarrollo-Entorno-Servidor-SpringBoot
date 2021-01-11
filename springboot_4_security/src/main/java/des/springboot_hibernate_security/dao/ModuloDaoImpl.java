package des.springboot_hibernate_security.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import des.springboot_hibernate_security.entidades.Modulo;
import des.springboot_hibernate_security.entidades.Profesor;

@Repository
@Component("ModuloDao")
public class ModuloDaoImpl extends DaoGenericoImpl<Modulo> implements ModuloDao {

	@Override
	public List<Modulo> listarModulosPorNombre(String nombreModulo) {
		Query query = this.em.createQuery("FROM Modulo u where u.nombreModulo= :nombre");
		query.setParameter("nombre", nombreModulo);
		List<Modulo> lModulo = query.getResultList();

		if (lModulo != null) {
			return lModulo;
		}
		return null;
	}

	@Override
	public List<Modulo> listarModulos() {
		Query query = this.em.createQuery("FROM Modulo");
		List<Modulo> lModulo = query.getResultList();

		if (lModulo != null) {
			return lModulo;
		}
		return null;
	}

	@Override
	public Modulo agregarProfesor(long idModulo, Profesor profesor) {

		Modulo modulo = this.buscar(idModulo);
		modulo.addProfesor(profesor);
		this.em.merge(modulo);
		this.em.refresh(modulo);

		return modulo;
	}

	@Override
	public Modulo eliminarProfesor(long idModulo, Profesor profesor) {
		Modulo modulo = this.buscar(idModulo);
		return modulo;
	}

}