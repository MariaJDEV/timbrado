package com.perliexpress.Timbrado.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class ProveedorTimbrado {
	private Integer idProveedorTimbrado;
	private Integer estatus;
	private String nombre;
	private @NonNull String usuario;
	private @NonNull String contrasena;
	private Integer pac;
	private String proceso;
	private Integer porcentajeFact;
	private Integer timbresRealizados;
	
	
	
	
	
}
