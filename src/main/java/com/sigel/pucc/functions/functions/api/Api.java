package com.sigel.pucc.functions.functions.api;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.sigel.pucc.functions.dto.MmceToken;
import com.sigel.pucc.functions.dto.MmceTokenResponse;
import com.sigel.pucc.functions.dto.Response;
import com.sigel.pucc.functions.exceptions.HttpUnauthorizedException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * Clase que contiene la función de Azure para realizar peticiones HTTP a PUCC
 */
public class Api {

	private static final HttpClient httpClient = HttpClient.newHttpClient();

	/**
	 * Función de Azure asíncrona ejecutable al realizar una petición HTTP a `/api`
	 *
	 * @param httpRequestMessage Petición HTTP a PUCC
	 * @param route Ruta de la petición a PUCC
	 * @return Respuesta de la petición PUCC
	 */
	@FunctionName("api")
	public HttpResponseMessage run(
		@HttpTrigger(
			name = "req",
			methods = {
				HttpMethod.GET,
				HttpMethod.HEAD,
				HttpMethod.POST,
				HttpMethod.PUT,
				HttpMethod.DELETE,
				HttpMethod.CONNECT,
				HttpMethod.OPTIONS,
				HttpMethod.TRACE,
				HttpMethod.PATCH
			},
			route = "api/{*route}"
		) HttpRequestMessage<Optional<String>> httpRequestMessage,
		@BindingName("route") String route,
		final ExecutionContext executionContext
	) {
		// Validar las variables de entorno
		executionContext.getLogger().info("Validando las variables de entorno.");
		String mmceTokenUrl = System.getenv("MmceTokenUrl");
		String puccUrl = System.getenv("PuccUrl");

		if (mmceTokenUrl == null || mmceTokenUrl.isEmpty()) {
			executionContext
				.getLogger()
				.severe("No se ha configurado la URL de validación de token de MMCE en las variables de entorno.");
			return httpRequestMessage
				.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(
					new Response(
						"Internal Server Error",
						(short) HttpStatus.INTERNAL_SERVER_ERROR.value(),
						"No se ha configurado la URL de validación de token de MMCE en las variables de entorno.",
						new NullPointerException().toString(),
						null
					)
				)
				.build();
		}

		if (puccUrl == null || puccUrl.isEmpty()) {
			executionContext.getLogger().severe("No se ha configurado la URL de PUCC en las variables de entorno.");
			return httpRequestMessage
				.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(
					new Response(
						"Internal Server Error",
						(short) HttpStatus.INTERNAL_SERVER_ERROR.value(),
						"No se ha configurado la URL de PUCC en las variables de entorno.",
						new NullPointerException().toString(),
						null
					)
				)
				.build();
		}

		// Validar los headers de la petición
		String username = httpRequestMessage.getHeaders().get("user-name");
		String token = httpRequestMessage.getHeaders().get("pucc-token");

		if (username == null || username.isEmpty() || token == null || token.isEmpty()) {
			executionContext.getLogger().warning("No se ha proporcionado ningún usuario ni token en la petición.");
			return httpRequestMessage
				.createResponseBuilder(HttpStatus.UNAUTHORIZED)
				.body(
					new Response(
						"Unauthorized",
						(short) HttpStatus.UNAUTHORIZED.value(),
						"No se ha proporcionado ningún usuario ni token en la petición.",
						new NullPointerException().toString(),
						null
					)
				)
				.build();
		}

		// Validar el token de MMCE
		HttpRequest httpRequest = HttpRequest
			.newBuilder()
			.uri(URI.create(mmceTokenUrl))
			.POST(HttpRequest.BodyPublishers.ofString((new MmceToken(username, token)).toJson()))
			.build();

		HttpResponse<String> httpResponse;

		try {
			httpResponse = httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString()).get();
		} catch (InterruptedException | ExecutionException exception) {
			return httpRequestMessage
				.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(
					new Response(
						"Internal Server Error",
						(short) HttpStatus.INTERNAL_SERVER_ERROR.value(),
						"Error al validar el token de MMCE.",
						exception.toString(),
						null
					)
				)
				.build();
		}

		MmceTokenResponse mmceTokenResponse = MmceTokenResponse.fromJson(httpResponse.body());

		if (mmceTokenResponse == null || mmceTokenResponse.getResult() != "Sesion valida") {
			return httpRequestMessage
				.createResponseBuilder(HttpStatus.UNAUTHORIZED)
				.body(
					new Response(
						"Unauthorized",
						(short) HttpStatus.UNAUTHORIZED.value(),
						"El token no es válido.",
						new HttpUnauthorizedException("El token no es válido").toString(),
						null
					)
				)
				.build();
		}

		// Recibir la petición completa que se quería hacer a PUCC
		URI uri = httpRequestMessage.getUri();
		Map<String, String> httpHeaders = httpRequestMessage.getHeaders();
		HttpMethod method = httpRequestMessage.getHttpMethod();

		return null;
	}
}
