package dev.brimming;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.brimming.baseclasses.AbstractBaseDomainEvent;
import dev.brimming.interfaces.EventHandler;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Singleton
public class Mediator {

  private final Map<String, List<EventHandler<? extends AbstractBaseDomainEvent>>> eventHandlers;

  @Inject
  public Mediator(
      Map<String, List<EventHandler<? extends AbstractBaseDomainEvent>>> eventHandlers) {
    this.eventHandlers = eventHandlers;
  }

  public <E extends AbstractBaseDomainEvent> void publish(E event) {
    publishAsync(event).join();
  }


  public <E extends AbstractBaseDomainEvent> CompletableFuture<Void> publishAsync(E event) {
    String eventName = event.getClass().getCanonicalName();
    List<EventHandler<? extends AbstractBaseDomainEvent>> handlers = eventHandlers.get(eventName);

    if (handlers == null) {
      return CompletableFuture.completedFuture(null);
    }

    List<CompletableFuture<Void>> futures = handlers.stream()
        .map(handler -> handleEvent(handler, event))
        .toList();

    return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
  }

  @SuppressWarnings("unchecked")
  private <E extends AbstractBaseDomainEvent> CompletableFuture<Void> handleEvent(
      EventHandler<? extends AbstractBaseDomainEvent> handler, E event) {
    return ((EventHandler<E>) handler).handle(event);
  }
}
