package com.github.russiavk.feign.contract;

import java.util.List;

import com.github.russiavk.AbstractSPIResolver;

import feign.Contract;
import feign.MethodMetadata;


public class SpiContract extends AbstractSPIResolver<Contract> implements Contract {
	private static final SpiContract INSTANCE= new SpiContract();
	private static final Contract CONTRACT=INSTANCE.resolve();
 

	@Override
	public List<MethodMetadata> parseAndValidateMetadata(Class<?> targetType) {
		return CONTRACT.parseAndValidateMetadata(targetType);
	}

}
