package com.sigel.pucc.functions.dto;

import com.google.gson.Gson;

/**
 * Clase que contiene la respuesta de la validación de token de MMCE
 */
public class MmceTokenResponse {

	private MmceTokenResponseStatus status;
	private String result;

	/**
	 * Constructor de la clase
	 *
	 * @param mmceTokenResponseStatus Estado de la respuesta
	 * @param result                  Resultado de la respuesta
	 */
	public MmceTokenResponse(MmceTokenResponseStatus mmceTokenResponseStatus, String result) {
		this.status = mmceTokenResponseStatus;
		this.result = result;
	}

	/**
	 * Crea una instancia de objeto a partir de un JSON
	 */
	public static MmceTokenResponse fromJson(String json) {
		return new Gson().fromJson(json, MmceTokenResponse.class);
	}

	/**
	 * Obtiene el estado de la respuesta
	 *
	 * @return Estado de la respuesta
	 */
	public MmceTokenResponseStatus getStatus() {
		return status;
	}

	/**
	 * Establece el estado de la respuesta
	 *
	 * @param status Estado de la respuesta
	 */
	public void setStatus(MmceTokenResponseStatus status) {
		this.status = status;
	}

	/**
	 * Obtiene el resultado de la respuesta
	 *
	 * @return Resultado de la respuesta
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Establece el resultado de la respuesta
	 *
	 * @param result Resultado de la respuesta
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * Crear un JSON a partir de una instancia de objeto
	 */
	public String toJson() {
		return new Gson().toJson(this);
	}
}
