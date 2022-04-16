package edu.mx.utez.SCCT.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mx.utez.SCCT.model.Servicio;
import edu.mx.utez.SCCT.repository.ServicioRepository;


@Service
public class ServicioServiceImpl implements ServicioService{

	@Autowired
	ServicioRepository servicioRepository;
	
	/*@Override*/
	public List<Servicio> listar() {
		return servicioRepository.findAll();
	}

	/*@Override*/
	public boolean guardar(Servicio servicio) {
		try{
			servicioRepository.save(servicio);
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*@Override*/
	public boolean eliminar(long id) {
		boolean existe = servicioRepository.existsById(id);
		if(existe) {
			servicioRepository.deleteById(id);
			return !servicioRepository.existsById(id);
		}else {
			return false;
		}
	}

	/*@Override*/
	public Servicio mostrar(long id) {
		Optional<Servicio> optional = servicioRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
