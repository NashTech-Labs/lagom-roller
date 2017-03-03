package edu.knoldus.hello.impl;

import akka.Done;
import com.lightbend.lagom.javadsl.persistence.AggregateEventTag;
import com.lightbend.lagom.javadsl.persistence.Offset;

import java.util.concurrent.CompletionStage;

public interface MyDatabase {
  /**
   * Create the tables needed for this read side if not already created.
   */
  CompletionStage<Done> createTables();

  /**
   * Load the offset of the last event processed.
   */
  CompletionStage<Offset> loadOffset(AggregateEventTag<HelloEvent> tag);

  /**
   * Handle the post added event.
   */
  CompletionStage<Done> handleEvent(HelloEvent event, Offset offset);
}