package edu.mx.utez.SCCT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mx.utez.SCCT.model.Transaccion;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long>{

}
