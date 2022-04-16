package edu.mx.utez.SCCT.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "transacciones")
public class Transaccion {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idTransaccion;
	
	@NotNull(message="El monto no puede estar vacio")
	@Column(nullable = false)
	private Double monto;
	
	
	@NotNull(message="Selecciona un estado valido")
	@Column(nullable = false)
	private Boolean estado;
	
	@NotNull(message="La cita no puede estar vacia")
	@Column(nullable = false)
	private Integer idCita;

	public Long getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(Long idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Integer getIdCita() {
		return idCita;
	}

	public void setIdCita(Integer idCita) {
		this.idCita = idCita;
	}

	public Transaccion(Long idTransaccion, @NotNull(message = "El precio no puede estar vacio") Double monto,
			@NotNull(message = "Selecciona un estado valido") Boolean estado,
			@NotNull(message = "La cita no puede estar vacia") Integer idCita) {
		this.idTransaccion = idTransaccion;
		this.monto = monto;
		this.estado = estado;
		this.idCita = idCita;
	}

	public Transaccion() {
	}

	@Override
	public String toString() {
		return "Transaccion [idTransaccion=" + idTransaccion + ", monto=" + monto + ", estado=" + estado + ", idCita="
				+ idCita + "]";
	}
	
	
	
	

}
