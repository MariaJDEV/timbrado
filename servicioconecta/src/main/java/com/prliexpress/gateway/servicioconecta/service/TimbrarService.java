package com.prliexpress.gateway.servicioconecta.service;
import java.io.UnsupportedEncodingException;

import com.prliexpress.gateway.servicioconecta.DTO.RequestTimbrado;
import com.prliexpress.gateway.servicioconecta.DTO.ResponseTimbrado;





public interface TimbrarService {
	
	ResponseTimbrado peticionTimbrarCFDI(RequestTimbrado request)throws UnsupportedEncodingException;
	
	
}
