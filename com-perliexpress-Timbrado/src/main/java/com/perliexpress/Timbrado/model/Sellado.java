package com.perliexpress.Timbrado.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
//@AllArgsConstructor
//@NoArgsConstructor
@ToString

public class Sellado {

public String sellado;
public String certificado;
public String cadenaGeneral;
public String xmlBase64;
public String getSellado() {
	return sellado;
}
public void setSellado(String sellado) {
	this.sellado = sellado;
}
public String getCertificado() {
	return certificado;
}
public void setCertificado(String certificado) {
	this.certificado = certificado;
}
public String getCadenaGeneral() {
	return cadenaGeneral;
}
public void setCadenaGeneral(String cadenaGeneral) {
	this.cadenaGeneral = cadenaGeneral;
}
public String getXmlBase64() {
	return xmlBase64;
}
public void setXmlBase64(String xmlBase64) {
	this.xmlBase64 = xmlBase64;
}
public Sellado(String sellado, String certificado, String cadenaGeneral, String xmlBase64) {
	super();
	this.sellado = sellado;
	this.certificado = certificado;
	this.cadenaGeneral = cadenaGeneral;
	this.xmlBase64 = xmlBase64;
}
@Override
public String toString() {
	return "Sellado [sellado=" + sellado + ", certificado=" + certificado + ", cadenaGeneral=" + cadenaGeneral
			+ ", xmlBase64=" + xmlBase64 + "]";
}


	
	
	
}
