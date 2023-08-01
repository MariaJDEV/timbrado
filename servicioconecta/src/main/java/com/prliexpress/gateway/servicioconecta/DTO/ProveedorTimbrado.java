package com.prliexpress.gateway.servicioconecta.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class ProveedorTimbrado {
	
	private Integer idProveedorTimbrado;
	private Integer estatus;
	private String nombre;
	private String usuario;
	private String contrasena;
	private Integer pac;
	private String proceso;
	private @NonNull  Integer porcentajeFact;
	
	
	
}
