package edu.mx.utez.SCCT.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "servicios")
public class Servicio {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idServicio;
	
	@NotBlank(message="El nombre no puede estar en blanco")
	@Column(nullable = false, length = 45)
	private String nombre;
	
	@NotBlank(message="La descripcion no puede estar vacia")
	@Column(nullable = false, length = 45)
	private String descripcion;
	

	@NotNull(message="El precio no puede estar vacio")
	@Column(nullable = false)
	private Double precio;

	
	//@ManyToMany(fetch = FetchType.EAGER)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "servicios_documentos",
			joinColumns = @JoinColumn(name="idServicio"), 
			inverseJoinColumns = @JoinColumn(name="idDocumento"))
	private List<Documento> documento;
	
	
	public void addDocumento(Documento documento){
        if(this.documento == null){
            this.documento = new ArrayList();
        }
        
        this.documento.add(documento);
    }
	
	
	///////////////////////////////////////
	
	public Servicio() {
	}
	
	
	public Servicio(Long idServicio, String nombre, String descripcion, Double precio, List<Documento> documento) {
		super();
		this.idServicio = idServicio;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.documento = documento;
	}
	

	public Long getIdServicio() {
		return idServicio;
	}


	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Double getPrecio() {
		return precio;
	}


	public void setPrecio(Double precio) {
		this.precio = precio;
	}


	public List<Documento> getDocumento() {
		return documento;
	}


	public void setDocumento(List<Documento> documento) {
		this.documento = documento;
	}


	@Override
	public String toString() {
		return "Servicio [idServicio=" + idServicio + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", precio=" + precio + "]";
	}
	
	
	
	
}
