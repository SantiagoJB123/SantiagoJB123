package edu.mx.utez.SCCT.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mx.utez.SCCT.model.Documento;
import edu.mx.utez.SCCT.repository.DocumentoRepository;

@Service
public class DocumentoServiceImpl implements DocumentoService{


	@Autowired
	DocumentoRepository documentoRepository;
	
	/*@Override*/
	public List<Documento> listar() {
		return documentoRepository.findAll();
	}

	/*@Override*/
	public boolean guardar(Documento documento) {
		try{
			documentoRepository.save(documento);
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*@Override*/
	public boolean eliminar(long id) {
		boolean existe = documentoRepository.existsById(id);
		if(existe) {
			documentoRepository.deleteById(id);
			return !documentoRepository.existsById(id);
		}else {
			return false;
		}
	}

	/*@Override*/
	public Documento mostrar(long id) {
		Optional<Documento> optional = documentoRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
