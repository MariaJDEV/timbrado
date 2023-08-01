package com.prliexpress.gateway.servicioconecta.util;

import org.springframework.stereotype.Component;
import com.prliexpress.gateway.servicioconecta.DTO.MessagePerliExpress;
import com.prliexpress.gateway.servicioconecta.DTO.RequestTimbrado;
import com.prliexpress.gateway.servicioconecta.exception.PerliException;


@Component
public class TimbradoValidations {
		
	public void getTimbradoValidation(RequestTimbrado requestTimbrado)throws PerliException {
		
		if (requestTimbrado ==null) {
			throw new PerliException(MessagePerliExpress.REQUEST_NULL.getMessage());
		}
		else{
			if(requestTimbrado.getSerie().equals("") || requestTimbrado.getSerie().isEmpty()){
				throw new PerliException(MessagePerliExpress.TIMBRADO_REQUIRED_SERIE.getMessage());
			}
			if(requestTimbrado.getId()<0){
				throw new PerliException(MessagePerliExpress.TIMBRADO_REQUIRED_ID.getMessage());
			}
			if(requestTimbrado.getXml().equals("") || requestTimbrado.getXml().isEmpty()){
				throw new PerliException(MessagePerliExpress.TIMBRADO_REQUIRED_XML.getMessage());
			}
		}
		
		
		
	}
}
