package com.prliexpress.gateway.servicioconecta.entityperli;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Talones_Clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Talones_Clientes implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IdTalones")
	private Integer IdTalones;

	@Column(name = "QuienPaga")
	private Integer QuienPaga;

	@Column(name = "IdClientes")
	private Integer IdClientes;
}