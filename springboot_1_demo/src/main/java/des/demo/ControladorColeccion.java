package des.demo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ControladorColeccion {

	@RequestMapping(value="/coleccion", method=RequestMethod.GET)
	public String coleccion_get(Model modelo) {

		Date cal = new Date(2020, 05, 05, 20, 20, 20);
		Date calb = new Date(2019, 04, 05, 20, 20, 20);
		Date calc = new Date(2028, 03, 05, 20, 20, 20);
		
		List<Date> lcalendario = new LinkedList<Date>();
		lcalendario.add(cal);
		lcalendario.add(calb);
		lcalendario.add(calc);
		
		modelo.addAttribute("fechas", lcalendario);
		return "coleccion";
	}
	
}
