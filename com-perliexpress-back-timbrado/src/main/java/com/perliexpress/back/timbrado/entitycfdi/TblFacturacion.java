package com.perliexpress.back.timbrado.entitycfdi;

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
@AllArgsConstructor
@NoArgsConstructor
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
	
}
