package edu.mx.utez.SCCT.service;

import java.util.List;

import edu.mx.utez.SCCT.model.Transaccion;


public interface TransaccionService {

	List<Transaccion> listar();
	boolean guardar(Transaccion transaccion);
	boolean eliminar(long id);
	Transaccion mostrar(long id);
}
