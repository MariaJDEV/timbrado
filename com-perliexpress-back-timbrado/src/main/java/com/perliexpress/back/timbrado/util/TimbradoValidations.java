package com.perliexpress.back.timbrado.util;

import org.springframework.stereotype.Component;

import com.perliexpress.back.timbrado.enums.MessagePerliExpress;
import com.perliexpress.back.timbrado.exception.PerliException;
import com.perliexpress.back.timbrado.model.RequestTimbrado;

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
