package des.springboot_hibernate_security.servicios;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import des.springboot_hibernate_security.dao.ImagenDao;
import des.springboot_hibernate_security.dao.ProfesorDao;
import des.springboot_hibernate_security.entidades.Imagen;
import des.springboot_hibernate_security.entidades.Profesor;

@Transactional
@Service
public class ImagenServicioImpl implements ImagenServicio {

	@Autowired
	private ImagenDao imgDao;

	@Autowired
	private ProfesorDao profesorDao;

	public int guardarImagen(Imagen img) {
		try {
			imgDao.crear(img);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public Imagen obtenerImagen(Long id) {
		Imagen findById = imgDao.buscar(id);
		if (findById != null) {
			Imagen getImageDetails = findById;
			return findById;
		} else {
			return null;
		}
	}

	public Boolean actualizarImagen(long idProfesor, MultipartFile file) {

		Profesor p = profesorDao.buscar(idProfesor);

		Imagen img = imgDao.actualizarIamgen(p, file);

		if (img != null) {
			return true;
		} else {
			return false;
		}

	}

}