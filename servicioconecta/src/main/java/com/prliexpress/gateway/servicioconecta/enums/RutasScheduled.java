package com.prliexpress.gateway.servicioconecta.enums;

public enum RutasScheduled {

	RUTAPRINCIPAL("//ServConexion/FTP/"),	
	RUTAPRINCIPALNOTASREMISION("//ServConexion/FTP/Nota_Remision_"),
	RUTADESTINOYAEXISTE("La ruta de destino ya tiene un archivo de destino...Probablemente ya exista y no se pudo hacer el movimiento de reemplazo del principal a su carpeta correspondiente"),
	RUTAIOEXCEPTION("I/O No se pudo realizar el movimento de archivos ERROR CRITICO"),
	CONDICIONINVALIDA("No se pudo crear la carpeta porque entro a una condicion case que no existe");
	private String ruta;

	private RutasScheduled(String ruta) {
		this.ruta = ruta;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

}
