package com.perliexpress.back.timbrado.repositoryperli;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.perliexpress.back.timbrado.entityperli.ProveedoresTimbrado;
import com.perliexpress.back.timbrado.model.ProveedorTimbrado;

@Repository
public interface ProveedoresRepository  extends JpaRepository<ProveedoresTimbrado, Integer>{
	
	@Query("SELECT NEW com.perliexpress.back.timbrado.model.ProveedorTimbrado(p.usuario,p.contrasena) FROM  ProveedoresTimbrado AS p WHERE p.proceso LIKE 'F' AND p.estatus=1")
	public ProveedorTimbrado consultaProveedorTimbrado();
}
