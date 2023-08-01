package com.perliexpress.Timbrado.service;
import java.io.UnsupportedEncodingException;

import com.perliexpress.Timbrado.model.RequestTimbrado;
import com.perliexpress.Timbrado.model.ResponseTimbrado;



public interface TimbrarService {
	
	ResponseTimbrado peticionTimbrarCFDI(RequestTimbrado request)throws UnsupportedEncodingException;
	
	
}
