package des.springboot_hibernate.dao;

import org.springframework.data.repository.CrudRepository;

import des.springboot_hibernate.entidades.Imagen;

public interface ImagenDao extends CrudRepository<Imagen, Long> {

}