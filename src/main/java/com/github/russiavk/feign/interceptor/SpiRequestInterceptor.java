package com.github.russiavk.feign.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.russiavk.AbstractSPIResolver;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class SpiRequestInterceptor extends AbstractSPIResolver<RequestInterceptor> implements RequestInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpiRequestInterceptor.class);
	private static final SpiRequestInterceptor INSTANCE = new SpiRequestInterceptor();
	private static final RequestInterceptor INTERCEPTOR = INSTANCE.resolve();

	@Override
	public void apply(RequestTemplate template) {
		if (INTERCEPTOR != null) {
			INTERCEPTOR.apply(template);
		} else {
			LOGGER.warn("requestInterceptor not spefic!");
		}
	}
}
