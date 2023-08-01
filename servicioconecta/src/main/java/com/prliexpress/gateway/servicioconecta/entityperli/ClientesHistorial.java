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
@Table(name = "ClientesHistorial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientesHistorial implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IdClientesHistorial")
	private Integer IdClientesHistorial;

	@Column(name = "IdClientes")
	private Integer IdClientes;
}