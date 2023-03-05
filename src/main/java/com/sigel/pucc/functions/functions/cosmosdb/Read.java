package com.sigel.pucc.functions.functions.cosmosdb;
/* package com.sigel.pucc.functions.cosmosdb;

import com.azure.cosmos.CosmosAsyncContainer;
import com.azure.cosmos.models.CosmosItemResponse;
import com.azure.cosmos.models.PartitionKey;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import java.util.Optional;

public class Read {

	@FunctionName("read")
	public HttpResponseMessage run(
		@HttpTrigger(
			name = "req",
			methods = { HttpMethod.GET },
			authLevel = AuthorizationLevel.ANONYMOUS,
			route = "cosmos-db/read"
		) HttpRequestMessage<Optional<String>> httpRequestMessage,
		@BindingName("container") String container,
		@BindingName("id") String id,
		final ExecutionContext executionContext
	) {
		executionContext.getLogger().info("Leyendo documento...");

		if (container == null || container.isEmpty()) {
			executionContext.getLogger().warning("No se ha proporcionado un nombre del contenedor.");
			return httpRequestMessage
				.createResponseBuilder(HttpStatus.BAD_REQUEST)
				.body("No se ha proporcionado un nombre del contenedor.")
				.build();
		}

		if (id == null || id.isEmpty()) {
			executionContext.getLogger().warning("No se ha proporcionado un ID para el documento.");
			return httpRequestMessage
				.createResponseBuilder(HttpStatus.BAD_REQUEST)
				.body("No se ha proporcionado un ID para el documento.")
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

		CosmosItemResponse<Object> cosmosItemResponse = cosmosAsyncContainer
			.readItem(id, new PartitionKey(id), Object.class)
			.block();

		if (cosmosItemResponse != null && cosmosItemResponse.getStatusCode() == HttpStatus.OK.value()) {
			executionContext.getLogger().info("Documento leído.");
			return httpRequestMessage.createResponseBuilder(HttpStatus.OK).body(cosmosItemResponse.getItem()).build();
		} else {
			executionContext.getLogger().severe("Error al leer el documento.");
			return httpRequestMessage
				.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Error al leer el documento.")
				.build();
		}
	}
}
 */