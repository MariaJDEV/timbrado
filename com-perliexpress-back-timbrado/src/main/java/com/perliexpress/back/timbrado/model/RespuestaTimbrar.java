package com.perliexpress.back.timbrado.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RespuestaTimbrar {
	
	public int  IdInterno;
	public String MensajeDetallado;
	public String UUID;
	public String FechaTimbrado;
	public String NoCertificadoSAT;
	public String SelloCFD;
	public String SelloSAT;
	public String RfcProvCertif;
	public String Leyenda;
	public String CadenaOriginal;
	public String NoCertificado;	
	//public byte[]  XML;	
	public String  XML;	
	public boolean Exito;	
	public String Codigo;
	public String Mensaje;

	
}
