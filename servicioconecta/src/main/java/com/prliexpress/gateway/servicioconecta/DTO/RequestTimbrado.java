package com.prliexpress.gateway.servicioconecta.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data

@ToString
public class RequestTimbrado {
	
	public String serie; 
	public Integer id;
	public String xml;
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	public RequestTimbrado(String serie, Integer id, String xml) {
		super();
		this.serie = serie;
		this.id = id;
		this.xml = xml;
	}
	public RequestTimbrado() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "RequestTimbrado [serie=" + serie + ", id=" + id + ", xml=" + xml + "]";
	}
	
	
	
	
}
