package des.springboot_eagerlazy.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import des.springboot_eagerlazy.entidades.Imagen;

@Repository
public interface ImagenRepository extends CrudRepository<Imagen, Long>{

}
