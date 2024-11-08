package dev.brimming.helpers;

import java.util.concurrent.CompletableFuture;

public interface RequestHandler<T extends Request<V>, V> {

  CompletableFuture<V> handle(T request);
}
