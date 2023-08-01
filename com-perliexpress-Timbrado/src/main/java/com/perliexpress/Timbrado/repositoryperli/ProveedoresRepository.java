package com.perliexpress.Timbrado.repositoryperli;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.perliexpress.Timbrado.entityperli.ProveedoresTimbrado;
import com.perliexpress.Timbrado.model.ProveedorTimbrado;
import antlr.collections.List;
import lombok.NonNull;

@Repository
public interface ProveedoresRepository  extends JpaRepository<ProveedoresTimbrado, Integer>{
	
	@Query("SELECT NEW com.perliexpress.Timbrado.model.ProveedorTimbrado(p.usuario,p.contrasena) FROM  ProveedoresTimbrado AS p WHERE p.proceso LIKE 'M' AND p.estatus=1")
	public ProveedorTimbrado consultaProveedorTimbrado();

	
	@Modifying
    @Transactional
    @Query("UPDATE ProveedoresTimbrado as pt SET pt.timbresRealizados=:totalTimbres  WHERE pt.idProveedorTimbrado = 6" )
    public void actualizarTimbres(int totalTimbres);
}
