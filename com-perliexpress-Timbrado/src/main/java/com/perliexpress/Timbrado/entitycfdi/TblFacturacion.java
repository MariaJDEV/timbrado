package com.perliexpress.Timbrado.entitycfdi;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tblFacturacion")
@Data
//@AllArgsConstructor
//@NoArgsConstructor
@ToString
public class TblFacturacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdFacturacion")
	private Integer idFacturacion;
	
	@Column(name = "sClaveCapturista")
	private String claveCapturista;
	
	@Column(name = "nNumeroFactura")
	private Integer numeroFactura;
	
	@Column(name = "dtFechaSolicitud")
	private String fechaSolicitud;
	
	@Column(name = "sXmlSolicitado")
	private String xmlSolicitado;
	
	@Column(name = "nIdEstatusFactura")
	private Integer idEstatusFactura;
	
	@Column(name = "dtFechaEmision")
	private String fechaEmision;
	
	@Column(name = "dtFechaTimbrado")
	private String fechaTimbrado;
	
	@Column(name = "sXmlTimbrado")
	private String xmlTimbrado;
	
	@Column(name = "sUUID")
	private String uUid;
	
	@Column(name = "sCertificadoDigital")
	private String certificadoDigital;
	
	@Column(name = "sCertificadoSAT")
	private String certificadoSAT;
	
	
	@Column(name = "sCadenaOriginal")
	private String cadenaOriginal;
	
	
	@Column(name = "sSelloDigitalCFDI")
	private String selloDigitalCFDI;
	
	
	@Column(name = "sSelloDigitalSAT")
	private String selloDigitalSAT;
	
	@Column(name = "sErrorFacturacion")
	private String errorFacturacion;
	
	@Column(name = "sRutaFactura")
	private String rutaFactura;
	
	@Column(name = "sCampoExtra1")
	private String campoExtra1;
	
	@Column(name = "sCampoExtra2")
	private String campoExtra2;

	
	
	
	public Integer getIdFacturacion() {
		return idFacturacion;
	}

	public void setIdFacturacion(Integer idFacturacion) {
		this.idFacturacion = idFacturacion;
	}

	public String getClaveCapturista() {
		return claveCapturista;
	}

	public void setClaveCapturista(String claveCapturista) {
		this.claveCapturista = claveCapturista;
	}

	public Integer getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(Integer numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getXmlSolicitado() {
		return xmlSolicitado;
	}

	public void setXmlSolicitado(String xmlSolicitado) {
		this.xmlSolicitado = xmlSolicitado;
	}

	public Integer getIdEstatusFactura() {
		return idEstatusFactura;
	}

	public void setIdEstatusFactura(Integer idEstatusFactura) {
		this.idEstatusFactura = idEstatusFactura;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getFechaTimbrado() {
		return fechaTimbrado;
	}

	public void setFechaTimbrado(String fechaTimbrado) {
		this.fechaTimbrado = fechaTimbrado;
	}

	public String getXmlTimbrado() {
		return xmlTimbrado;
	}

	public void setXmlTimbrado(String xmlTimbrado) {
		this.xmlTimbrado = xmlTimbrado;
	}

	public String getuUid() {
		return uUid;
	}

	public void setuUid(String uUid) {
		this.uUid = uUid;
	}

	public String getCertificadoDigital() {
		return certificadoDigital;
	}

	public void setCertificadoDigital(String certificadoDigital) {
		this.certificadoDigital = certificadoDigital;
	}

	public String getCertificadoSAT() {
		return certificadoSAT;
	}

	public void setCertificadoSAT(String certificadoSAT) {
		this.certificadoSAT = certificadoSAT;
	}

	public String getCadenaOriginal() {
		return cadenaOriginal;
	}

	public void setCadenaOriginal(String cadenaOriginal) {
		this.cadenaOriginal = cadenaOriginal;
	}

	public String getSelloDigitalCFDI() {
		return selloDigitalCFDI;
	}

	public void setSelloDigitalCFDI(String selloDigitalCFDI) {
		this.selloDigitalCFDI = selloDigitalCFDI;
	}

	public String getSelloDigitalSAT() {
		return selloDigitalSAT;
	}

	public void setSelloDigitalSAT(String selloDigitalSAT) {
		this.selloDigitalSAT = selloDigitalSAT;
	}

	public String getErrorFacturacion() {
		return errorFacturacion;
	}

	public void setErrorFacturacion(String errorFacturacion) {
		this.errorFacturacion = errorFacturacion;
	}

	public String getRutaFactura() {
		return rutaFactura;
	}

	public void setRutaFactura(String rutaFactura) {
		this.rutaFactura = rutaFactura;
	}

	public String getCampoExtra1() {
		return campoExtra1;
	}

	public void setCampoExtra1(String campoExtra1) {
		this.campoExtra1 = campoExtra1;
	}

	public String getCampoExtra2() {
		return campoExtra2;
	}

	public void setCampoExtra2(String campoExtra2) {
		this.campoExtra2 = campoExtra2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TblFacturacion(Integer idFacturacion, String claveCapturista, Integer numeroFactura, String fechaSolicitud,
			String xmlSolicitado, Integer idEstatusFactura, String fechaEmision, String fechaTimbrado,
			String xmlTimbrado, String uUid, String certificadoDigital, String certificadoSAT, String cadenaOriginal,
			String selloDigitalCFDI, String selloDigitalSAT, String errorFacturacion, String rutaFactura,
			String campoExtra1, String campoExtra2) {
		super();
		this.idFacturacion = idFacturacion;
		this.claveCapturista = claveCapturista;
		this.numeroFactura = numeroFactura;
		this.fechaSolicitud = fechaSolicitud;
		this.xmlSolicitado = xmlSolicitado;
		this.idEstatusFactura = idEstatusFactura;
		this.fechaEmision = fechaEmision;
		this.fechaTimbrado = fechaTimbrado;
		this.xmlTimbrado = xmlTimbrado;
		this.uUid = uUid;
		this.certificadoDigital = certificadoDigital;
		this.certificadoSAT = certificadoSAT;
		this.cadenaOriginal = cadenaOriginal;
		this.selloDigitalCFDI = selloDigitalCFDI;
		this.selloDigitalSAT = selloDigitalSAT;
		this.errorFacturacion = errorFacturacion;
		this.rutaFactura = rutaFactura;
		this.campoExtra1 = campoExtra1;
		this.campoExtra2 = campoExtra2;
	}

	public TblFacturacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "TblFacturacion [idFacturacion=" + idFacturacion + ", claveCapturista=" + claveCapturista
				+ ", numeroFactura=" + numeroFactura + ", fechaSolicitud=" + fechaSolicitud + ", xmlSolicitado="
				+ xmlSolicitado + ", idEstatusFactura=" + idEstatusFactura + ", fechaEmision=" + fechaEmision
				+ ", fechaTimbrado=" + fechaTimbrado + ", xmlTimbrado=" + xmlTimbrado + ", uUid=" + uUid
				+ ", certificadoDigital=" + certificadoDigital + ", certificadoSAT=" + certificadoSAT
				+ ", cadenaOriginal=" + cadenaOriginal + ", selloDigitalCFDI=" + selloDigitalCFDI + ", selloDigitalSAT="
				+ selloDigitalSAT + ", errorFacturacion=" + errorFacturacion + ", rutaFactura=" + rutaFactura
				+ ", campoExtra1=" + campoExtra1 + ", campoExtra2=" + campoExtra2 + "]";
	}
	
	
	
	
	
	
	
	
}
