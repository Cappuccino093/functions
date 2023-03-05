package com.sigel.pucc.functions.functions.cosmosdb;
/*
package com.sigel.pucc.functions.cosmosdb;

import com.azure.cosmos.CosmosAsyncContainer;
import com.azure.cosmos.models.CosmosContainerResponse;
import com.azure.cosmos.models.CosmosItemResponse;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

public class Create {
	// a
	@FunctionName("create")
	public HttpResponseMessage run(
		@HttpTrigger(
			name = "req",
			methods = { HttpMethod.POST },
			authLevel = AuthorizationLevel.ANONYMOUS,
			route = "cosmos-db/create"
		) HttpRequestMessage<Object> httpRequestMessage,
		@BindingName("collection") String collection,
		final ExecutionContext executionContext
	) {
		executionContext.getLogger().info("Creando documento...");

		if (collection == null || collection.isEmpty()) {
			executionContext.getLogger().warning("No se ha proporcionado un nombre de la colección.");
			return httpRequestMessage
				.createResponseBuilder(HttpStatus.BAD_REQUEST)
				.body("No se ha proporcionado un nombre de la colección.")
				.build();
		}

		if (httpRequestMessage.getBody() == null || httpRequestMessage.getBody().toString().isEmpty()) {
			executionContext.getLogger().warning("No se ha proporcionado un cuerpo para el documento.");
			return httpRequestMessage
				.createResponseBuilder(HttpStatus.BAD_REQUEST)
				.body("No se ha proporcionado un cuerpo para el documento.")
				.build();
		}

		String[] containerPath = collection.split("/");
		CosmosAsyncContainer cosmosAsyncContainer = CosmosDb.cosmosAsyncDatabase.getContainer(containerPath[0]);
		for (int i = 1; i < containerPath.length; i++) {
			cosmosAsyncContainer = cosmosAsyncContainer.getDatabase().getContainer(containerPath[i]);
		}

		CosmosContainerResponse cosmosContainerResponse = cosmosAsyncContainer.read().block();

		if (cosmosContainerResponse != null && cosmosContainerResponse.getStatusCode() != HttpStatus.OK.value()) {
			executionContext.getLogger().info("La colección " + containerPath[0] + " no existe. Creando...");
			CosmosContainerResponse createdCosmosContainerResponse = CosmosDb.cosmosAsyncDatabase
				.createContainerIfNotExists(cosmosAsyncContainer.getId(), "/" + containerPath[0])
				.block();

			if (
				createdCosmosContainerResponse == null ||
				createdCosmosContainerResponse.getStatusCode() != HttpStatus.CREATED.value()
			) {
				executionContext.getLogger().severe("Error al crear la colección " + containerPath[0] + ".");
				return httpRequestMessage
					.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al crear la colección " + containerPath[0] + ".")
					.build();
			}

			CosmosAsyncContainer subContainer = CosmosDb.cosmosAsyncDatabase.getContainer(containerPath[0]);

			for (int i = 1; i < containerPath.length; i++) {
				CosmosAsyncContainer tempContainer = subContainer.getDatabase().getContainer(containerPath[i]);
				CosmosContainerResponse tempContainerResponse = tempContainer.read().block();
				if (tempContainerResponse == null || tempContainerResponse.getStatusCode() != HttpStatus.OK.value()) {
					executionContext.getLogger().info("La colección " + containerPath[i - 1] + " no existe. Creando...");
					CosmosContainerResponse createdCosmosSubContainerResponse = subContainer
						.getDatabase()
						.createContainerIfNotExists(tempContainer.getId(), "/" + containerPath[i - 1])
						.block();
					if (
						createdCosmosSubContainerResponse == null ||
						createdCosmosSubContainerResponse.getStatusCode() != HttpStatus.CREATED.value()
					) {
						executionContext.getLogger().severe("Error al crear la colección " + containerPath[i - 1] + ".");
						return httpRequestMessage
							.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
							.body("Error al crear la colección " + containerPath[i - 1] + ".")
							.build();
					}
				}
				subContainer = tempContainer;
			}
		}

		CosmosItemResponse<Object> cosmosItemResponse = cosmosAsyncContainer
			.createItem(httpRequestMessage.getBody())
			.block();

		if (cosmosItemResponse != null && cosmosItemResponse.getStatusCode() == HttpStatus.CREATED.value()) {
			executionContext.getLogger().info("Documento creado.");
			return httpRequestMessage.createResponseBuilder(HttpStatus.CREATED).body(cosmosItemResponse.getItem()).build();
		} else {
			executionContext.getLogger().severe("Error al crear el documento.");
			return httpRequestMessage
				.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Error al crear el documento.")
				.build();
		}
	}
}
*/