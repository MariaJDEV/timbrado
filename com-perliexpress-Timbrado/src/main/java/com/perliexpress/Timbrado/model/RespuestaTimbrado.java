package com.perliexpress.Timbrado.model;



public class RespuestaTimbrado{

public RequestField requestField;
public ResponseField responseField;
public ResponseDataField responseDataField;

public RequestField getRequestField() {
	return requestField;
}
public void setRequestField(RequestField requestField) {
	this.requestField = requestField;
}
public ResponseField getResponseField() {
	return responseField;
}
public void setResponseField(ResponseField responseField) {
	this.responseField = responseField;
}
public ResponseDataField getResponseDataField() {
	return responseDataField;
}
public void setResponseDataField(ResponseDataField responseDataField) {
	this.responseDataField = responseDataField;
}
public RespuestaTimbrado(RequestField requestField, ResponseField responseField, ResponseDataField responseDataField) {
	super();
	this.requestField = requestField;
	this.responseField = responseField;
	this.responseDataField = responseDataField;
}
public RespuestaTimbrado() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "RespuestaTimbrado [requestField=" + requestField + ", responseField=" + responseField
			+ ", responseDataField=" + responseDataField + "]";
}



}
