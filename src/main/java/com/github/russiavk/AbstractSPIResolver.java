package com.github.russiavk;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;
import java.util.function.Predicate;

public abstract class AbstractSPIResolver<T> {

	@SuppressWarnings("unchecked")
	protected ServiceLoader<T> getServiceLoader() {
		Type type = ParameterizedType.class.cast(getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		Class<?> clazz;
		if (type instanceof ParameterizedType) {
			clazz = Class.class.cast(ParameterizedType.class.cast(type).getRawType());
		} else {
			clazz = Class.class.cast(type);
		}
		ServiceLoader<T> serviceLoader = (ServiceLoader<T>) ServiceLoader.load(clazz);
		return serviceLoader;
	}

	public Iterator<T> resolveAll() {
		ServiceLoader<T> serviceLoader = getServiceLoader();
		Iterator<T> iterator = serviceLoader.iterator();
		return iterator;
	}

	public T resolve() {
		ServiceLoader<T> serviceLoader = getServiceLoader();
		Optional<T> opt = serviceLoader.findFirst();
		return opt.isPresent() ? opt.get() : null;
	}

	public T resolve(Predicate<? super ServiceLoader.Provider<T>> predicate) {
		ServiceLoader<T> serviceLoader = getServiceLoader();
		Optional<T> opt = serviceLoader.stream().filter(predicate::test).map(Provider::get).findFirst();
		return opt.isPresent() ? opt.get() : null;
	}
}