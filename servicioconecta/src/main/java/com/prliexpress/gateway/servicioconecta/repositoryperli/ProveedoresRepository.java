package com.prliexpress.gateway.servicioconecta.repositoryperli;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.prliexpress.gateway.servicioconecta.DTO.ProveedorTimbrado;
import com.prliexpress.gateway.servicioconecta.entityperli.ProveedoresTimbrado;


@Repository
public interface ProveedoresRepository  extends JpaRepository<ProveedoresTimbrado, Integer>{
	
	@Query("SELECT NEW com.prliexpress.gateway.servicioconecta.DTO.ProveedorTimbrado(p.porcentajeFact) FROM  ProveedoresTimbrado AS p WHERE p.proceso LIKE 'F' AND p.estatus=1")
	public ProveedorTimbrado consultaProveedorTimbrado();

	
	@Query("SELECT NEW com.prliexpress.gateway.servicioconecta.DTO.ProveedorTimbrado(p.porcentajeFact) FROM  ProveedoresTimbrado AS p WHERE p.proceso LIKE 'M' AND p.estatus=1")
	public ProveedorTimbrado consultaProveedorTimbradoM();
	
}
