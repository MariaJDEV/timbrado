package com.perliexpress.Timbrado.enums;

public enum Emisor {
	PERLIEXP("PEX910515FV0"),
	TRANSACTION("TIMBRAR"),
	TRANSACCIONCANCELACIONCFDI("CANCEL_CFDI_3"),
	TRANSACTIONCONSULTACFDI("CONSULT_TIMBRAR_DOCUMENT"),
	COUNTRY("MX"),
	ENTITYPRUEBAS("JES900109Q90");
	
	
	private final String emisor;

	public String getEmisor() {
		return emisor;
	}

	private Emisor(String emisor) {
		this.emisor = emisor;
	}

	
}
