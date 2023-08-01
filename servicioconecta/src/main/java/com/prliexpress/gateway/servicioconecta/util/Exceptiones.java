package com.prliexpress.gateway.servicioconecta.util;



public class Exceptiones  {

	public Integer  buscarCliente(Integer cliente) throws Exception{
		Integer clienteEspecial = 0;
		
	   if(cliente==56143 || cliente==3752 || cliente==994 || cliente==60642 ) {
			clienteEspecial=1;
		}
		return clienteEspecial;
		
	}	

}
