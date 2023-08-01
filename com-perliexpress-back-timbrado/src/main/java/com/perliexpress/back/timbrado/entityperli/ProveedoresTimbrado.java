package com.perliexpress.back.timbrado.entityperli;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "ProveedoresTimbrado")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProveedoresTimbrado implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IdProveedorTimbrado")
	private Integer idProveedorTimbrado;
	
	@Column(name = "Estatus")
	private Integer estatus;

	@Column(name = "Nombre")
	private String nombre;
	
	@Column(name = "Usuario")
	private String usuario;
	
	@Column(name = "Contrasena")
	private String contrasena;
	
	@Column(name = "Pac")
	private Integer pac;
	
	@Column(name = "Proceso")
	private String proceso;
	

}
