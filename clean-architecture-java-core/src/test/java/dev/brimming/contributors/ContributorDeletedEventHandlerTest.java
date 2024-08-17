package dev.brimming.contributors;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import dev.brimming.interfaces.EmailSender;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ContributorDeletedEventHandlerTest {

  private static class TestEmailSender implements EmailSender {

    @Override
    public CompletableFuture<Void> sendEmailAsync(String to, String from, String subject,
        String body) {

      return CompletableFuture.completedFuture(null);
    }
  }

  @Test
  public void itSendsEmailWithCorrectParameters() {
    TestEmailSender emailSenderSpy = spy(new TestEmailSender());
    ContributorDeletedEvent contributorDeletedEvent = ContributorDeletedEvent.of(1);
    ContributorDeletedEventHandler eventHandler = new ContributorDeletedEventHandler(
        emailSenderSpy);

    eventHandler.handle(contributorDeletedEvent);

    verify(emailSenderSpy).sendEmailAsync("to@test.com", "from@test.com", "Contributor Deleted",
        String.format("Contributor with id %s was deleted.",
            contributorDeletedEvent.getContributorId()));
  }
}