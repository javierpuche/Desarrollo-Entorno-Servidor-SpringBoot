package des.springboot_hibernate.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import des.springboot_hibernate.dao.ImagenDao;
import des.springboot_hibernate.dao.ProfesorDao;
import des.springboot_hibernate.entidades.Imagen;
import des.springboot_hibernate.entidades.Profesor;

@Transactional
@Service
public class ImagenServicio {

	@Autowired
	private ImagenDao imgDao;

	@Autowired
	private ProfesorDao profesorDao;

	public int saveImage(Imagen img) {
		try {
			imgDao.save(img);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public Imagen getImages(Long id) {
		Optional findById = imgDao.findById(id);
		if (findById.isPresent()) {
			Imagen getImageDetails = (Imagen) findById.get();
			return getImageDetails;
		} else {
			return null;
		}
	}

	public Boolean actualizaFotoUsuario(long idProfesor, MultipartFile file) {

		Profesor p = profesorDao.buscar(idProfesor);

		if (p == null)
			return false;
		try {
			byte[] image = file.getBytes();

			if (p.getImagen() == null) {
				Imagen img = new Imagen("foto", image);
				p.setImagen(img);
				img.setProfesor(p);
				imgDao.save(img);
			} else {
				Imagen i = p.getImagen();
				i.setImagen(image);
				imgDao.save(i);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}