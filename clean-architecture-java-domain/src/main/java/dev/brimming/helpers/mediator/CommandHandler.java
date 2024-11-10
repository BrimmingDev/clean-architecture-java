package dev.brimming.helpers.mediator;

import java.util.concurrent.CompletableFuture;

public interface CommandHandler<C extends Command<T>, T> {

  CompletableFuture<T> handle(C command);
}
