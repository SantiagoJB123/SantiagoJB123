package edu.mx.utez.SCCT.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mx.utez.SCCT.model.Transaccion;
import edu.mx.utez.SCCT.repository.TransaccionRepository;

@Service
public class TransaccionServiceImpl implements TransaccionService{


	@Autowired
	TransaccionRepository transaccionRepository;

	/*@Override*/
	public List<Transaccion> listar() {
		
		return transaccionRepository.findAll();
	}

	/*@Override*/
	public boolean guardar(Transaccion transaccion) {
		try{
			transaccionRepository.save(transaccion);
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*@Override*/
	public boolean eliminar(long id) {
		boolean existe = transaccionRepository.existsById(id);
		if(existe) {
			transaccionRepository.deleteById(id);
			return !transaccionRepository.existsById(id);
		}else {
			return false;
		}
	}

	/*@Override*/
	public Transaccion mostrar(long id) {
		Optional<Transaccion> optional = transaccionRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
}
