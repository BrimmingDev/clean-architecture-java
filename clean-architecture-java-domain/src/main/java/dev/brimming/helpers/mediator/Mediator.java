package dev.brimming.helpers.mediator;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Singleton
public class Mediator {

  private final Map<String, List<EventHandler<? extends Event>>> eventHandlers;
  private final Map<Class<? extends Command<?>>, CommandHandler<? extends Command<?>, ?>> commandHandlers;

  @Inject
  public Mediator(
      Map<String, List<EventHandler<? extends Event>>> eventHandlers,
      Map<Class<? extends Command<?>>, CommandHandler<? extends Command<?>, ?>> commandHandlers) {
    this.eventHandlers = eventHandlers;
    this.commandHandlers = commandHandlers;
  }

  public <T> T send(Command<T> command) {
    return sendAsync(command).join();
  }

  public <T> CompletableFuture<T> sendAsync(Command<T> command) {
    @SuppressWarnings("unchecked")
    CommandHandler<Command<T>, T> commandHandler = (CommandHandler<Command<T>, T>) commandHandlers.get(
        command.getClass());

    if (commandHandler == null) {
      return CompletableFuture.completedFuture(null);
    }

    return commandHandler.handle(command);
  }

  public <E extends Event> void publish(E event) {
    publishAsync(event).join();
  }


  public <E extends Event> CompletableFuture<Void> publishAsync(E event) {
    String eventName = event.getClass().getCanonicalName();
    List<EventHandler<? extends Event>> handlers = eventHandlers.get(eventName);

    if (handlers == null) {
      return CompletableFuture.completedFuture(null);
    }

    List<CompletableFuture<Void>> futures = handlers.stream()
        .map(handler -> handleEvent(handler, event))
        .toList();

    return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
  }

  @SuppressWarnings("unchecked")
  private <E extends Event> CompletableFuture<Void> handleEvent(
      EventHandler<? extends Event> handler, E event) {
    return ((EventHandler<E>) handler).handle(event);
  }
}
