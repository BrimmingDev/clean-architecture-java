package dev.brimming;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.brimming.baseclasses.AbstractBaseDomainEvent;
import dev.brimming.interfaces.EventHandler;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MediatorTest {

  @Mock
  private EventHandler<MyEvent> mockHandler1;

  @Mock
  private EventHandler<MyEvent> mockHandler2;

  private Mediator mediator;

  @BeforeEach
  void setUp() {
    Map<String, List<EventHandler<? extends AbstractBaseDomainEvent>>> eventHandlers = new HashMap<>();
    eventHandlers.put(MyEvent.class.getCanonicalName(), Arrays.asList(mockHandler1, mockHandler2));
    mediator = new Mediator(eventHandlers);
  }

  @Test
  void itPassesEventToAllHandlers() {
    MyEvent event = new MyEvent();
    CompletableFuture<Void> future1 = CompletableFuture.completedFuture(null);
    CompletableFuture<Void> future2 = CompletableFuture.completedFuture(null);

    when(mockHandler1.handle(event)).thenReturn(future1);
    when(mockHandler2.handle(event)).thenReturn(future2);

    mediator.publish(event);

    verify(mockHandler1).handle(event);
    verify(mockHandler2).handle(event);
  }

  // Define mock event classes
  private static class MyEvent extends AbstractBaseDomainEvent {

  }
}