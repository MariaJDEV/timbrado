package com.prliexpress.gateway.servicioconecta.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import com.prliexpress.gateway.servicioconecta.DTO.RequestTimbrado;
import com.prliexpress.gateway.servicioconecta.DTO.ResponseTimbrado;

@FeignClient(name ="${service1.name1}",url="${service1.base1.url1}")
public interface ClienteRestMy {	
	@PostMapping("")
	ResponseTimbrado timbrarCFDI(RequestTimbrado request);	

}



 