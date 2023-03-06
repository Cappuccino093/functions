package com.sigel.pucc.functions.dto;

import com.google.gson.Gson;

/**
 * Clase que contiene el token de MMCE
 */
public class MmceToken {
	private String username;
	private String token;

	/**
	 * Constructor de la clase
	 * @param userName Nombre de usuario
	 * @param puccToken Token del PUCC
	 */
	public MmceToken(String userName, String puccToken) {
		this.username = userName;
		this.token = puccToken;
	}

	/**
	 * Obtiene el nombre de usuario
	 * @return String con el nombre de usuario
	 */
	public String username() {
		return username;
	}

	/**
	 * Obtiene el token
	 * @return String con el token
	 */
	public String token() {
		return token;
	}

	/**
	 * Establece el token
	 * @param token
	 * @return MmceToken con el token establecido
	 */
	public MmceToken setToken(String token) {
		this.token = token;
		return this;
	}

	/**
	 * Establece el nombre de usuario
	 * @param username
	 * @return MmceToken con el nombre de usuario establecido
	 */
	public MmceToken setUsername(String username) {
		this.username = username;
		return this;
	}

	/**
	 * Convierte el objeto a JSON
	 * @return String con el JSON
	 */
	public String toJson() {
		return new Gson().toJson(this);
	}

	/**
	 * Crea una instancia de objeto a partir de un JSON
	 * @param json
	 * @return MmceToken con los datos del JSON
	 */
	public static MmceToken fromJson(String json) {
		return new Gson().fromJson(json, MmceToken.class);
	}
}
