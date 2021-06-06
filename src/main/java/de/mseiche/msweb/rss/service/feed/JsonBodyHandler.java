package de.mseiche.msweb.rss.service.feed;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.http.HttpResponse;
import java.util.function.Supplier;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonBodyHandler<W> implements HttpResponse.BodyHandler<Supplier<W>> {

	private final Class<W> forClass;
	private final ObjectMapper objectMapper;

	public JsonBodyHandler(final ObjectMapper objectMapper, final Class<W> forClass) {
		this.objectMapper = objectMapper;
		this.forClass = forClass;
	}

	@Override
	public HttpResponse.BodySubscriber<Supplier<W>> apply(final HttpResponse.ResponseInfo responseInfo) {
		return HttpResponse.BodySubscribers.mapping(HttpResponse.BodySubscribers.ofInputStream(),
				inputStream -> toSupplierForClass(inputStream));
	}

	private Supplier<W> toSupplierForClass(final InputStream inputStream) {
		return () -> {
			try (final InputStream stream = inputStream) {
				return objectMapper.readValue(stream, forClass);
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}
		};
	}
}