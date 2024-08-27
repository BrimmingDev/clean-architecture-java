package dev.brimming.interfaces;

import java.util.concurrent.CompletableFuture;

public interface EmailSender {

  CompletableFuture<Void> sendEmailAsync(String to, String from, String subject, String body);
}
