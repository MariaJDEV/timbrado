package com.perliexpress.back.timbrado.enums;

public enum Emisor {
	PERLIPRUEBAS("AAA010101AAA"),
	PERLIEXP("PEX910515FV0");
	
	private final String emisor;

	public String getEmisor() {
		return emisor;
	}

	private Emisor(String emisor) {
		this.emisor = emisor;
	}

	
}
