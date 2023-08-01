package com.perliexpress.back.timbrado.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import com.perliexpress.back.timbrado.model.PeticionTimbrar;
import com.perliexpress.back.timbrado.model.RespuestaTimbrar;

@FeignClient(name ="${service.name}",url="${service.base.url}")
public interface ClienteRest {	
	@PostMapping("timbrar")
	RespuestaTimbrar timbrarCFDI(PeticionTimbrar request);	
}
