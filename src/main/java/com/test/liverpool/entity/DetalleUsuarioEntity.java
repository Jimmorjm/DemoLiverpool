package com.test.liverpool.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "ENT_DETALLE_USUARIO")
public class DetalleUsuarioEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column("ID_DETALLE_USUARIO")
	private Integer idDetalleUsuario;
	@Column("CALLE_NUMERO")
	private String calleNumero;
	@Column("COLONIA_MUNICIPIO")
	private String coloniaMunicipio;
	@Column("DEL_ALCALDIA")
	private String delAlcaldia;
	@Column("CODIGO_POSTAL")
	private String codigoPostal;
	@Column("ESTADO")
	private String estado;
	@Column("ID_USUARIO")
	private Integer idUsuario;
	@Column("ESTATUS_DETALLE_USUARIO")
	private Integer estatusDetalleUsuario;

	public Integer getIdDetalleUsuario() {
		return idDetalleUsuario;
	}

	public void setIdDetalleUsuario(Integer idDetalleUsuario) {
		this.idDetalleUsuario = idDetalleUsuario;
	}

	public Integer getEstatusDetalleUsuario() {
		return estatusDetalleUsuario;
	}

	public void setEstatusDetalleUsuario(Integer estatusDetalleUsuario) {
		this.estatusDetalleUsuario = estatusDetalleUsuario;
	}

	public String getCalleNumero() {
		return calleNumero;
	}

	public void setCalleNumero(String calleNumero) {
		this.calleNumero = calleNumero;
	}

	public String getColoniaMunicipio() {
		return coloniaMunicipio;
	}

	public void setColoniaMunicipio(String coloniaMunicipio) {
		this.coloniaMunicipio = coloniaMunicipio;
	}

	public String getDelAlcaldia() {
		return delAlcaldia;
	}

	public void setDelAlcaldia(String delAlcaldia) {
		this.delAlcaldia = delAlcaldia;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

}
