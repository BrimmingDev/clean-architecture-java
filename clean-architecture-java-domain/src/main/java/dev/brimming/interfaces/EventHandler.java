package dev.brimming.interfaces;

import dev.brimming.baseclasses.BaseDomainEvent;
import java.util.concurrent.CompletableFuture;

public interface EventHandler<E extends BaseDomainEvent> {

  CompletableFuture<Void> handle(E event);
}