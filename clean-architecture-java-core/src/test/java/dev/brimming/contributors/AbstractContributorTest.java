package dev.brimming.contributors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

class AbstractContributorTest {

  @Test
  public void itDoesNotAllowEmptyName() {
    assertThrows(IllegalArgumentException.class,
        () -> Instancio.create(Contributor.class).withName(""));
  }

  @Test
  public void itDefaultsContributorStatusToNotSet() {
    Contributor contributor = Contributor.builder().setId(1).setName("Test").build();

    assertThat(contributor.getStatus()).isEqualTo(ContributorStatus.NOT_SET);
  }
}