package com.perliexpress.Timbrado.model;

import java.sql.Date;

public class ResponseField {

	public boolean resultField;
    public Date timeStampField;
    public String lastResultField;
    public int codeField;
    public String descriptionField;
    public String hintField;
    public String dataField;
    public String processorField;
    public Object identifierField;
    public Object batchIdentifierField;
	public boolean isResultField() {
		return resultField;
	}
	public void setResultField(boolean resultField) {
		this.resultField = resultField;
	}
	public Date getTimeStampField() {
		return timeStampField;
	}
	public void setTimeStampField(Date timeStampField) {
		this.timeStampField = timeStampField;
	}
	public String getLastResultField() {
		return lastResultField;
	}
	public void setLastResultField(String lastResultField) {
		this.lastResultField = lastResultField;
	}
	public int getCodeField() {
		return codeField;
	}
	public void setCodeField(int codeField) {
		this.codeField = codeField;
	}
	public String getDescriptionField() {
		return descriptionField;
	}
	public void setDescriptionField(String descriptionField) {
		this.descriptionField = descriptionField;
	}
	public String getHintField() {
		return hintField;
	}
	public void setHintField(String hintField) {
		this.hintField = hintField;
	}
	public String getDataField() {
		return dataField;
	}
	public void setDataField(String dataField) {
		this.dataField = dataField;
	}
	public String getProcessorField() {
		return processorField;
	}
	public void setProcessorField(String processorField) {
		this.processorField = processorField;
	}
	public Object getIdentifierField() {
		return identifierField;
	}
	public void setIdentifierField(Object identifierField) {
		this.identifierField = identifierField;
	}
	public Object getBatchIdentifierField() {
		return batchIdentifierField;
	}
	public void setBatchIdentifierField(Object batchIdentifierField) {
		this.batchIdentifierField = batchIdentifierField;
	}
	public ResponseField(boolean resultField, Date timeStampField, String lastResultField, int codeField,
			String descriptionField, String hintField, String dataField, String processorField, Object identifierField,
			Object batchIdentifierField) {
		super();
		this.resultField = resultField;
		this.timeStampField = timeStampField;
		this.lastResultField = lastResultField;
		this.codeField = codeField;
		this.descriptionField = descriptionField;
		this.hintField = hintField;
		this.dataField = dataField;
		this.processorField = processorField;
		this.identifierField = identifierField;
		this.batchIdentifierField = batchIdentifierField;
	}
	public ResponseField() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ResponseField [resultField=" + resultField + ", timeStampField=" + timeStampField + ", lastResultField="
				+ lastResultField + ", codeField=" + codeField + ", descriptionField=" + descriptionField
				+ ", hintField=" + hintField + ", dataField=" + dataField + ", processorField=" + processorField
				+ ", identifierField=" + identifierField + ", batchIdentifierField=" + batchIdentifierField + "]";
	}
    
    
    
    
}
