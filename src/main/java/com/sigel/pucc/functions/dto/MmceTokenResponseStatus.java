package com.sigel.pucc.functions.dto;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Clase que contiene el estado de la respuesta de la validación de token de MMCE
 */
public class MmceTokenResponseStatus {

	@SerializedName("descripcion")
	private String description;

	@SerializedName("codigoEstado")
	private String statusCode;

	/**
	 * Constructor de la clase
	 * @param description Descripción de la respuesta
	 * @param statusCode Estado de la respuesta
	 */
	public MmceTokenResponseStatus(String description, String statusCode) {
		this.description = description;
		this.statusCode = statusCode;
	}

	/**
	 * Crear instancia de objeto a partir de un JSON
	 * @param json JSON
	 */
	public static MmceTokenResponseStatus fromJson(String json) {
		return new Gson().fromJson(json, MmceTokenResponseStatus.class);
	}

	/**
	 * Obtener descripción de la respuesta
	 * @return Descripción de la respuesta
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Establecer descripción de la respuesta
	 * @param description Descripción de la respuesta
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Obtener estado de la respuesta
	 * @return Estado de la respuesta
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * Establecer estado de la respuesta
	 * @param statusCode Estado de la respuesta
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * Crear JSON a partir de una instancia de objeto
	 */
	public String toJson() {
		return new Gson().toJson(this);
	}
}
