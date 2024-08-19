package dev.brimming.contributors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

class ContributorTest {

  @Test
  public void itDoesNotAllowEmptyName() {
    assertThrows(IllegalArgumentException.class,
        () -> Instancio.create(ImmutableContributor.class).withName(""));
  }

  @Test
  public void itDefaultsContributorStatusToNotSet() {
    Contributor contributor = ImmutableContributor.builder().setId(1).setName("blah").build();
    Contributor blah = Contributor.builder().setId(1).setName("fdf")
        .build();

    assertThat(contributor.status()).isEqualTo(ContributorStatus.NOT_SET);
  }
}