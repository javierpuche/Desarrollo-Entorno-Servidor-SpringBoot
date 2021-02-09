package des.springboot_eagerlazy.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import des.springboot_eagerlazy.dao.EmailRepository;
import des.springboot_eagerlazy.dao.ModuloRepository;
import des.springboot_eagerlazy.dao.ProfesorRepository;
import des.springboot_eagerlazy.entidades.Email;
import des.springboot_eagerlazy.entidades.Modulo;
import des.springboot_eagerlazy.entidades.Profesor;

@Transactional
@Service
public class ProfesorServiceImpl implements ProfesorServicio {

	
	
	public ProfesorServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProfesorServiceImpl(ProfesorRepository profesorRepository, EmailRepository emailRepository,
			ModuloRepository moduloRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.profesorRepository = profesorRepository;
		this.emailRepository = emailRepository;
		this.moduloRepository = moduloRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Autowired
	private ProfesorRepository profesorRepository;

	@Autowired
	private EmailRepository emailRepository;

	@Autowired
	private ModuloRepository moduloRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Profesor crearPorfesor(Profesor profesor) {
		profesor.setPassword(bCryptPasswordEncoder.encode(profesor.getPassword()));

		return profesorRepository.save(profesor);
	}

	@Override
	public void eliminarPorfesor(long idProfesor) {
		profesorRepository.deleteById(idProfesor);
	}

	@Override
	public Iterable<Profesor> listarProfesores() {
		return profesorRepository.findAll();
	}

	@Override
	public Profesor obtenerProfesor(long idProfesor) {
		return profesorRepository.findById(idProfesor).orElse(null);
	}

	@Override
	public Profesor anadirEmail(long idProfesor, Email email) {

		Profesor p = profesorRepository.findById(idProfesor).orElse(null);
		if (p == null)
			return null;

		p.anadirEmail(email);
		return profesorRepository.save(p);
	}

	@Override
	public void eliminarEmail(long idProfesor, Long idEmail) {
		Profesor p = profesorRepository.findById(idProfesor).orElse(null);
		Email e = emailRepository.findById(idEmail).orElse(null);
		if (p != null && e != null) {
			p.eliminarEmail(e);
			profesorRepository.save(p);

			emailRepository.deleteById(idEmail);
		}
	}

	@Override
	public Profesor modificarProfesor(Profesor profesor) {

		return profesorRepository.save(profesor);
	}

	@Override
	public List<Profesor> listarPorfesoresQueNoImparten(Long idModulo) {
		
		Modulo modulo = moduloRepository.findById(idModulo).orElse(null);
		List<Profesor> profesores = new ArrayList<Profesor>(modulo.getProfesores());

		if (profesores.isEmpty()) {
			Iterable<Profesor> iterable = profesorRepository.findAll();
			List<Profesor> result = new ArrayList<Profesor>();
			iterable.forEach(result::add);
			return result;

		} else {
			List<Long> lista_id = new ArrayList<Long>();
			profesores.forEach(p -> lista_id.add(p.getIdProfesor()));

			return profesorRepository.BuscarProfesoresQueNoImparten(lista_id);
		}
	}

	@Override
	public Profesor buscarPorNombreUsuario(String username) {
		return profesorRepository.findByUsername(username);
	}

}