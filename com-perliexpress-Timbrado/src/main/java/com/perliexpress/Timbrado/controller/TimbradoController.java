package com.perliexpress.Timbrado.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.perliexpress.Timbrado.client.ClienteRest;
import com.perliexpress.Timbrado.commons.TimbradoConstants;
import com.perliexpress.Timbrado.exception.PerliException;
import com.perliexpress.Timbrado.model.RequestTimbrado;
import com.perliexpress.Timbrado.model.ResponseTimbrado;
import com.perliexpress.Timbrado.service.CancelacionService;
import com.perliexpress.Timbrado.service.ConsultaEstatusService;
import com.perliexpress.Timbrado.service.TimbrarService;
import com.perliexpress.Timbrado.util.TimbradoValidations;

@RestController
@RequestMapping(TimbradoConstants.TIMBRADO_CONTROLLER_API)
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PATCH})
public class TimbradoController {

	  @Autowired
	    TimbrarService timbrarService;
	    
	    @Autowired
	    ClienteRest clienteRest;
	    
	    @Autowired
	   TimbradoValidations timbradoValidations; 
	    
	    @Autowired
	    CancelacionService cancelarService ; 
	    
	    @Autowired
	    ConsultaEstatusService consultaEstatusService ; 
	    
		@PostMapping(TimbradoConstants.TIMBRADO_CONTROLLER_TIMBRAR)
	    public ResponseTimbrado timbrar(@RequestBody RequestTimbrado requestTimbrado)throws XPathExpressionException, CertificateException, TransformerException, ParserConfigurationException, SAXException, IOException, GeneralSecurityException, MarshalException, XMLSignatureException {        
	        ResponseTimbrado response=new ResponseTimbrado();
	        
	        try {
			
	        	timbradoValidations.getTimbradoValidation(requestTimbrado);
	        	if(requestTimbrado.getSerie().equals("C")) {
			   response = cancelarService.peticionCancelarCFDI(requestTimbrado);
			    }else if(requestTimbrado.getSerie().equals("E")){
	            response = consultaEstatusService.peticionConsultarEstatus(requestTimbrado);
	           }else {
	        	response = timbrarService.peticionTimbrarCFDI(requestTimbrado);
				}
			
			} catch (PerliException e) {

				response.setExito(false);
		        response.setMensaje(e.getMessage());
		        return response;
			
			}      
	        return response;
	    }
		
		@GetMapping({"/test"})
		 public ResponseEntity<String> testService() {		
			return new ResponseEntity<String>("Api PerliExpress Mysuite - Pruebas: Funcionado.", HttpStatus.OK);
		}
	
	
	
}
