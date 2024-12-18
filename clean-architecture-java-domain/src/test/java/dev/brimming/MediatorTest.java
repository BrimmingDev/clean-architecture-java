package dev.brimming;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.brimming.helpers.mediator.Event;
import dev.brimming.helpers.mediator.Mediator;
import dev.brimming.helpers.mediator.EventHandler;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
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
    Map<String, List<EventHandler<? extends Event>>> eventHandlers = new HashMap<>();
    eventHandlers.put(MyEvent.class.getCanonicalName(), Arrays.asList(mockHandler1, mockHandler2));
    mediator = new Mediator(eventHandlers, Map.of());
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
  private static class MyEvent implements Event {

  }
}