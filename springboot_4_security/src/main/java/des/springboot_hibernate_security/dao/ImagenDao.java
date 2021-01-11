package des.springboot_hibernate_security.dao;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import des.springboot_hibernate_security.entidades.Imagen;
import des.springboot_hibernate_security.entidades.Profesor;

@Repository
public interface ImagenDao extends DaoGenerico<Imagen>{

	public Imagen actualizarIamgen(Profesor profesor, MultipartFile file);
	
	
}