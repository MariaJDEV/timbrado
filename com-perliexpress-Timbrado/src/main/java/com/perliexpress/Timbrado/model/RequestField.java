package com.perliexpress.Timbrado.model;

import java.sql.Date;

public class RequestField {
	    public String requestorField;
	    public Object requestorNameField;
	    public boolean requestorActiveField;
	    public int transactionField;
	    public String countryField;
	    public String entityField;
	    public String userField;
	    public String userNameField;
	    public String idField;
	    public Date timeStampField;
		public String getRequestorField() {
			return requestorField;
		}
		public void setRequestorField(String requestorField) {
			this.requestorField = requestorField;
		}
		public Object getRequestorNameField() {
			return requestorNameField;
		}
		public void setRequestorNameField(Object requestorNameField) {
			this.requestorNameField = requestorNameField;
		}
		public boolean isRequestorActiveField() {
			return requestorActiveField;
		}
		public void setRequestorActiveField(boolean requestorActiveField) {
			this.requestorActiveField = requestorActiveField;
		}
		public int getTransactionField() {
			return transactionField;
		}
		public void setTransactionField(int transactionField) {
			this.transactionField = transactionField;
		}
		public String getCountryField() {
			return countryField;
		}
		public void setCountryField(String countryField) {
			this.countryField = countryField;
		}
		public String getEntityField() {
			return entityField;
		}
		public void setEntityField(String entityField) {
			this.entityField = entityField;
		}
		public String getUserField() {
			return userField;
		}
		public void setUserField(String userField) {
			this.userField = userField;
		}
		public String getUserNameField() {
			return userNameField;
		}
		public void setUserNameField(String userNameField) {
			this.userNameField = userNameField;
		}
		public String getIdField() {
			return idField;
		}
		public void setIdField(String idField) {
			this.idField = idField;
		}
		public Date getTimeStampField() {
			return timeStampField;
		}
		public void setTimeStampField(Date timeStampField) {
			this.timeStampField = timeStampField;
		}
		public RequestField(String requestorField, Object requestorNameField, boolean requestorActiveField,
				int transactionField, String countryField, String entityField, String userField, String userNameField,
				String idField, Date timeStampField) {
			super();
			this.requestorField = requestorField;
			this.requestorNameField = requestorNameField;
			this.requestorActiveField = requestorActiveField;
			this.transactionField = transactionField;
			this.countryField = countryField;
			this.entityField = entityField;
			this.userField = userField;
			this.userNameField = userNameField;
			this.idField = idField;
			this.timeStampField = timeStampField;
		}
		public RequestField() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "RequestField [requestorField=" + requestorField + ", requestorNameField=" + requestorNameField
					+ ", requestorActiveField=" + requestorActiveField + ", transactionField=" + transactionField
					+ ", countryField=" + countryField + ", entityField=" + entityField + ", userField=" + userField
					+ ", userNameField=" + userNameField + ", idField=" + idField + ", timeStampField=" + timeStampField
					+ "]";
		}
	    
	    
	    
}
