package edu.mx.utez.SCCT.service;

import java.util.List;

import edu.mx.utez.SCCT.model.Documento;

public interface DocumentoService {

	List<Documento> listar();
	boolean guardar(Documento documento);
	boolean eliminar(long id);
	Documento mostrar(long id);
}
