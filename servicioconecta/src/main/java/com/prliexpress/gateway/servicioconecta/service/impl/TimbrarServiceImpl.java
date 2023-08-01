package com.prliexpress.gateway.servicioconecta.service.impl;

import java.io.UnsupportedEncodingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prliexpress.gateway.servicioconecta.DTO.CCartaPorte;
import com.prliexpress.gateway.servicioconecta.DTO.ProveedorTimbrado;
import com.prliexpress.gateway.servicioconecta.DTO.RequestTimbrado;
import com.prliexpress.gateway.servicioconecta.DTO.ResponseTimbrado;
import com.prliexpress.gateway.servicioconecta.client.ClienteRest;
import com.prliexpress.gateway.servicioconecta.client.ClienteRestMy;
import com.prliexpress.gateway.servicioconecta.repositoryperli.CCartaPorteRepository;
import com.prliexpress.gateway.servicioconecta.repositoryperli.ProveedoresRepository;
import com.prliexpress.gateway.servicioconecta.service.TimbrarService;
import com.prliexpress.gateway.servicioconecta.util.Exceptiones;

@Service
public class TimbrarServiceImpl implements TimbrarService {

	private static final Logger logtimbrado = LogManager.getLogger(TimbrarServiceImpl.class);
	int i = 1;
	int m = 0;

	@Autowired
	ClienteRest clienteRest;

	@Autowired
	ClienteRestMy clienteRest1;

	@Autowired
	ProveedoresRepository proveedoresRepository;
	
	@Autowired
	CCartaPorteRepository clienteRepository;

	
	@Override
	public ResponseTimbrado peticionTimbrarCFDI(RequestTimbrado request) throws UnsupportedEncodingException {
		Integer cliente = 0;
		Integer clienteEspecial = 0;
		
		String uuidlogs = java.util.UUID.randomUUID().toString();
		logtimbrado.info(uuidlogs + " Entrando a peticion de timbrado servicio conecta Id: " + request.getId());
		
		CCartaPorte clientes = clienteRepository.consultaIdCliente(request.getId());
		if (clientes== null  || clientes.equals("")) {
			cliente=0;
		}else
		cliente = clientes.getIdClientes();
		
		
		Exceptiones BusquedaCliente = new Exceptiones();
	    try {
			clienteEspecial=BusquedaCliente.buscarCliente(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestTimbrado peticionTimbrar = new RequestTimbrado();
		ResponseTimbrado response = new ResponseTimbrado();
		ResponseTimbrado responseResult = new ResponseTimbrado();
		
		peticionTimbrar.setId(request.getId());
		peticionTimbrar.setSerie(request.getSerie());
		peticionTimbrar.setXml(request.getXml());

		ProveedorTimbrado proveedorTimbrado = proveedoresRepository.consultaProveedorTimbrado();
		ProveedorTimbrado proveedorTimbradoM = proveedoresRepository.consultaProveedorTimbradoM();
		
		int porcentajeI = proveedorTimbrado.getPorcentajeFact();
		int porcentajeM = proveedorTimbradoM.getPorcentajeFact();

		
		
	  if (clienteEspecial==1) {
	   response = clienteRest1.timbrarCFDI(peticionTimbrar);
	   
	   m = m + 1;
	   logtimbrado.info(uuidlogs + " ENTRO MAYLO O ESTEREN TIMBRADO MYSUITE: " + request.getId() + " CONTADOR I: " + i);
			
		}else 
		if (i <= porcentajeI) {
			response = clienteRest.timbrarCFDI(peticionTimbrar);
			i = i + 1;
			logtimbrado.info(uuidlogs + " ENTRO TIMBRADO INFINITY: " + request.getId() + " CONTADOR I: " + i);
			

		} else if (m <= porcentajeM) {

			if (porcentajeM == m) {

				response = clienteRest.timbrarCFDI(peticionTimbrar);
				logtimbrado.info(uuidlogs + " ENTRO TIMBRADO INFINITY SE REINICIA CONTADORES " + request.getId());

				i = 2;
				m = 0;

			} else {

				response = clienteRest1.timbrarCFDI(peticionTimbrar);

				if (response.isExito()) {

					m = m + 1;
					logtimbrado.info(uuidlogs + " ENTRO TIMBRADO MYSUITE " + request.getId() + " CONTADOR M: " + m);
				} else {

					response = clienteRest.timbrarCFDI(peticionTimbrar);
					logtimbrado.info(uuidlogs + " ENTRO TIMBRADO INFINITY FALLO MYSUITE" + request.getId());
				}

			}

		} else {

			response = clienteRest.timbrarCFDI(peticionTimbrar);
			i = 2;
			m = 0;
			logtimbrado.info(uuidlogs + " ENTRO TIMBRADO INFINITY FALLO POR DAFAULT " + request.getId());

		}

		responseResult.setExito(response.isExito());
		responseResult.setMensaje(response.getMensaje());
		logtimbrado.info(uuidlogs + " MENSAJE SERVICIO CONECTA " + response.getMensaje());

		return responseResult;

	}

}
