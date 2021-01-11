package des.springboot_hibernate_security.servicios;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import des.springboot_hibernate_security.dao.ProfesorDao;
import des.springboot_hibernate_security.entidades.Profesor;
import des.springboot_hibernate_security.entidades.Rol;

@Transactional
@Service
public class CustomUserDetailsService implements  UserDetailsService {

	@Autowired
	private ProfesorDao profesorDao;
	
	@Override
	@Transactional()
	public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {

		Profesor profesor = profesorDao.buscarPorNombre(nombre);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Rol rol : profesor.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(rol.getNombreRol()));
		}

		return new org.springframework.security.core.userdetails.User(profesor.getUsername(), profesor.getPassword(),
				grantedAuthorities);
	}
}