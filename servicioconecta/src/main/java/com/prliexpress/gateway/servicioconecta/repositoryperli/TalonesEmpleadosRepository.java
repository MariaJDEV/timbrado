package com.prliexpress.gateway.servicioconecta.repositoryperli;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prliexpress.gateway.servicioconecta.DTO.NotaRemisionFechaImpresion;
import com.prliexpress.gateway.servicioconecta.entityperli.TalonesEmpleados;

@Repository
public interface TalonesEmpleadosRepository extends JpaRepository<TalonesEmpleados, Integer> {
	
	@Query("SELECT new com.prliexpress.gateway.servicioconecta.DTO.NotaRemisionFechaImpresion(e.fechaMovto) FROM TalonesEmpleados as e where e.idTalones=:idTalones AND e.tipoMovto='I'")
	public NotaRemisionFechaImpresion obtieneFechaNotaRemisionImpreso(int idTalones);
}
