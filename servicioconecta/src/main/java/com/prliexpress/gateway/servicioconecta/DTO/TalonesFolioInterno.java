package com.prliexpress.gateway.servicioconecta.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

public class TalonesFolioInterno {
	private Integer IdTalones;

	public Integer getIdTalones() {
		return IdTalones;
	}

	public void setIdTalones(Integer idTalones) {
		IdTalones = idTalones;
	}

	public TalonesFolioInterno(Integer idTalones) {
		IdTalones = idTalones;
	}

	@Override
	public String toString() {
		return "TalonesFolioInterno [IdTalones=" + IdTalones + "]";
	}	
	
}
