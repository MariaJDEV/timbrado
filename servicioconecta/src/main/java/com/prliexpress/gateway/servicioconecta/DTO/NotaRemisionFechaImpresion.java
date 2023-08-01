package com.prliexpress.gateway.servicioconecta.DTO;
import java.util.Date;

public class NotaRemisionFechaImpresion {
	private Date fechaMovto;

	public Date getFechaMovto() {
		return fechaMovto;
	}

	public void setFechaMovto(Date fechaMovto) {
		this.fechaMovto = fechaMovto;
	}

	public NotaRemisionFechaImpresion(Date fechaMovto) {		
		this.fechaMovto = fechaMovto;
	}

	@Override
	public String toString() {
		return "ComplementoPagoFechaImpresionDto [fechaMovto=" + fechaMovto + "]";
	}
}
