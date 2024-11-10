package dev.brimming.contributors.services;

import com.google.inject.Inject;
import dev.brimming.contributors.Contributor;
import dev.brimming.contributors.ContributorDto;
import dev.brimming.contributors.ImmutableContributor;
import dev.brimming.contributors.ImmutableContributorDeletedEvent;
import dev.brimming.contributors.PhoneNumber;
import dev.brimming.contributors.ContributorRepository;
import dev.brimming.helpers.mediator.Mediator;
import dev.brimming.utils.result.Result;
import java.util.Optional;

// Example class to show implementation without the command pattern
public class ContributorService {

  private final ContributorRepository repository;
  private final Mediator mediator;

  @Inject
  public ContributorService(ContributorRepository repository, Mediator mediator) {
    this.repository = repository;
    this.mediator = mediator;
  }

  public Result<Integer> CreateContributor(String name, Optional<PhoneNumber> phoneNumber) {
    Contributor newContributor = ImmutableContributor.builder().setName(name)
        .setPhoneNumber(phoneNumber).build();
    ;

    Contributor createdContributor = repository.create(newContributor);

    return Result.created(createdContributor.id());
  }

  public Result<ContributorDto> UpdateContributor(long contributorId, String newName) {
    Contributor existingContributor = repository.getById(contributorId);
    if (existingContributor == null) {
      return Result.notFound();
    }

    ImmutableContributor updatedContributor = ImmutableContributor.copyOf(existingContributor)
        .withName(newName);

    repository.update(updatedContributor);

    return Result.success(new ContributorDto(updatedContributor.id(), updatedContributor.getName(),
        updatedContributor.getPhoneNumber()));
  }

  public Result<?> DeleteContributor(long contributorId) {
    Contributor contributorToDelete = repository.getById(contributorId);

    if (contributorToDelete == null) {
      return Result.notFound();
    }

    repository.delete(contributorToDelete);

    mediator.publish(ImmutableContributorDeletedEvent.of(contributorId));
    return Result.success();
  }
}
