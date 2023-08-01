package com.perliexpress.back.timbrado.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestTimbrado {
	
	public String serie; 
	public Integer id;
	public String xml;
}
