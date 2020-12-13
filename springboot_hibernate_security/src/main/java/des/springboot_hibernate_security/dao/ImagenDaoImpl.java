package des.springboot_hibernate_security.dao;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import des.springboot_hibernate_security.entidades.Imagen;
import des.springboot_hibernate_security.entidades.Profesor;

@Repository
@Component("ImagenDao")
public class ImagenDaoImpl extends DaoGenericoImpl<Imagen> implements ImagenDao {

	@Override
	public Imagen actualizarIamgen(Profesor profesor, MultipartFile file) {

		try {
			byte[] image = file.getBytes();

			if (!profesor.getImagen().isEmpty()) {

				Set<Imagen> limg = profesor.getImagen();

				for (Imagen a : limg) {
					a.setImagen(image);
					this.em.merge(a);
					this.em.refresh(a);
					return a;
				}
				return null;
				
			} else {
				Imagen img = new Imagen("foto", image);
				profesor.addImagen(img);
				this.em.persist(img);
				this.em.merge(profesor);
				return img;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
