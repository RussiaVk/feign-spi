package com.github.russiavk.feign.log;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.LoggerFactory;

import com.github.russiavk.AbstractSPIResolver;

import feign.Logger;

public class SpiLogger extends Logger {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SpiLogger.class);
	private static final LoggerResolver INSTANCE;
	private static final feign.Logger FEIGN_LOGGER;
	private static final File FILE = Paths.get(System.getProperty("user.dir"), "log", "feign.log").toFile();
	private static Method method;
	static {
		INSTANCE = new LoggerResolver();
		FEIGN_LOGGER = INSTANCE.resolve();
		if (!FILE.exists()) {
			FILE.mkdir();
		}
		if (FEIGN_LOGGER instanceof Logger.JavaLogger) {
			((Logger.JavaLogger) FEIGN_LOGGER).appendToFile(FILE.toString());
		}
		try {
			method = feign.Logger.class.getDeclaredMethod("log", String.class, String.class, Object[].class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
		}
	}

	private static final class LoggerResolver extends AbstractSPIResolver<Logger> {
	}

	@Override
	protected void log(String configKey, String format, Object... args) {
		try {
			method.invoke(FEIGN_LOGGER, configKey, format, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {

			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
		}

	}

}
