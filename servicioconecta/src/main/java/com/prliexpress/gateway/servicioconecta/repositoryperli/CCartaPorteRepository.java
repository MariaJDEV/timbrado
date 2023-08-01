package com.prliexpress.gateway.servicioconecta.repositoryperli;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.prliexpress.gateway.servicioconecta.DTO.CCartaPorte;
import com.prliexpress.gateway.servicioconecta.entityperli.CartaPorte;



@Repository
public interface CCartaPorteRepository  extends JpaRepository<CartaPorte, Integer>{
	@Query("SELECT NEW com.prliexpress.gateway.servicioconecta.DTO.CCartaPorte(C.IdClientes) FROM  CartaPorte CP LEFT JOIN Talones T ON T.IdTalones=CP.IdTalones LEFT JOIN Talones_Clientes TC ON TC.IdTalones=T.IdTalones LEFT JOIN ClientesHistorial CH ON CH.IdClientesHistorial=TC.IdClientes LEFT JOIN Clientes C ON C.IdClientes=CH.IdClientes WHERE IdCCartaPorte=:IdCCartaPorte AND TC.QuienPaga=-3")
	public CCartaPorte consultaIdCliente(Integer IdCCartaPorte);
}
