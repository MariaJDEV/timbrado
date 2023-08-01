package com.prliexpress.gateway.servicioconecta.entityperli;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Talones_Empleados")
public class TalonesEmpleados {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdTalones_Empleados")
	private int idTalones_Empleados;

	@Column(name = "IdTalones")
	private int  idTalones;

	@Column(name = "IdEmpleados")
	private int idEmpleados;
	
	@Column(name = "FechaMovto")
	private Date fechaMovto;

	@Column(name = "TipoMovto")
	private char tipoMovto;
}