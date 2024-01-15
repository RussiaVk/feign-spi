package com.github.russiavk.feign.codec;

import java.lang.reflect.Type;
import java.util.Optional;

import com.github.russiavk.AbstractSPIResolver;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;


public class SpiEncoder extends AbstractSPIResolver<Encoder> implements Encoder {
	private static final SpiEncoder INSTANCE;
	private static final Encoder ENCODER;
	static {
		INSTANCE = new SpiEncoder();
		ENCODER = Optional.ofNullable(INSTANCE.resolve()).orElseGet(Encoder.Default::new);
	}

	@Override
	public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
		ENCODER.encode(object, bodyType, template);
	}

}
