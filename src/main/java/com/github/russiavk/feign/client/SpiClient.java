package com.github.russiavk.feign.client;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.github.russiavk.AbstractSPIResolver;

import feign.AsyncClient;
import feign.Request;
import feign.Request.Options;
import feign.Response;

public class SpiClient extends AbstractSPIResolver<AsyncClient<Object>> implements AsyncClient<Object> {
	private static SpiClient INSTANCE= new SpiClient();
	private static final AsyncClient<Object> CLIENT = INSTANCE.resolve();
 
	@Override
	public CompletableFuture<Response> execute(Request request, Options options, Optional<Object> requestContext) {
		return CLIENT.execute(request, options, requestContext);
	}

}
