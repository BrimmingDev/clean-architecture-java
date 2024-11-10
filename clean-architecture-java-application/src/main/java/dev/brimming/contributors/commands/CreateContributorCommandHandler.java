package dev.brimming.contributors.commands;

import com.google.inject.Inject;
import dev.brimming.contributors.Contributor;
import dev.brimming.contributors.ContributorRepository;
import dev.brimming.contributors.ImmutableContributor;
import dev.brimming.contributors.ImmutablePhoneNumber;
import dev.brimming.helpers.mediator.CommandHandler;
import dev.brimming.utils.result.Result;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class CreateContributorCommandHandler implements
    CommandHandler<ImmutableCreateContributorCommand, Result<Integer>> {

  private final ContributorRepository contributorRepository;

  @Inject
  public CreateContributorCommandHandler(ContributorRepository contributorRepository) {
    this.contributorRepository = contributorRepository;
  }

  @Override
  public CompletableFuture<Result<Integer>> handle(ImmutableCreateContributorCommand command) {
    ImmutableContributor newContributor = ImmutableContributor.of(command.getName(),
        command.getPhoneNumber()
            .map(phoneNumber -> ImmutablePhoneNumber.of("", phoneNumber, Optional.empty()))
    );

    Contributor contributor = contributorRepository.create(newContributor);

    return CompletableFuture.completedFuture(Result.created(contributor.getId()));
  }
}
