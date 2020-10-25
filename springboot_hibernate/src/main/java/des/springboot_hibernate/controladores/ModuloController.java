package des.springboot_hibernate.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import des.springboot_hibernate.entidades.Modulo;
import des.springboot_hibernate.servicios.ModuloServicio;

@Controller
@RequestMapping(value = "/modulo")
public class ModuloController {

	@Autowired
	ModuloServicio moduloServicio;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ModelAndView perfilProfesor(@PathVariable("id") long idModulo) {

		ModelAndView mav = new ModelAndView();

		Modulo modulos = moduloServicio.obtenerModulo(idModulo);

		mav.addObject("modulo", modulos);
		mav.setViewName("modulo_perfil");
		return mav;
	}

//@RequestMapping(method = RequestMethod.POST, value = "/matricularprofesor/{id}")
//public @ResponseBody ResponseEntity matricularProfesor(@PathVariable("id") long idModulo,@RequestBody ProfesorDto profesorDto) {
//
//	Modulo modulo =moduloServicio.agregarProfesor(idModulo, profesorDto.getIdProfesor());
//		
//	if (modulo ==null) {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
//	
//	return new ResponseEntity(HttpStatus.OK);
//}
	
@RequestMapping(method = RequestMethod.DELETE, value = "/desmatricularProfesor/{idModulo}/{idProfesor}")
public @ResponseBody ResponseEntity desmatricularProfesor(@PathVariable("idModulo") long idModulo,
		@PathVariable("idProfesor") long idProfesor) {

	Modulo modulo = moduloServicio.eliminarProfesor(idModulo, idProfesor);

	if (modulo == null)
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	return new ResponseEntity(HttpStatus.OK);
}

}
