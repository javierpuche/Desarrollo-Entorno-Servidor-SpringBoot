package des.springboot_eagerlazy.controladores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import des.springboot_eagerlazy.dao.EmailRepository;
import des.springboot_eagerlazy.dao.ImagenRepository;
import des.springboot_eagerlazy.dao.ModuloRepository;
import des.springboot_eagerlazy.dao.ProfesorRepository;
import des.springboot_eagerlazy.entidades.Modulo;
import des.springboot_eagerlazy.entidades.Profesor;
import des.springboot_eagerlazy.servicios.ProfesorServiceImpl;
import des.springboot_eagerlazy.servicios.ProfesorServicio;

public class ProfesorServicioImplTest {

	ProfesorRepository profesorRepository = Mockito.mock(ProfesorRepository.class);

	ImagenRepository imagenRepository = Mockito.mock(ImagenRepository.class);

	EmailRepository EmailRepository = Mockito.mock(EmailRepository.class);

	ModuloRepository moduloRepository = Mockito.mock(ModuloRepository.class);

	BCryptPasswordEncoder bCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder.class);

	@Autowired
	ProfesorServicio profesorServicio = new ProfesorServiceImpl(profesorRepository, EmailRepository, moduloRepository,
			bCryptPasswordEncoder);

	@BeforeEach
	void setUp() {

		Modulo m = new Modulo();
		m.setIdModulo(1L);
		m.setNombreModulo("Porgramación");
		

		Modulo m2 = new Modulo();
		m2.setIdModulo(1L);
		m2.setNombreModulo("Porgramación");
		
		
		
		Profesor p1 = new Profesor();
		p1.setIdProfesor(1L);
		p1.setNombreProfesor("Steve");
		p1.setApellidosProfesor("Jobs");
		p1.setUsername("steve.jobs");
		p1.setPassword("123456789");
		p1.anadirModulo(m);
		
		p1.setEmails(null);
		p1.setImagen(null);
		Optional<Profesor> mockProfesor = Optional.of(p1);
		
		Profesor p2 = new Profesor();
		p2.setIdProfesor(2L);
		p2.setNombreProfesor("Bill");
		p2.setApellidosProfesor("Gates");
		p2.setUsername("bill.gates");
		p2.setPassword("987654321");
		p2.setModulos(null);
		p2.setEmails(null);
		p2.setImagen(null);

		Profesor p3 = new Profesor();
		p3.setIdProfesor(3L);
		p3.setNombreProfesor("Roger");
		p3.setApellidosProfesor("Pressman");
		p3.setUsername("roger.pressman");
		p3.setPassword("987654321");
		p3.setModulos(null);
		p3.setEmails(null);
		p3.setImagen(null);
		
		List<Profesor> Lprofesores = new ArrayList<Profesor>();
		Lprofesores.add(p1);
		Lprofesores.add(p2);
		Lprofesores.add(p3);

		

		Optional<Modulo> mockModulo = Optional.of(m);
		Optional<Modulo> mockModulo2 = Optional.of(m2);
		Mockito.when(moduloRepository.findById(1L)).thenReturn(mockModulo);
		Mockito.when(moduloRepository.findById(2L)).thenReturn(mockModulo2);

		Mockito.when(profesorRepository.findById(1L)).thenReturn(mockProfesor);
		Mockito.when(profesorRepository.findAll()).thenReturn(Lprofesores);


		List<Long> lista_id = new ArrayList<Long>();
		
		
		List<Profesor> LprofesoresNoImparten = new ArrayList<Profesor>();
		LprofesoresNoImparten.add(p2);
		LprofesoresNoImparten.add(p3);
		Mockito.when(profesorRepository.
				BuscarProfesoresQueNoImparten(Arrays.asList(1L))).thenReturn(LprofesoresNoImparten);
		
		
	}

	@Test
	void obtenerProfesorTest() {
		Profesor t1 = profesorServicio.obtenerProfesor(1L);

		Assertions.assertEquals(1L, t1.getIdProfesor());
		Assertions.assertEquals("Steve", t1.getNombreProfesor());

	}

	@Test
	void listarProfesoresTest() {

		Iterable<Profesor> iterResultado = profesorServicio.listarProfesores();
		ArrayList<Profesor> listaTest = new ArrayList<Profesor>();
		iterResultado.forEach(listaTest::add);

		Assertions.assertEquals(1L, listaTest.get(0).getIdProfesor());
		Assertions.assertEquals("Steve", listaTest.get(0).getNombreProfesor());
		Assertions.assertEquals("Jobs", listaTest.get(0).getApellidosProfesor());

		Assertions.assertEquals(2L, listaTest.get(1).getIdProfesor());
		Assertions.assertEquals("Bill", listaTest.get(1).getNombreProfesor());
		Assertions.assertEquals("Gates", listaTest.get(1).getApellidosProfesor());

	}
	
	
	@Test
	void listarPorfesoresQueNoImpartenTest() {

		List<Profesor> listaTest = profesorServicio.listarPorfesoresQueNoImparten(1L);

		Assertions.assertEquals(2, listaTest.size());
		Assertions.assertEquals(2L, listaTest.get(0).getIdProfesor());
		Assertions.assertEquals("Bill", listaTest.get(0).getNombreProfesor());
		Assertions.assertEquals("Gates", listaTest.get(0).getApellidosProfesor());

		Assertions.assertEquals(3L, listaTest.get(1).getIdProfesor());
		Assertions.assertEquals("Roger", listaTest.get(1).getNombreProfesor());
		Assertions.assertEquals("Pressman", listaTest.get(1).getApellidosProfesor());
		
		//Probamos un modulo que no existe por lo tanto devvuelve todos los porfesores.
		List<Profesor> listaTest2 = profesorServicio.listarPorfesoresQueNoImparten(2L);
		Assertions.assertEquals(3, listaTest2.size());
		

	}
	
}
