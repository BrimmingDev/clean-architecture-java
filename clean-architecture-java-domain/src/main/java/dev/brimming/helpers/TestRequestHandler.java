package dev.brimming.helpers;

import java.util.concurrent.CompletableFuture;

public class TestRequestHandler implements RequestHandler<TestRequest, String> {

  @Override
  public CompletableFuture<String> handle(TestRequest request) {
    return CompletableFuture.completedFuture("Hello World");
  }
}
