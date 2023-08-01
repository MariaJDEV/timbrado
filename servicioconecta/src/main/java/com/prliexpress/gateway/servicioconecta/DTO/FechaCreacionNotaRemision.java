package com.prliexpress.gateway.servicioconecta.DTO;

public class FechaCreacionNotaRemision {

	private String anio;
	private String mes;
	private String dia;

	public FechaCreacionNotaRemision() {
		
	}
	
	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public FechaCreacionNotaRemision(String anio, String mes, String dia) {
		this.anio = anio;
		this.mes = mes;
		this.dia = dia;
	}

	@Override
	public String toString() {
		return "FechaCreacionNotaRemision [anio=" + anio + ", mes=" + mes + ", dia=" + dia + "]";
	}

}
