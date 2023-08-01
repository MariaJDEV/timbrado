package com.perliexpress.back.timbrado.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PeticionTimbrar {
	
	public String XmlCfdi40;
	public boolean Sellado;
	public String RFCEmisor;
	public boolean Pruebas;
	public String Usuario;
	public String Contraseña;
}
