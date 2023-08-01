package com.perliexpress.back.timbrado.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseTimbrado {
	
	public boolean exito;
	public String mensaje;
	
}
