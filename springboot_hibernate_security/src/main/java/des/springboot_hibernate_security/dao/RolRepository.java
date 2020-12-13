package des.springboot_hibernate_security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import des.springboot_hibernate_security.entidades.Rol;

@Repository
@Component("rolRepository")
public interface RolRepository extends JpaRepository<Rol, Integer>{
	
}