package com.perliexpress.Timbrado.service.impl;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.perliexpress.Timbrado.client.ClienteRest;
import com.perliexpress.Timbrado.enums.Emisor;
import com.perliexpress.Timbrado.model.PeticionTimbrar;
import com.perliexpress.Timbrado.model.ProveedorTimbrado;
import com.perliexpress.Timbrado.model.RequestTimbrado;
import com.perliexpress.Timbrado.model.ResponseTimbrado;
import com.perliexpress.Timbrado.model.RespuestaTimbrado;
import com.perliexpress.Timbrado.repositoryperli.ProveedoresRepository;
import com.perliexpress.Timbrado.service.CancelacionService;
import com.perliexpress.Timbrado.util.FirmarXML;


@Service
public class CancelacionServiceImpl implements CancelacionService {
	
	@Autowired
    ClienteRest clienteRest;
	
	@Autowired
	ProveedoresRepository proveedoresRepository;
	

	
	@Override
	public ResponseTimbrado peticionCancelarCFDI(RequestTimbrado request) throws XPathExpressionException, CertificateException, TransformerException, ParserConfigurationException, SAXException, IOException, GeneralSecurityException, MarshalException, XMLSignatureException {
		String host="" ,Data1="";
		
		
		FirmarXML xmlFirmado = new FirmarXML();
		Data1 = xmlFirmado.generateXMLDigitalSignature(request.getXml());
		
		
		PeticionTimbrar peticionTimbrar=new PeticionTimbrar();
		RespuestaTimbrado response= new RespuestaTimbrado();
		ResponseTimbrado responseResult=new ResponseTimbrado();
	
	/*	try {
			 host=InetAddress.getLocalHost().getHostName().toUpperCase();
			} catch (UnknownHostException e) {			
			e.printStackTrace();
		
		}
		*/
		host="servdeveloper";
		ProveedorTimbrado  proveedorTimbrado = proveedoresRepository.consultaProveedorTimbrado();
		
		if(proveedorTimbrado !=null) {
		 
		    peticionTimbrar.setRequestor(proveedorTimbrado.getContrasena());
			peticionTimbrar.setTransaction(Emisor.TRANSACCIONCANCELACIONCFDI.getEmisor());
            peticionTimbrar.setCountry(Emisor.COUNTRY.getEmisor());
            peticionTimbrar.setEntity(host.equals("PERLIEXP")?Emisor.PERLIEXP.getEmisor():Emisor.ENTITYPRUEBAS.getEmisor());
            peticionTimbrar.setUser(proveedorTimbrado.getContrasena());
            peticionTimbrar.setUserName(proveedorTimbrado.getUsuario());
        	peticionTimbrar.setData1(Data1);
        	peticionTimbrar.setData2("");
        	peticionTimbrar.setData3("");
        	response= clienteRest.timbrarCFDI(peticionTimbrar);
        	
        	
        	   if(response.getResponseField().isResultField()){
        		
        		
               	responseResult.setExito(response.getResponseField().isResultField());
       		    responseResult.setMensaje(response.getResponseDataField().getResponseData2Field());
       	
            
        	   }else {
        		responseResult.setExito(response.getResponseField().isResultField());
          		responseResult.setMensaje(response.getResponseField().getDataField());
                
        	   }

		}else {
			responseResult.setExito(response.getResponseField().isResultField());
		    responseResult.setMensaje("No se pudo encontrar proveedor de timbrado");
		}
		return responseResult;
	}




}
