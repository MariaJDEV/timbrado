package com.perliexpress.back.timbrado.service;
import java.io.UnsupportedEncodingException;

import com.perliexpress.back.timbrado.model.RequestTimbrado;
import com.perliexpress.back.timbrado.model.ResponseTimbrado;

public interface TimbrarService {
	
	ResponseTimbrado peticionTimbrarCFDI(RequestTimbrado request)throws UnsupportedEncodingException;
	
}
