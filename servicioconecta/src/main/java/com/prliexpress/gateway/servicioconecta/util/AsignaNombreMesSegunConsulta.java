package com.prliexpress.gateway.servicioconecta.util;

public class AsignaNombreMesSegunConsulta {

	public String retornaNombreMes(String obtieneMes) {
		switch (obtieneMes) {
		case "01":
			obtieneMes = "Enero";
			break;
		case "02":
			obtieneMes = "Febrero";
			break;

		case "03":
			obtieneMes = "Marzo";
			break;

		case "04":
			obtieneMes = "Abril";
			break;

		case "05":
			obtieneMes = "Mayo";
			break;

		case "06":
			obtieneMes = "Junio";
			break;

		case "07":
			obtieneMes = "Julio";
			break;

		case "08":
			obtieneMes = "Agosto";
			break;

		case "09":
			obtieneMes = "Septiembre";
			break;

		case "10":
			obtieneMes = "Octubre";
			break;

		case "11":
			obtieneMes = "Noviembre";
			break;

		case "12":
			obtieneMes = "Diciembre";
			break;
		}
		return obtieneMes;
	}

}
