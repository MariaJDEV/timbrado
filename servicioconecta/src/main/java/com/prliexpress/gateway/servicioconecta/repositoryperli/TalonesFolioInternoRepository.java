package com.prliexpress.gateway.servicioconecta.repositoryperli;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import com.prliexpress.gateway.servicioconecta.DTO.TalonesFolioInterno;

@Repository
public class TalonesFolioInternoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public List<TalonesFolioInterno> obtieneFolioInternoTalones(final Integer numeroControl, final int limit) {
		return (List<TalonesFolioInterno>) this.entityManager.createQuery(
				"Select NEW com.prliexpress.gateway.servicioconecta.DTO.TalonesFolioInterno(T.IdTalones) FROM Talones as T "
				+ "WHERE T.NumeroControl=:IdTalones order by T.IdTalones desc",
				(Class) TalonesFolioInterno.class).setParameter("IdTalones", (Object) numeroControl).setMaxResults(limit).getResultList();
	}
}
