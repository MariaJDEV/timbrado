package com.prliexpress.gateway.servicioconecta.exception;



public class PerliException  extends Exception{
	private static final long serialVersionUID = 1L;

	public PerliException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public PerliException(String mensaje) {
		super(mensaje);
	}
	
	
}
