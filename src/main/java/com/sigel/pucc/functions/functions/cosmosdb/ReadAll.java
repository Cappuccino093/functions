package com.sigel.pucc.functions.functions.cosmosdb;
/* package com.sigel.pucc.functions.cosmosdb;

import com.azure.cosmos.CosmosAsyncContainer;
import com.azure.cosmos.models.CosmosQueryRequestOptions;
import com.azure.cosmos.models.FeedResponse;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

public class ReadAll {

	@FunctionName("read-all")
	public HttpResponseMessage run(
		@HttpTrigger(
			name = "req",
			methods = { HttpMethod.GET },
			authLevel = AuthorizationLevel.ANONYMOUS,
			route = "cosmos-db/read-all"
		) HttpRequestMessage<Object> httpRequestMessage,
		@BindingName("container") String container,
		final ExecutionContext executionContext
	) {
		executionContext.getLogger().info("Leyendo documentos...");

		if (container == null || container.isEmpty()) {
			executionContext.getLogger().warning("No se ha proporcionado un nombre del contenedor.");
			return httpRequestMessage
				.createResponseBuilder(HttpStatus.BAD_REQUEST)
				.body("No se ha proporcionado un nombre del contenedor.")
				.build();
		}

		String[] containerPath = container.split("/");
		CosmosAsyncContainer cosmosAsyncContainer = CosmosDb.cosmosAsyncDatabase.getContainer(containerPath[0]);
		for (int i = 1; i < containerPath.length; i++) {
			cosmosAsyncContainer = cosmosAsyncContainer.getDatabase().getContainer(containerPath[i]);
		}

		if (cosmosAsyncContainer == null) {
			executionContext.getLogger().warning("El contenedor " + container + " no existe.");
			return httpRequestMessage
				.createResponseBuilder(HttpStatus.NOT_FOUND)
				.body("El contenedor " + container + " no existe.")
				.build();
		}

		CosmosQueryRequestOptions options = new CosmosQueryRequestOptions();
		FeedResponse<Object> response = cosmosAsyncContainer
			.queryItems("SELECT * FROM c", options, Object.class)
			.byPage()
			.blockFirst();

		if (
			response == null ||
			Integer.parseInt(response.getResponseHeaders().get("x-ms-status-code")) != HttpStatus.OK.value()
		) {
			executionContext.getLogger().severe("Error al leer los documentos.");
			return httpRequestMessage
				.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Error al leer los documentos.")
				.build();
		} else {
			executionContext.getLogger().info("Documentos leídos.");
			return httpRequestMessage.createResponseBuilder(HttpStatus.OK).body(response.getElements()).build();
		}
	}
}
 */