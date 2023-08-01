package com.perliexpress.Timbrado.repositorycfdi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.perliexpress.Timbrado.entitycfdi.TblFacturacion;

@Repository
public interface FacturacionRepository  extends JpaRepository<TblFacturacion, Integer>{
	
	@Query("SELECT idFacturacion from TblFacturacion where nNumeroFactura like %?1% AND nIdEstatusFactura=1")
    Integer findByIdFacturacion(Integer nNumeroFactura);
	
}
