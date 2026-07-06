package com.test.liverpool.requestdto;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UsuarioRequestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idUsuario;
	@NotBlank(message = "El nombre no puede ir vacio")
	private String nombre;
	@NotBlank(message = "El apellido paterno no puede ir vacio")
	private String apellidoPaterno;
	@NotBlank(message = "El apellido materno no puede ir vacio")
	private String apellidoMaterno;
	@NotBlank(message = "El correo no puede ir vacio")
	@Email(message = "El formato del correo es incorrecto")
	private String correo;
	@NotBlank(message = "El userName no puede ir vacio")
	private String userName;

	private List<DetalleUsuarioRequestDto> lstDetalleUsuario;

	private List<Integer> lstDetalleUsuarioDelete;

	public UsuarioRequestDto() {
	}

	public List<Integer> getLstDetalleUsuarioDelete() {
		return lstDetalleUsuarioDelete;
	}

	public void setLstDetalleUsuarioDelete(List<Integer> lstDetalleUsuarioDelete) {
		this.lstDetalleUsuarioDelete = lstDetalleUsuarioDelete;
	}

	public List<DetalleUsuarioRequestDto> getLstDetalleUsuario() {
		return lstDetalleUsuario;
	}

	public void setLstDetalleUsuario(List<DetalleUsuarioRequestDto> lstDetalleUsuario) {
		this.lstDetalleUsuario = lstDetalleUsuario;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}
