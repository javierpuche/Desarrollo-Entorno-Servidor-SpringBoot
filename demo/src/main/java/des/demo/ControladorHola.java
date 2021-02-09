package des.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorHola {

	@RequestMapping("/hola")
	public String hola(Model modelo) {

		modelo.addAttribute("mensaje", "hola desde thymeleaf");
		return "hola";
	}

}
