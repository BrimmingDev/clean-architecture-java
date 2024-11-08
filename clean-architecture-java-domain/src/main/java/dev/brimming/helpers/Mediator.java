package dev.brimming.helpers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.brimming.baseclasses.BaseDomainEvent;
import dev.brimming.interfaces.EventHandler;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Singleton
public class Mediator {

  private final Map<String, List<EventHandler<? extends BaseDomainEvent>>> eventHandlers;

  @Inject
  public Mediator(
      Map<String, List<EventHandler<? extends BaseDomainEvent>>> eventHandlers) {
    this.eventHandlers = eventHandlers;
  }


  public <E extends BaseDomainEvent> void publish(E event) {
    publishAsync(event).join();
  }


  public <E extends BaseDomainEvent> CompletableFuture<Void> publishAsync(E event) {
    String eventName = event.getClass().getCanonicalName();
    List<EventHandler<? extends BaseDomainEvent>> handlers = eventHandlers.get(eventName);

    if (handlers == null) {
      return CompletableFuture.completedFuture(null);
    }

    List<CompletableFuture<Void>> futures = handlers.stream()
        .map(handler -> handleEvent(handler, event))
        .toList();

    return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
  }

  @SuppressWarnings("unchecked")
  private <E extends BaseDomainEvent> CompletableFuture<Void> handleEvent(
      EventHandler<? extends BaseDomainEvent> handler, E event) {
    return ((EventHandler<E>) handler).handle(event);
  }
}
