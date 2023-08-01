package com.prliexpress.gateway.servicioconecta.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data

@ToString
public class ResponseTimbrado {
	
	public boolean exito;
	public String mensaje;
	public boolean isExito() {
		return exito;
	}
	public void setExito(boolean exito) {
		this.exito = exito;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public ResponseTimbrado(boolean exito, String mensaje) {
		super();
		this.exito = exito;
		this.mensaje = mensaje;
	}
	public ResponseTimbrado() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ResponseTimbrado [exito=" + exito + ", mensaje=" + mensaje + "]";
	}
	
	
	
	
}
