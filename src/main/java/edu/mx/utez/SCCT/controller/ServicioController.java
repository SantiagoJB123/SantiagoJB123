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
import edu.mx.utez.SCCT.model.Servicio;
import edu.mx.utez.SCCT.service.DocumentoServiceImpl;
import edu.mx.utez.SCCT.service.ServicioServiceImpl;

@Controller
@RequestMapping(value = "/servicios")
public class ServicioController {
	
	
	@Autowired
	private ServicioServiceImpl servicioService;
	
	@Autowired
	private DocumentoServiceImpl documentoService;
	

	@GetMapping( "/listar" )
	public String listarServicios( Model model, RedirectAttributes redirectAtributtes) {
		
		model.addAttribute("listaServicios", servicioService.listar());
		return "listarServicios";
	}
	
	@GetMapping("/crear")
	public String crearServicio(Servicio servicio, Model model) {
		model.addAttribute("listaDocumentos", documentoService.listar());
		return "formServicio";
	}
	
	@PostMapping("/guardar")
	public String guardarServicio(@Valid @ModelAttribute("servicio")Servicio servicio,  BindingResult result, Model model, RedirectAttributes redirectAttributes) {
	
	boolean respuesta = servicioService.guardar(servicio);
	
	if(result.hasErrors()){

		for(ObjectError error: result.getAllErrors()){
		System.out.println("Error : " + error.getDefaultMessage());

		}
		return "formServicio";
		}
	
	if(respuesta) {
		redirectAttributes.addFlashAttribute("msg_success", "¡Registro de Servicio exitoso!");
		return "redirect:/servicios/listar";
	}else {
		redirectAttributes.addFlashAttribute("msg_error", "¡Registro de Servicio fallido!");
		return "redirect:/servicios/crear";
	}
	
	
	}
	
	
	////METODO PARA PROBAR LA RELACION DE LAS ENTIDADES SERVICIO - DOCUMENTO
	/*@GetMapping("/prueba")
	public String guardarServicio() {
	
		Documento documento = new Documento();
		documento.setNombre("Acta de nacimiento");
		documento.setEstado(true);
		
		
		Servicio servicio1 = new Servicio();
		servicio1.setNombre("Titulacion");
		servicio1.setDescripcion("tramite para poder recibir titulo...");
		servicio1.setPrecio(100.00);
		servicio1.addDocumento(documento);
		
	boolean respuesta = servicioService.guardar(servicio1);
	
	
	return "prueba";

	}*/
	
	

	@GetMapping("/eliminar/{id}")
	public String eliminarServicio(@PathVariable long id, RedirectAttributes redirectAttributes) {
		
		boolean respuesta = servicioService.eliminar(id);
		
		if(respuesta) {
			redirectAttributes.addFlashAttribute("msg_success", "¡Eliminacion exitosa!");
			return "redirect:/servicios/listar";
		}else {
			redirectAttributes.addFlashAttribute("msg_error", "¡Eliminación fallida!");
			return "redirect:/servicios/listar";
		}
	}

	
	
	
	
	/*
	 * 
	 * Display the specified resource
	 */
	@GetMapping("/mostrar/{id}")
	public String mostrarServicio(@PathVariable long id, Model modelo, RedirectAttributes redirectAttributes) {
		Servicio servicio = servicioService.mostrar(id);
		if(servicio != null) {
			modelo.addAttribute("listaDocumentos", documentoService.listar());
			modelo.addAttribute("servicio", servicio);
			return "mostrarServicio";
		}
		redirectAttributes.addFlashAttribute("msg_error", "consulta fallida");
		return "redirect:/servicios/listar";
		
		
	}
	
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable long id, Model modelo, RedirectAttributes redirectAttributes) {
		Servicio servicio = servicioService.mostrar(id);
		if(servicio != null) {
			modelo.addAttribute("listaDocumentos", documentoService.listar());
			modelo.addAttribute("servicio", servicio);

			return "formServicio";
			
		}
		
		redirectAttributes.addFlashAttribute("msg_error", "Registro no encontrado");
		return "redirect:/Servicios/listar";
	}
	
}
