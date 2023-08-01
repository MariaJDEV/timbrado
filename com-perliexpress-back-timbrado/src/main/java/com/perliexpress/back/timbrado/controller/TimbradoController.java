package com.perliexpress.back.timbrado.controller;

import java.io.UnsupportedEncodingException;

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
import com.perliexpress.back.timbrado.client.ClienteRest;
import com.perliexpress.back.timbrado.commons.TimbradoConstants;
import com.perliexpress.back.timbrado.exception.PerliException;
import com.perliexpress.back.timbrado.model.RequestTimbrado;
import com.perliexpress.back.timbrado.model.ResponseTimbrado;
import com.perliexpress.back.timbrado.service.TimbrarService;
import com.perliexpress.back.timbrado.util.TimbradoValidations;


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
    
	@PostMapping(TimbradoConstants.TIMBRADO_CONTROLLER_TIMBRAR)
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
		return new ResponseEntity<String>("Api PerliExpress TimbradoCfdi 4.0 - Pruebas: Funcionado.", HttpStatus.OK);
	}
	
}
