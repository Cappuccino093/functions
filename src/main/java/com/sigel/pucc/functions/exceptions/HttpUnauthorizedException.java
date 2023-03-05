package com.sigel.pucc.functions.exceptions;

public class HttpUnauthorizedException extends Exception {

	private static final long serialVersionUID = 1L;

	public HttpUnauthorizedException(String message) {
		super(message);
	}
}
