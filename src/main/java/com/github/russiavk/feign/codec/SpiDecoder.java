package com.github.russiavk.feign.codec;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Optional;

import com.github.russiavk.AbstractSPIResolver;

import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;


public class SpiDecoder extends AbstractSPIResolver<Decoder> implements Decoder {
	private static final SpiDecoder INSTANCE;
	private static final Decoder DECODER;
	static {
		INSTANCE = new SpiDecoder();
		DECODER = Optional.ofNullable(INSTANCE.resolve()).orElseGet(Decoder.Default::new);
	}

	@Override
	public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
		return DECODER.decode(response, type);
	}

}
