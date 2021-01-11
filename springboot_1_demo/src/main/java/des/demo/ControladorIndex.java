package des.demo;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class ControladorIndex {

	
	@RequestMapping("/")
	public String hola(Model modelo) {

		return "index";
	}

	
}
