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

public class Update {

	@FunctionName("update")
	public HttpResponseMessage run(
		@HttpTrigger(
			name = "req",
			methods = { HttpMethod.PUT },
			authLevel = AuthorizationLevel.ANONYMOUS,
			route = "cosmos-db/update"
		) HttpRequestMessage<Object> httpRequestMessage,
		@BindingName("container") String container,
		@BindingName("id") String id,
		final ExecutionContext executionContext
	) {
		executionContext.getLogger().info("Actualizando documento...");

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

		if (httpRequestMessage.getBody() == null || httpRequestMessage.getBody().toString().isEmpty()) {
			executionContext.getLogger().warning("No se ha proporcionado un cuerpo para el documento.");
			return httpRequestMessage
				.createResponseBuilder(HttpStatus.BAD_REQUEST)
				.body("No se ha proporcionado un cuerpo para el documento.")
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

		if (cosmosItemResponse == null || cosmosItemResponse.getStatusCode() != HttpStatus.OK.value()) {
			executionContext.getLogger().warning("El documento con el ID " + id + " no existe.");
			return httpRequestMessage
				.createResponseBuilder(HttpStatus.NOT_FOUND)
				.body("El documento con el ID " + id + " no existe.")
				.build();
		}

		cosmosItemResponse =
			cosmosAsyncContainer.replaceItem(httpRequestMessage.getBody(), id, new PartitionKey(id)).block();

		if (cosmosItemResponse != null && cosmosItemResponse.getStatusCode() == HttpStatus.OK.value()) {
			executionContext.getLogger().info("Documento actualizado.");
			return httpRequestMessage.createResponseBuilder(HttpStatus.OK).body(cosmosItemResponse.getItem()).build();
		} else {
			executionContext.getLogger().severe("Error al actualizar el documento.");
			return httpRequestMessage
				.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Error al actualizar el documento.")
				.build();
		}
	}
}
 */