package edu.mx.utez.SCCT.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mx.utez.SCCT.model.Transaccion;
import edu.mx.utez.SCCT.service.TransaccionServiceImpl;


@Controller
@RequestMapping(value = "/transacciones")
public class TransaccionController {


	@Autowired
	private TransaccionServiceImpl transaccionService;
	

	@GetMapping( "/listar" )
	public String listarServicios( Model model,
			RedirectAttributes redirectAtributtes) {
		model.addAttribute("listaTransacciones", transaccionService.listar());

		return "listarTransacciones";
	}
	
	@GetMapping("/crear")
	public String crearTransaccion(Transaccion transaccion, Model model) {
	
		return "formTransaccion";
	}
	
	@GetMapping("/crearPago")
	public String crearPago(Transaccion transaccion, Model model) {

		Transaccion transaccion1 = new Transaccion();
		transaccion1.setEstado(true);
		transaccion1.setIdCita(1);
		transaccion1.setMonto(100.00);
		transaccionService.guardar(transaccion1);
		return "wizardPago";
	}
	
	@PostMapping("/guardar")
	public String guardarTransaccion(@Valid @ModelAttribute("transaccion") Transaccion transaccion, BindingResult result,  Model model, RedirectAttributes redirectAttributes) {
	
	boolean respuesta = transaccionService.guardar(transaccion);
	
	if(result.hasErrors()){

		for(ObjectError error: result.getAllErrors()){
		System.out.println("Error : " + error.getDefaultMessage());

		}
		return "formTransaccion";
		}

	
	if(respuesta) {
		redirectAttributes.addFlashAttribute("msg_success", "¡Registro de Documento exitoso!");
		return "redirect:/transacciones/listar";
	}else {
		redirectAttributes.addFlashAttribute("msg_error", "¡Registro de Documento fallido!");
		return "redirect:/transacciones/crear";
	}
	
	
	}

	

	@GetMapping("/eliminar/{id}")
	public String eliminarTransaccion(@PathVariable long id, RedirectAttributes redirectAttributes) {
		
		boolean respuesta = transaccionService.eliminar(id);
		
		if(respuesta) {
			redirectAttributes.addFlashAttribute("msg_success", "¡Eliminacion exitosa!");
			return "redirect:/transacciones/listar";
		}else {
			redirectAttributes.addFlashAttribute("msg_error", "¡Eliminación fallida!");
			return "redirect:/transacciones/listar";
		}
	}

	
	

	@GetMapping("/mostrar/{id}")
	public String mostrarTransaccion(@PathVariable long id, Model modelo, RedirectAttributes redirectAttributes) {
		Transaccion transaccion = transaccionService.mostrar(id);
		if(transaccion != null) {
			modelo.addAttribute("transaccion", transaccion);
			return "mostrarTransaccion";
		}
		redirectAttributes.addFlashAttribute("msg_error", "consulta fallida");
		return "redirect:/transaccion/listar";
		
		
	}
	
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable long id, Model modelo, RedirectAttributes redirectAttributes) {
		Transaccion transaccion = transaccionService.mostrar(id);
		if(transaccion != null) {
			modelo.addAttribute("transaccion", transaccion);

			return "formTransaccion";
			
		}
		
		redirectAttributes.addFlashAttribute("msg_error", "Registro no encontrado");
		return "redirect:/transacciones/listar";
	}
}
