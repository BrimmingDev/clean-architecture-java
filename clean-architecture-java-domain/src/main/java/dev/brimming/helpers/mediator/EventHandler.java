package dev.brimming.helpers.mediator;

import java.util.concurrent.CompletableFuture;

public interface EventHandler<E extends Event> {

  CompletableFuture<Void> handle(E event);
}