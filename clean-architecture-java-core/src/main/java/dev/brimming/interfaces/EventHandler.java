package dev.brimming.interfaces;

import dev.brimming.baseclasses.AbstractBaseDomainEvent;
import java.util.concurrent.CompletableFuture;

public interface EventHandler<E extends AbstractBaseDomainEvent> {

  CompletableFuture<Void> handle(E event);
}