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
  private final Map<Class<? extends Request<?>>, RequestHandler<? extends Request<?>, ?>> requestHandlers;

  @Inject
  public Mediator(
      Map<String, List<EventHandler<? extends BaseDomainEvent>>> eventHandlers,
      Map<Class<? extends Request<?>>, RequestHandler<? extends Request<?>, ?>> requestHandlers) {
    this.eventHandlers = eventHandlers;
    this.requestHandlers = requestHandlers;
  }

  public <T> T send(Request<T> request) {
    return sendAsync(request).join();
  }

  public <T> CompletableFuture<T> sendAsync(Request<T> request) {
    @SuppressWarnings("unchecked")
    RequestHandler<Request<T>, T> requestHandler = (RequestHandler<Request<T>, T>) requestHandlers.get(
        request.getClass());

    if (requestHandler == null) {
      return CompletableFuture.completedFuture(null);
    }

    return requestHandler.handle(request);
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

  private <T> RequestHandler<Request<T>, T> getRequestHandler(
      Class<? extends Request<T>> requestClass) {
    @SuppressWarnings("unchecked")
    RequestHandler<Request<T>, T> handler = (RequestHandler<Request<T>, T>) requestHandlers.get(
        requestClass);
    return handler;
  }
}
