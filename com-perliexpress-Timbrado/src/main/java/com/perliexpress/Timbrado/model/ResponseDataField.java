package com.perliexpress.Timbrado.model;

public class ResponseDataField {
	
	 public String responseData1Field;
	 public String responseData2Field;
	 public String responseData3Field;
	 public Object responseDataSetField;
	public String getResponseData1Field() {
		return responseData1Field;
	}
	public void setResponseData1Field(String responseData1Field) {
		this.responseData1Field = responseData1Field;
	}
	public String getResponseData2Field() {
		return responseData2Field;
	}
	public void setResponseData2Field(String responseData2Field) {
		this.responseData2Field = responseData2Field;
	}
	public String getResponseData3Field() {
		return responseData3Field;
	}
	public void setResponseData3Field(String responseData3Field) {
		this.responseData3Field = responseData3Field;
	}
	public Object getResponseDataSetField() {
		return responseDataSetField;
	}
	public void setResponseDataSetField(Object responseDataSetField) {
		this.responseDataSetField = responseDataSetField;
	}
	public ResponseDataField(String responseData1Field, String responseData2Field, String responseData3Field,
			Object responseDataSetField) {
		super();
		this.responseData1Field = responseData1Field;
		this.responseData2Field = responseData2Field;
		this.responseData3Field = responseData3Field;
		this.responseDataSetField = responseDataSetField;
	}
	public ResponseDataField() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ResponseDataField [responseData1Field=" + responseData1Field + ", responseData2Field="
				+ responseData2Field + ", responseData3Field=" + responseData3Field + ", responseDataSetField="
				+ responseDataSetField + "]";
	}


	 
	 
}
