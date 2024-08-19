package dev.brimming.contributors;

import java.util.Optional;

public record ContributorDto(int id, String name, Optional<PhoneNumber> phoneNumber) {

}
