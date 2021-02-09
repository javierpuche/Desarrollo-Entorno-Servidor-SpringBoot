package des.springboot_hibernate_security.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import des.springboot_hibernate_security.entidades.Modulo;
import des.springboot_hibernate_security.entidades.Profesor;
import des.springboot_hibernate_security.servicios.ModuloServicio;
import des.springboot_hibernate_security.servicios.ProfesorServicio;

@Controller
@RequestMapping(value = "/modulo")
public class ModuloControlador {

	@Autowired
	ModuloServicio moduloServicio;

	@Autowired
	ProfesorServicio profesorServicio;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ModelAndView perfilModulo(@PathVariable("id") long idModulo) {

		ModelAndView mav = new ModelAndView();

		Modulo modulos = moduloServicio.obtenerModulo(idModulo);
		List<Profesor> lProfesores = profesorServicio.listarPorfesoresQueNoImparten(idModulo);

		mav.addObject("profesor", new Profesor());
		mav.addObject("profesores", lProfesores);
		mav.addObject("modulo", modulos);
		mav.setViewName("/modulo/perfil");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "borrar/{id}")
	public String borrarModulo(@PathVariable("id") long idModulo) {

		moduloServicio.eliminarModulo(idModulo);

		return "redirect:/modulo/lista";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/lista")
	public ModelAndView listarModulos() {

		ModelAndView mav = new ModelAndView();

		List<Modulo> modulos = moduloServicio.listarModulos();

		mav.addObject("modulos", modulos);
		mav.addObject("modulo_nuevo", new Modulo());
		mav.setViewName("/modulo/lista");
		return mav;
	}

	@PostMapping("/crear")
	public String actualizarPerfilProfesor(Model model, @ModelAttribute("modulo_nuevo") Modulo modulo) {

		modulo.setProfesores(null);
		moduloServicio.crearModulo(modulo);

		return "redirect:/modulo/lista";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/desmatricularProfesor/{idProfesor}/{idModulo}")
	public String desmatricularProfesor(@PathVariable("idModulo") long idModulo,
			@PathVariable("idProfesor") long idProfesor) {

		Modulo modulo = moduloServicio.desmatricularProfesor(idModulo, idProfesor);

		return "redirect:/modulo/" + idModulo;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/matricular/{idModulo}")
	public String matricularPorfesore(@PathVariable("idModulo") long idModulo, HttpServletRequest request,
			@RequestParam("profesoresseleccionados") List<Long> profesores) {

		ModelAndView mav = new ModelAndView();
		moduloServicio.matricularProfesores(idModulo, profesores);

		return "redirect:/modulo/" + idModulo;
	}

}
