package dev.brimming.helpers;

import java.util.concurrent.CompletableFuture;

public interface RequestHandler<R extends Request<T>, T> {

  CompletableFuture<T> handle(R request);
}
