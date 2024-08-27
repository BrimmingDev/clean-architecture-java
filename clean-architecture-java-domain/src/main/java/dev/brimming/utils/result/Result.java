package dev.brimming.utils.result;

import dev.brimming.helpers.DefaultStyle;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.immutables.value.Value.Default;
import org.immutables.value.Value.Derived;
import org.immutables.value.Value.Immutable;

@Immutable
@DefaultStyle
public abstract class Result<T> {

  @Default
  public ResultStatus getStatus() {
    return ResultStatus.OK;
  }

  @Default
  public List<String> getErrors() {
    return Collections.emptyList();
  }

  @Default
  public List<ValidationError> getValidationErrors() {
    return Collections.emptyList();
  }

  @Default
  public Optional<String> getSuccessMessage() {
    return Optional.empty();
  }

  @Default
  public Optional<String> getCorrelationId() {
    return Optional.empty();
  }

  @Default
  public Optional<String> getLocation() {
    return Optional.empty();
  }

  public abstract T getValue();

  @Derived
  public boolean isSuccess() {
    return getStatus() == ResultStatus.OK || getStatus() == ResultStatus.CREATED
        || getStatus() == ResultStatus.NO_CONTENT;
  }

  public static <T> Result<T> success() {
    return ImmutableResult.<T>builder().setStatus(ResultStatus.NO_CONTENT).build();
  }

  public static <T> Result<T> success(T value) {
    return ImmutableResult.<T>builder().setValue(value).build();
  }

  public static <T> Result<T> success(T value, Optional<String> successMessage) {
    return ImmutableResult.<T>builder().setValue(value).setSuccessMessage(successMessage).build();
  }

  public static <T> Result<T> created(T value) {
    return ImmutableResult.<T>builder().setValue(value).setStatus(ResultStatus.CREATED).build();
  }

  public static <T> Result<T> created(T value, Optional<String> location) {
    return ImmutableResult.<T>builder().setValue(value).setStatus(ResultStatus.CREATED)
        .setLocation(location)
        .build();
  }

  public static <T> Result<T> error(String errorMessage) {
    return ImmutableResult.<T>builder().setStatus(ResultStatus.ERROR).addErrors(errorMessage)
        .build();
  }

  public static <T> Result<T> error(ErrorList error) {
    return ImmutableResult.<T>builder().setStatus(ResultStatus.ERROR)
        .setCorrelationId(error.getCorrelationId())
        .addAllErrors(error.getErrorMessages()).build();
  }

  public static <T> Result<T> invalid(ValidationError validationError) {
    return ImmutableResult.<T>builder().setStatus(ResultStatus.INVALID)
        .addValidationErrors(validationError)
        .build();
  }

  public static <T> Result<T> invalid(ValidationError... validationErrors) {
    return ImmutableResult.<T>builder().setStatus(ResultStatus.INVALID)
        .addValidationErrors(validationErrors)
        .build();
  }

  public static <T> Result<T> invalid(List<ValidationError> validationErrors) {
    return ImmutableResult.<T>builder().setStatus(ResultStatus.INVALID)
        .addAllValidationErrors(validationErrors)
        .build();
  }

  public static <T> Result<T> notFound() {
    return ImmutableResult.<T>builder().setStatus(ResultStatus.NOT_FOUND).build();
  }

  public static <T> Result<T> notFound(String... errorMessages) {
    return ImmutableResult.<T>builder().setStatus(ResultStatus.NOT_FOUND)
        .addErrors(errorMessages)
        .build();
  }

  public static <T> Result<T> forbidden() {
    return ImmutableResult.<T>builder().setStatus(ResultStatus.FORBIDDEN).build();
  }

  public static <T> Result<T> unauthorized() {
    return ImmutableResult.<T>builder().setStatus(ResultStatus.UNAUTHORIZED).build();
  }

  public static <T> Result<T> conflict() {
    return ImmutableResult.<T>builder().setStatus(ResultStatus.CONFLICT).build();
  }

  public static <T> Result<T> conflict(String... errorMessages) {
    return ImmutableResult.<T>builder().setStatus(ResultStatus.CONFLICT)
        .addErrors(errorMessages)
        .build();
  }

  public static <T> Result<T> criticalError(String... errorMessages) {
    return ImmutableResult.<T>builder().setStatus(ResultStatus.CRITICAL_ERROR)
        .addErrors(errorMessages)
        .build();
  }

  public static <T> Result<T> unavailable(String... errorMessages) {
    return ImmutableResult.<T>builder().setStatus(ResultStatus.UNAVAILABLE)
        .addErrors(errorMessages)
        .build();
  }

  public static <T> Result<T> noContent() {
    return ImmutableResult.<T>builder().setStatus(ResultStatus.NO_CONTENT).build();
  }
}
