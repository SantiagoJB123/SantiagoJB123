package edu.mx.utez.SCCT.service;

import java.util.List;

import edu.mx.utez.SCCT.model.Servicio;

public interface ServicioService {
	List<Servicio> listar();
	boolean guardar(Servicio servicio);
	boolean eliminar(long id);
	Servicio mostrar(long id);

}
