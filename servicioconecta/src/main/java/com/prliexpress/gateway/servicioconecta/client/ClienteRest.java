package com.prliexpress.gateway.servicioconecta.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import com.prliexpress.gateway.servicioconecta.DTO.RequestTimbrado;
import com.prliexpress.gateway.servicioconecta.DTO.ResponseTimbrado;

@FeignClient(name ="${service.name}",url="${service.base.url}")
public interface ClienteRest {	
	@PostMapping("")
	ResponseTimbrado timbrarCFDI(RequestTimbrado request);	

}



 