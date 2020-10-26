package des.springboot_hibernate.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import des.springboot_hibernate.entidades.Imagen;
import des.springboot_hibernate.entidades.Profesor;
import des.springboot_hibernate.servicios.ImagenServicio;
import des.springboot_hibernate.servicios.ProfesorServicio;

@Controller
@RequestMapping(value = "/imagenes")
public class ImagenController {

    @Autowired
    private ImagenServicio imgServicio;

	@Autowired
	ProfesorServicio profesorService;
    
    
    @GetMapping("/cargar/{idProfesor}")
    public ModelAndView actualizarFotoPerfil( HttpServletRequest request,@PathVariable("idProfesor") long idProfesor) {
        
		ModelAndView mav = new ModelAndView();

		Profesor profesor = profesorService.obtenerProfesor(idProfesor);

		mav.addObject("profesor", profesor);
		mav.setViewName("/imagen/imagen_subir");
		return mav;
        
        
    }

    @PostMapping("/cargar/{idProfesor}")
    public String fileUpload( @RequestParam("file") MultipartFile file, HttpServletRequest request,@PathVariable("idProfesor") long idProfesor) {
        
    	imgServicio.actualizaFotoUsuario(idProfesor,file);
    	
    	try {
            byte[] image = file.getBytes();
            Imagen img = new Imagen("foto", image);
            int saveImage = imgServicio.saveImage(img);
            if (saveImage == 1) {
                return "redirect:/index";
            } else {
                return "redirect:/index";
            }
        } catch (Exception e) {
        	e.printStackTrace();
            return "redirect:/imagenes/index";
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity getImageAsResponseEntity(@PathVariable String id) {
    	
    	try {
            Imagen imagesObj = imgServicio.getImages(Long.parseLong(id));
            byte[] media = imagesObj.getImagen();
            HttpHeaders headers = new HttpHeaders();
            headers.setCacheControl(CacheControl.noCache().getHeaderValue());
            
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
            return responseEntity;
            
        } catch (Exception e) {
        	return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    	
    }
    
    
//  @GetMapping("/imagen/{id}")
//  public String getDbDetils(@PathVariable String id, Model model) {
//      try {
//          Imagen imagesObj = imgService.getImages(Long.parseLong(id));
//          model.addAttribute("id", imagesObj.getId());
//          model.addAttribute("name", imagesObj.getNombre());
//          byte[] encode = java.util.Base64.getEncoder().encode(imagesObj.getImagen());
//          model.addAttribute("image", new String(encode, "UTF-8"));
//          return "imagedetails";
//      } catch (Exception e) {
//          model.addAttribute("message", "Error in getting image");
//          return "redirect:/";
//      }
//  }
	
}
