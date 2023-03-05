package com.sigel.pucc.functions.dto;

public class MmceToken {

	private String username;
	private String token;

	public MmceToken(String puccToken, String userName) {
		this.token = puccToken;
		this.username = userName;
	}

	public String username() {
		return username;
	}

	public String token() {
		return token;
	}

	public MmceToken setToken(String token) {
		this.token = token;
		return this;
	}

	public MmceToken setUsername(String username) {
		this.username = username;
		return this;
	}

	public String toJson() {
		return String.format("{\"username\":\"%s\",\"token\":\"%s\"}", username, token);
	}

	public static MmceToken fromJson(String json) throws Exception {
		if (json == null || json.isEmpty()) throw new Exception("El JSON está vacío o no existe.");

		String[] jsonParts = json.split("[{}:,]");

		if (jsonParts.length != 6) throw new Exception("No es un JSON válido de MmceToken.");

		String username = null;
		String token = null;

		for (int i = 0; i < jsonParts.length; i++) {
			String jsonPart = jsonParts[i].trim();

			if (jsonPart.equals("\"username\"")) {
				username = jsonParts[i + 2].trim();
			} else if (jsonPart.equals("\"token\"")) {
				token = jsonParts[i + 2].trim();
			}
		}

		if (username == null || username.isEmpty()) throw new Exception("No es un JSON válido de MmceToken.");
		if (token == null || token.isEmpty()) throw new Exception("No es un JSON válido de MmceToken.");

		return new MmceToken(token, username);
	}
}
