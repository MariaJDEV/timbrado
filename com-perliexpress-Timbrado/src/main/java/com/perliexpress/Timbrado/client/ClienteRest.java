package com.perliexpress.Timbrado.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.perliexpress.Timbrado.model.PeticionTimbrar;
import com.perliexpress.Timbrado.model.RespuestaTimbrado;

@FeignClient(name ="${service.name}",url="${service.base.url}")
public interface ClienteRest {	
	@PostMapping("")
	RespuestaTimbrado timbrarCFDI(PeticionTimbrar request);	

}
 