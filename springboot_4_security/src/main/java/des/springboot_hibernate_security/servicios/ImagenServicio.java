package des.springboot_hibernate_security.servicios;

import org.springframework.web.multipart.MultipartFile;

import des.springboot_hibernate_security.entidades.Imagen;

public interface ImagenServicio {

	public int guardarImagen(Imagen img);
	
	public Imagen obtenerImagen(Long id);
	
	public Boolean actualizarImagen(long idProfesor, MultipartFile file);
	
}
