package com.prliexpress.gateway.servicioconecta.controller;

import java.io.UnsupportedEncodingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.prliexpress.gateway.servicioconecta.util.TimbradoValidations;
import com.prliexpress.gateway.servicioconecta.DTO.RequestTimbrado;
import com.prliexpress.gateway.servicioconecta.DTO.ResponseTimbrado;
import com.prliexpress.gateway.servicioconecta.client.ClienteRest;
import com.prliexpress.gateway.servicioconecta.commons.TimbradoConstan;
import com.prliexpress.gateway.servicioconecta.exception.PerliException;
import com.prliexpress.gateway.servicioconecta.service.TimbrarService;
import com.prliexpress.gateway.servicioconecta.service.impl.TimbrarServiceImpl;



@RestController
@RequestMapping(TimbradoConstan.TIMBRADO_CONTROLLER_API)
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PATCH})
public class TimbradoController {

	  @Autowired
	    TimbrarService timbrarService;
	    
	    @Autowired
	    ClienteRest clienteRest;
	    
	    @Autowired
		   TimbradoValidations timbradoValidations; 
	    
		@PostMapping(TimbradoConstan.TIMBRADO_CONTROLLER_TIMBRAR)
	    public ResponseTimbrado timbrar(@RequestBody RequestTimbrado requestTimbrado)throws UnsupportedEncodingException {        
			ResponseTimbrado response=new ResponseTimbrado();
	       
			
            try {
           timbradoValidations.getTimbradoValidation(requestTimbrado);
		    response = timbrarService.peticionTimbrarCFDI(requestTimbrado);
			
		
		} catch (PerliException e) {

			response.setExito(false);
	        response.setMensaje(e.getMessage());
	        return response;
		
		} 
		  
	        return response;
	    }
		
		@GetMapping({"/test"})
		 public ResponseEntity<String> testService() {	
				return new ResponseEntity<String>("Api PerliExpress Servicio Conecta 4.0 - Pruebas: Funcionado.", HttpStatus.OK);
		}
	
	
	
}
