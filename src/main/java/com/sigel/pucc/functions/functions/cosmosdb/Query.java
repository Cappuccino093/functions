package com.sigel.pucc.functions.functions.cosmosdb;
/* package com.sigel.pucc.functions.cosmosdb;

import com.azure.cosmos.CosmosAsyncContainer;
import com.azure.cosmos.models.CosmosQueryRequestOptions;
import com.azure.cosmos.models.FeedResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import java.io.IOException;
import java.util.Map;

public class Query {

	@FunctionName("query")
	public HttpResponseMessage run(
		@HttpTrigger(
			name = "req",
			methods = { HttpMethod.POST },
			authLevel = AuthorizationLevel.ANONYMOUS,
			route = "cosmos-db/query"
		) HttpRequestMessage<String> httpRequestMessage,
		final ExecutionContext executionContext
	) {
		executionContext.getLogger().info("Ejecutando consulta...");

		String body = httpRequestMessage.getBody();

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> requestMap;
		try {
			requestMap = objectMapper.readValue(body, new TypeReference<>() {});
		} catch (IOException e) {
			executionContext.getLogger().warning("Error al leer el cuerpo de la petición.");
			return httpRequestMessage
				.createResponseBuilder(HttpStatus.BAD_REQUEST)
				.body("Error al leer el cuerpo de la petición.")
				.build();
		}

		String collection;
		String query;
		try {
			collection = requestMap.get("collection");
			query = requestMap.get("query");
		} catch (ClassCastException e) {
			executionContext.getLogger().warning("El cuerpo de la petición no es válido.");
			return httpRequestMessage
				.createResponseBuilder(HttpStatus.BAD_REQUEST)
				.body("El cuerpo de la petición no es válido.")
				.build();
		}

		if (collection == null || collection.isEmpty()) {
			executionContext.getLogger().warning("No se ha proporcionado un nombre del contenedor.");
			return httpRequestMessage
				.createResponseBuilder(HttpStatus.BAD_REQUEST)
				.body("No se ha proporcionado un nombre del contenedor.")
				.build();
		}

		if (query == null || query.isEmpty()) {
			executionContext.getLogger().warning("No se ha proporcionado una consulta.");
			return httpRequestMessage
				.createResponseBuilder(HttpStatus.BAD_REQUEST)
				.body("No se ha proporcionado una consulta.")
				.build();
		}

		String[] containerPath = collection.split("/");
		CosmosAsyncContainer cosmosAsyncContainer = CosmosDb.cosmosAsyncDatabase.getContainer(containerPath[0]);
		for (int i = 1; i < containerPath.length; i++) {
			cosmosAsyncContainer = cosmosAsyncContainer.getDatabase().getContainer(containerPath[i]);
		}

		if (cosmosAsyncContainer == null) {
			executionContext.getLogger().warning("El contenedor " + collection + " no existe.");
			return httpRequestMessage
				.createResponseBuilder(HttpStatus.NOT_FOUND)
				.body("El contenedor " + collection + " no existe.")
				.build();
		}

		CosmosQueryRequestOptions options = new CosmosQueryRequestOptions();
		FeedResponse<Object> response = cosmosAsyncContainer.queryItems(query, options, Object.class).byPage().blockFirst();

		if (
			response == null ||
			Integer.parseInt(response.getResponseHeaders().get("x-ms-status-code")) != HttpStatus.OK.value()
		) {
			executionContext.getLogger().severe("Error al ejecutar la consulta.");
			return httpRequestMessage
				.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Error al ejecutar la consulta.")
				.build();
		} else {
			executionContext.getLogger().info("Consulta ejecutada.");
			return httpRequestMessage.createResponseBuilder(HttpStatus.OK).body(response.getElements()).build();
		}
	}
}
 */