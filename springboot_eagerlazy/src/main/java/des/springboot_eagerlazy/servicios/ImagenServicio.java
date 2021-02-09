package des.springboot_eagerlazy.servicios;

import org.springframework.web.multipart.MultipartFile;

import des.springboot_eagerlazy.entidades.Imagen;

public interface ImagenServicio {

	public int guardarImagen(Imagen img);
	
	public Imagen obtenerImagen(Long id);
	
	public Boolean actualizarImagen(long idProfesor, MultipartFile file);
	
}
