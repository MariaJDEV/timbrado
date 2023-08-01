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

public class PeticionTimbrar {
	public String Requestor;
	public String Transaction;
	public String Country;
	public String Entity;
    public String User;
	public String UserName;
	public String Data1;
	public String Data2;
	public String Data3;
	public String getRequestor() 
	{
		return Requestor;
	}
	public void setRequestor(String requestor) {
		Requestor = requestor;
	}
	public String getTransaction() {
		return Transaction;
	}
	public void setTransaction(String transaction) {
		Transaction = transaction;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getEntity() {
		return Entity;
	}
	public void setEntity(String entity) {
		Entity = entity;
	}
	public String getUser() {
		return User;
	}
	public void setUser(String user) {
		User = user;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getData1() {
		return Data1;
	}
	public void setData1(String data1) {
		Data1 = data1;
	}
	public String getData2() {
		return Data2;
	}
	public void setData2(String data2) {
		Data2 = data2;
	}
	public String getData3() {
		return Data3;
	}
	public void setData3(String data3) {
		Data3 = data3;
	}
	public PeticionTimbrar(String requestor, String transaction, String country, String entity, String user,
			String userName, String data1, String data2, String data3) {
		super();
		Requestor = requestor;
		Transaction = transaction;
		Country = country;
		Entity = entity;
		User = user;
		UserName = userName;
		Data1 = data1;
		Data2 = data2;
		Data3 = data3;
	}
	public PeticionTimbrar() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PeticionTimbrar [Requestor=" + Requestor + ", Transaction=" + Transaction + ", Country=" + Country
				+ ", Entity=" + Entity + ", User=" + User + ", UserName=" + UserName + ", Data1=" + Data1 + ", Data2="
				+ Data2 + ", Data3=" + Data3 + "]";
	}
	
	
	
	

}
