package com.perliexpress.back.timbrado.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perliexpress.back.timbrado.client.ClienteRest;
import com.perliexpress.back.timbrado.entitycfdi.TblFacturacion;
import com.perliexpress.back.timbrado.enums.Emisor;
import com.perliexpress.back.timbrado.model.PeticionTimbrar;
import com.perliexpress.back.timbrado.model.ProveedorTimbrado;
import com.perliexpress.back.timbrado.model.RequestTimbrado;
import com.perliexpress.back.timbrado.model.ResponseTimbrado;
import com.perliexpress.back.timbrado.model.RespuestaTimbrar;
import com.perliexpress.back.timbrado.repositorycfdi.FacturacionRepository;
import com.perliexpress.back.timbrado.repositoryperli.ProveedoresRepository;
import com.perliexpress.back.timbrado.service.TimbrarService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@Service
public class TimbrarServiceImpl implements TimbrarService {
	
	
	private static final Logger logtimbrado = LogManager.getLogger(TimbrarServiceImpl.class);

	@Autowired
	ClienteRest clienteRest;
	
	@Autowired 
	FacturacionRepository facturacionRepository;
	
	@Autowired
	ProveedoresRepository proveedoresRepository;
	
	@Override
	public ResponseTimbrado peticionTimbrarCFDI(RequestTimbrado request) throws UnsupportedEncodingException {
 String uuidlogs=java.util.UUID.randomUUID().toString();
		logtimbrado.info(uuidlogs+" Entrando a peticion de timbrado cfdi "+request.getId());
		
		TblFacturacion facturacion=new TblFacturacion();
		Date date = new Date(); 
		String fechaSolicitud;
		PeticionTimbrar peticionTimbrar=new PeticionTimbrar();
		RespuestaTimbrar response= new RespuestaTimbrar();
		ResponseTimbrado responseResult=new ResponseTimbrado();
		String host="";
		try {
			 host=InetAddress.getLocalHost().getHostName().toUpperCase();
			 
			 logtimbrado.info(uuidlogs+" Conectado a servidor: "+host);

		} catch (UnknownHostException e) {			
			e.printStackTrace();
			logtimbrado.error(uuidlogs+" Error en la conexion al servidor local");
		}
		
		ProveedorTimbrado  proveedorTimbrado = proveedoresRepository.consultaProveedorTimbrado();
		
		if(proveedorTimbrado !=null) {
			
			logtimbrado.info(uuidlogs+" Se valida correctamente provedor de timbrado");

			peticionTimbrar.setXmlCfdi40(request.getXml());
			peticionTimbrar.setSellado(false);
			peticionTimbrar.setRFCEmisor(host.equals("PERLIEXP")?Emisor.PERLIEXP.getEmisor():Emisor.PERLIPRUEBAS.getEmisor());
			peticionTimbrar.setPruebas(true);// TRUE=SERVDEVELOPER/SERVIDORQA   FALSE=PERLIEXP
			peticionTimbrar.setUsuario(proveedorTimbrado.getUsuario());
			peticionTimbrar.setContraseña(proveedorTimbrado.getContrasena());

			logtimbrado.info(uuidlogs+" Se valida correctamente provedor de timbrado");

		try {
			
			Thread.sleep(2000);
			logtimbrado.info(uuidlogs+" SE CUMPLE LAPSO DE TIEMPO PARA PETICION");
			response= clienteRest.timbrarCFDI(peticionTimbrar);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logtimbrado.error(uuidlogs+ " ERROR: No fue posible entrar a la peticion de timbrado" );
			e.printStackTrace();
		}	
			
			if(response !=null){
				fechaSolicitud = DateFormatUtils.format(date, "dd-MM-yyyy HH:mm:ss.S"); 
			  if(response.isExito()){
				  logtimbrado.info(uuidlogs+" Solicitud de timbrado exitosa: Carta Porte timbrada: "+request.getId());

		        byte[] decodedBytes = Base64.getDecoder().decode(response.getXML());		        
		        String decodedString = new String(decodedBytes,"UTF-8");
		        //String decodedString.getBytes("UTF-8");
		        
		      Integer TblFacturacion= facturacionRepository.findByIdFacturacion(request.getId());
		        
		      
		      	facturacion.setIdFacturacion(TblFacturacion);
		        facturacion.setClaveCapturista(request.getSerie());
				facturacion.setNumeroFactura(request.getId());
				facturacion.setFechaSolicitud(fechaSolicitud);
				facturacion.setXmlSolicitado(request.getXml());
				facturacion.setIdEstatusFactura(2);
				facturacion.setFechaEmision(response.getFechaTimbrado());
				facturacion.setFechaTimbrado(response.getFechaTimbrado());
				facturacion.setXmlTimbrado(decodedString);
				facturacion.setUUid(response.getUUID());
				facturacion.setCertificadoDigital(response.getNoCertificado());
				facturacion.setCertificadoSAT(response.getNoCertificadoSAT());
				facturacion.setCadenaOriginal(response.getCadenaOriginal());
				facturacion.setSelloDigitalCFDI(response.getSelloCFD());
				facturacion.setSelloDigitalSAT(response.getSelloSAT());
				facturacion.setErrorFacturacion(null);
				facturacion.setRutaFactura(null);
				facturacion.setCampoExtra1("I");
				facturacion.setCampoExtra2(null);
				facturacionRepository.save(facturacion);
				logtimbrado.info(uuidlogs+" Actualizacion de registro de forma exitosa");

		        
			 }else{
				  
			      Integer TblFacturacion= facturacionRepository.findByIdFacturacion(request.getId());
			        
			      logtimbrado.error(uuidlogs+" ERROR al timbrar la carta porte: "+request.getId()+" CAUSA: "+response.getMensaje());

			      	facturacion.setIdFacturacion(TblFacturacion);
				  	facturacion.setClaveCapturista(request.getSerie());
					facturacion.setNumeroFactura(request.getId());
					facturacion.setFechaSolicitud(fechaSolicitud);
					facturacion.setXmlSolicitado(request.getXml());
					facturacion.setIdEstatusFactura(4);
					facturacion.setFechaEmision(null);
					facturacion.setFechaTimbrado(null);
					facturacion.setXmlTimbrado(null);
					facturacion.setUUid(null);
					facturacion.setCertificadoDigital(null);
					facturacion.setCertificadoSAT(null);
					facturacion.setCadenaOriginal(null);
					facturacion.setSelloDigitalCFDI(null);
					facturacion.setSelloDigitalSAT(null);
					facturacion.setErrorFacturacion(response.getMensaje());
					facturacion.setRutaFactura(null);
					facturacion.setCampoExtra1("I");
					facturacion.setCampoExtra2(null);
				facturacionRepository.save(facturacion);
				logtimbrado.error(uuidlogs+" Actualizacion de registro por error de timbrado");

			  }	
			
			  logtimbrado.info(uuidlogs+" Actualizacion de registro de forma exitosa");
			  responseResult.setExito(response.isExito());
			  responseResult.setMensaje(response.getMensaje());
			}
			
		}

		return responseResult;
	}

}
