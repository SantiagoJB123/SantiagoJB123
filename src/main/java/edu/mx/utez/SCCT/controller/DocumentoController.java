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

import edu.mx.utez.SCCT.model.Documento;
import edu.mx.utez.SCCT.service.DocumentoServiceImpl;

@Controller
@RequestMapping(value = "/documentos")
public class DocumentoController {


	@Autowired
	private DocumentoServiceImpl documentoService;
	

	@GetMapping( "/listar" )
	public String listarServicios( Model model,
			RedirectAttributes redirectAtributtes) {
		model.addAttribute("listaDocumentos", documentoService.listar());

		return "listarDocumentos";
	}
	
	@GetMapping("/crear")
	public String crearDocumento(Documento documento, Model model) {
	
		return "formDocumento";
	}
	
	@PostMapping("/guardar")
	public String guardarServicio(@Valid @ModelAttribute("documento") Documento documento, BindingResult result,  Model model, RedirectAttributes redirectAttributes) {
	
	boolean respuesta = documentoService.guardar(documento);
	
	if(result.hasErrors()){

		for(ObjectError error: result.getAllErrors()){
		System.out.println("Error : " + error.getDefaultMessage());

		}
		return "formDocumento";
		}

	
	if(respuesta) {
		redirectAttributes.addFlashAttribute("msg_success", "¡Registro de Documento exitoso!");
		return "redirect:/documentos/listar";
	}else {
		redirectAttributes.addFlashAttribute("msg_error", "¡Registro de Documento fallido!");
		return "redirect:/documentos/crear";
	}
	
	
	}

	

	@GetMapping("/eliminar/{id}")
	public String eliminarServicio(@PathVariable long id, RedirectAttributes redirectAttributes) {
		
		boolean respuesta = documentoService.eliminar(id);
		
		if(respuesta) {
			redirectAttributes.addFlashAttribute("msg_success", "¡Eliminacion exitosa!");
			return "redirect:/documentos/listar";
		}else {
			redirectAttributes.addFlashAttribute("msg_error", "¡Eliminación fallida!");
			return "redirect:/documentos/listar";
		}
	}

	
	

	@GetMapping("/mostrar/{id}")
	public String mostrarServicio(@PathVariable long id, Model modelo, RedirectAttributes redirectAttributes) {
		Documento documento = documentoService.mostrar(id);
		if(documento != null) {
			modelo.addAttribute("documento", documento);
			return "mostrarDocumento";
		}
		redirectAttributes.addFlashAttribute("msg_error", "consulta fallida");
		return "redirect:/documentos/listar";
		
		
	}
	
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable long id, Model modelo, RedirectAttributes redirectAttributes) {
		Documento documento = documentoService.mostrar(id);
		if(documento != null) {
			modelo.addAttribute("documento", documento);

			return "formDocumento";
			
		}
		
		redirectAttributes.addFlashAttribute("msg_error", "Registro no encontrado");
		return "redirect:/documentos/listar";
	}
	
}
