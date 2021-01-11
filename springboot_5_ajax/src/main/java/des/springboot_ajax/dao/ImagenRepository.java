package des.springboot_ajax.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import des.springboot_ajax.entidades.Imagen;

@Repository
public interface ImagenRepository extends CrudRepository<Imagen, Long>{

}
