package dev.brimming;

import com.google.inject.AbstractModule;

public class CleanArchitectureJavaApplicationModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new CleanArchitectureJavaCoreModule());
  }
}
