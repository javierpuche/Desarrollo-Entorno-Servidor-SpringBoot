package des.springboot_jdbc.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import des.springboot_jdbc.dao.BookDao;
import des.springboot_jdbc.model.Book;

@Controller
public class BookController {

	@Autowired
	private BookDao bookdao;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Model modelo) {

		List<Book> libros = bookdao.findAll();
		
		modelo.addAttribute("libros",libros);
		
		return "libros";
	}
	
	@RequestMapping(value="/nuevolibro", method=RequestMethod.POST)
	public String crearlibro(Model modelo,
			@RequestParam String nombre,
			@RequestParam BigDecimal precio) {

		Book book = new Book(nombre,precio);
		bookdao.save(book);
		
		
		return "redirect:/";
	}
	
	
	@RequestMapping(value="/borrarlibro/{id}", method=RequestMethod.GET)
	public String borrarlibro(@PathVariable("id") long idLibro) {

		bookdao.deleteById(idLibro);
		
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/editarlibro/{id}", method=RequestMethod.GET)
	public String editarlibro(@PathVariable("id") long idLibro, Model modelo) {

		Optional <Book> book = bookdao.findById(idLibro);
		
		if(book == null) {
			return "redirect:/";

		}
		Book b1= book.get();
		
		modelo.addAttribute("book", b1);
		return "/editarlibro";
	}
	
	
	@RequestMapping(value="/editarlibro/{id}", method=RequestMethod.POST)
	public String modificarlibro(@PathVariable("id") long idLibro,
//			@RequestParam Long id,
			@RequestParam String nombre,
			@RequestParam BigDecimal precio) {
		
		
		Book book = new Book(idLibro,nombre,precio);
		bookdao.update(book);

		return "redirect:/";
	}
	
}
