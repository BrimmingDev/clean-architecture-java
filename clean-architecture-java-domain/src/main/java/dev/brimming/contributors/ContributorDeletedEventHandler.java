package dev.brimming.contributors;

import com.google.inject.Inject;
import dev.brimming.interfaces.EmailSender;
import dev.brimming.helpers.mediator.EventHandler;
import java.util.concurrent.CompletableFuture;

public class ContributorDeletedEventHandler implements EventHandler<ContributorDeletedEvent> {

  private final EmailSender emailSender;

  @Inject
  public ContributorDeletedEventHandler(EmailSender emailSender) {
    this.emailSender = emailSender;
  }

  @Override
  public CompletableFuture<Void> handle(ContributorDeletedEvent event) {
    //TODO Add logger

    emailSender.sendEmailAsync("to@test.com",
        "from@test.com",
        "Contributor Deleted",
        String.format("Contributor with id %s was deleted.", event.contributorId())).join();

    return CompletableFuture.completedFuture(null);
  }
}
