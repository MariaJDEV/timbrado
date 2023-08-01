package com.prliexpress.gateway.servicioconecta.DTO;

import java.util.HashMap;
import java.util.Map;

public enum MessagePerliExpress {
	
	REQUEST_NULL("RQN01", "El request no contiene el body con parámetros"),
	TIMBRADO_REQUIRED_SERIE("RQN02", "El parámetro serie es requerido"),
	TIMBRADO_REQUIRED_ID("RQN03", "El parámetro id es requerido"),
	TIMBRADO_REQUIRED_XML("RQN04", "El parámetro xml es requerido");

	private String code;

	private String message;

	private static final Map<String, MessagePerliExpress> map;

	static {
		map = new HashMap<>();
		MessagePerliExpress[] values = values();
		for (int i = 0; i < values.length; i++)
			map.put(values[i].getCode(), values[i]);
	}

	MessagePerliExpress(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}

	public static MessagePerliExpress getByCode(String code) {
		return map.get(code);
	}
}
