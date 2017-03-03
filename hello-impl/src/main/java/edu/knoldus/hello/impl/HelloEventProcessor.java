package edu.knoldus.hello.impl;

import akka.Done;
import akka.japi.Pair;
import akka.stream.javadsl.Flow;
import com.lightbend.lagom.javadsl.persistence.AggregateEventTag;
import com.lightbend.lagom.javadsl.persistence.Offset;
import com.lightbend.lagom.javadsl.persistence.ReadSideProcessor;
import org.pcollections.PSequence;

import java.util.concurrent.CompletionStage;

public class HelloEventProcessor extends ReadSideProcessor<HelloEvent> {

  MyDatabase myDatabase = new DummyDBImpl();

  @Override
  public ReadSideHandler<HelloEvent> buildHandler() {

    return new ReadSideHandler<HelloEvent>() {

      @Override
      public CompletionStage<Done> globalPrepare() {
        return myDatabase.createTables();
      }

      @Override
      public CompletionStage<Offset> prepare(AggregateEventTag<HelloEvent> tag) {
        return myDatabase.loadOffset(tag);
      }

      @Override
      public Flow<Pair<HelloEvent, Offset>, Done, ?> handle() {
        return Flow.<Pair<HelloEvent, Offset>>create()
                .mapAsync(1, eventAndOffset ->
                        myDatabase.handleEvent(eventAndOffset.first(),
                                eventAndOffset.second())
                );
      }
    };
  }
  @Override
  public PSequence<AggregateEventTag<HelloEvent>> aggregateTags() {
    return HelloEvent.TAG.allTags();
  }
}