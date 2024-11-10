package dev.brimming.helpers;

import dev.brimming.helpers.mediator.CommandHandler;
import java.util.concurrent.CompletableFuture;

public class TestCommandHandler implements CommandHandler<TestCommand, String> {

  @Override
  public CompletableFuture<String> handle(TestCommand command) {
    return CompletableFuture.completedFuture("Hello World");
  }
}
