package com.perliexpress.Timbrado.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perliexpress.Timbrado.client.ClienteRest;
import com.perliexpress.Timbrado.entitycfdi.TblFacturacion;
import com.perliexpress.Timbrado.entityperli.ProveedoresTimbrado;
import com.perliexpress.Timbrado.enums.Emisor;
import com.perliexpress.Timbrado.model.PeticionTimbrar;
import com.perliexpress.Timbrado.model.ProveedorTimbrado;
import com.perliexpress.Timbrado.model.RequestTimbrado;
import com.perliexpress.Timbrado.model.ResponseTimbrado;
import com.perliexpress.Timbrado.model.RespuestaTimbrado;
import com.perliexpress.Timbrado.repositorycfdi.FacturacionRepository;
import com.perliexpress.Timbrado.repositoryperli.ProveedoresRepository;
import com.perliexpress.Timbrado.service.TimbrarService;
import com.perliexpress.Timbrado.util.SelladoCFDI;
import com.perliexpress.Timbrado.util.XmlTimbrado;
import com.perliexpress.Timbrado.service.impl.TimbrarServiceImpl;

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
		logtimbrado.info(uuidlogs+"  ENTRANDO A PETICION DE TIMBRADO CFDI PROVEEDOR MYSUITE  "+request.getId());
		
		String cadenafinal="";
		String resultadoXML="";
	
		
		
		SelladoCFDI cfdi = new SelladoCFDI();
		RequestTimbrado objxmlBase64 = new RequestTimbrado();
		HashMap<String,String> tbl = null;
		HashMap<String,String> dattbl = null;
		
		
        try {
        	objxmlBase64.setXml(request.getXml());
        	
        	//System.out.print("objxmlBase64"+objxmlBase64.getXml());
		    cadenafinal=cfdi.crearComprobante(request.getXml());
		    tbl=cfdi.obetersello();
		   
			
        } catch (Exception e1) {
			e1.printStackTrace();
		}
		
        
		TblFacturacion facturacion=new TblFacturacion();
		Date date = new Date(); 
		String fechaSolicitud;
		PeticionTimbrar peticionTimbrar=new PeticionTimbrar();
		RespuestaTimbrado response= new RespuestaTimbrado();
		ResponseTimbrado responseResult=new ResponseTimbrado();
	/*
		String host="";
		try {
			 host=InetAddress.getLocalHost().getHostName().toUpperCase();
			 
			 logtimbrado.info(uuidlogs+"  CONECTADO A SERVIDOR: "+host);

		} catch (UnknownHostException e) {			
			e.printStackTrace();
			logtimbrado.error(uuidlogs+" ERROR EN LA OCNEXION DEL SERVIDOR ");
		}
		*/
		String host="servdeveloper";
		
		ProveedorTimbrado  proveedorTimbrado = proveedoresRepository.consultaProveedorTimbrado();
		
		if(proveedorTimbrado !=null) {
			
			
            peticionTimbrar.setRequestor(proveedorTimbrado.getContrasena());
            peticionTimbrar.setTransaction(Emisor.TRANSACTION.getEmisor());
            peticionTimbrar.setCountry(Emisor.COUNTRY.getEmisor());
            peticionTimbrar.setEntity(host.equals("PERLIEXP")?Emisor.PERLIEXP.getEmisor():Emisor.ENTITYPRUEBAS.getEmisor());
            peticionTimbrar.setUser(proveedorTimbrado.getContrasena());
        	peticionTimbrar.setUserName(proveedorTimbrado.getUsuario());
        	peticionTimbrar.setData1(cadenafinal);
        	peticionTimbrar.setData2("");
        	peticionTimbrar.setData3("");
        	
        	logtimbrado.info(uuidlogs+" SE VALIDA CORRECTAMENTE PROVEEDOR DE TIMBRADO");
        	
        	try {
    			
    			Thread.sleep(2000);
    			logtimbrado.info(uuidlogs+" SE CUMPLE LAPSO DE TIEMPO PARA PETICION");
    			response= clienteRest.timbrarCFDI(peticionTimbrar);
    			
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			logtimbrado.error(uuidlogs+" ERROR: NO FUE POSIBLE ENTRAR A LA PETICION DE TIMBRADO" );
    			e.printStackTrace();
    		}	
            
            
            
            if(response !=null){
             fechaSolicitud = DateFormatUtils.format(date, "dd-MM-yyyy HH:mm:ss.S"); 
             
           if(response.getResponseField().isResultField()){
        	   logtimbrado.info(uuidlogs+" SOLICITUD EXITOSA: CARTA PORTE TIMBRADA CORRECTAMENTE: "+request.getId());
				
        	   byte[] decodedBytess = Base64.getDecoder().decode(cadenafinal);		        
			    String decodedXmlFinal = new String(decodedBytess,"UTF-8");
			  
			    byte[] decodedBytes = Base64.getDecoder().decode(response.getResponseDataField().getResponseData1Field());		        
			    String decodedString = new String(decodedBytes,"UTF-8");
			   
			   
			    XmlTimbrado cfdiTimbre = new XmlTimbrado();
	       		 try {
	       		resultadoXML=cfdiTimbre.xmlTimbre(decodedXmlFinal, decodedString);
	       		dattbl=cfdiTimbre.obeterselloSat();
	       		
	       		} catch (Exception e) {
	       			e.printStackTrace();
	       		 }
	        	   
	       		Integer TblFacturacion= facturacionRepository.findByIdFacturacion(request.getId());
	       		 
	       		facturacion.setIdFacturacion(TblFacturacion);
			    facturacion.setClaveCapturista(request.getSerie());
			    facturacion.setNumeroFactura(request.getId());
				facturacion.setFechaSolicitud(fechaSolicitud);
				facturacion.setXmlSolicitado(request.getXml());
				facturacion.setIdEstatusFactura(2);
				facturacion.setFechaEmision(dattbl.get("dtFechaTimbrado"));
				facturacion.setFechaTimbrado(dattbl.get("dtFechaTimbrado"));
		        facturacion.setXmlTimbrado(resultadoXML);
				facturacion.setuUid(response.getRequestField().getIdField());
				facturacion.setCertificadoDigital(tbl.get("sCertificadoDigital"));
				facturacion.setCertificadoSAT(dattbl.get("sCertificadoSAT"));
				facturacion.setCadenaOriginal(tbl.get("sCadenaOriginal"));
				facturacion.setSelloDigitalCFDI(tbl.get("sSelloDigitalCFDI"));
				facturacion.setSelloDigitalSAT(dattbl.get("sSelloDigitalSAT"));
				facturacion.setErrorFacturacion(null);
				facturacion.setRutaFactura(null);
				facturacion.setCampoExtra1("M");
				facturacion.setCampoExtra2(null);
				facturacionRepository.save(facturacion); 
				logtimbrado.info(uuidlogs+" ACTUALIZACION DE REGISTRO CORRECTA A 2");
				//logtimbrado.info("TIMBRES GENERADOS POR LA CUENTA    "+response.getResponseDataField().getResponseData2Field());
			
				
				Integer totalTimbres =	Integer.parseInt(response.getResponseDataField().getResponseData2Field());
				proveedoresRepository.actualizarTimbres(totalTimbres);  
			   
			    
			 }else{
				 
				  Integer TblFacturacion= facturacionRepository.findByIdFacturacion(request.getId());
				  logtimbrado.error(uuidlogs+" ERROR AL TIMBRAR LA CARTA PORTE : "+request.getId()+" CAUSA: "+response.getResponseField().getDescriptionField());
				 
				    facturacion.setIdFacturacion(TblFacturacion);
				  	facturacion.setClaveCapturista(request.getSerie());
					facturacion.setNumeroFactura(request.getId());
					facturacion.setFechaSolicitud(fechaSolicitud);
					facturacion.setXmlSolicitado(request.getXml());
					facturacion.setIdEstatusFactura(4);
					facturacion.setFechaEmision(null);
					facturacion.setFechaTimbrado(null);
					facturacion.setXmlTimbrado(null);
					facturacion.setuUid(null);
					facturacion.setCertificadoDigital(null);
					facturacion.setCertificadoSAT(null);
					facturacion.setCadenaOriginal(null);
					facturacion.setSelloDigitalCFDI(null);
					facturacion.setSelloDigitalSAT(null);
					facturacion.setErrorFacturacion(response.getResponseField().getDataField());
					facturacion.setRutaFactura(null);
					facturacion.setCampoExtra1("M");
					facturacion.setCampoExtra2(null);
					facturacionRepository.save(facturacion);
					logtimbrado.error(uuidlogs+" ACTUALIZACION DE REGISTRO POR ERROR DE TIMBRE A 4  "+response.getResponseField().getDescriptionField());
				
					
				
			}	
			  	
              logtimbrado.info(uuidlogs+" ACTUALIZACION DE REGISTRO DE FORMA EXITOSA");
			  responseResult.setExito(response.getResponseField().isResultField());
			  responseResult.setMensaje(response.getResponseField().getDescriptionField());
			
			}
		}
		return responseResult;
	}

	


}
