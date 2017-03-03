package edu.knoldus.hello.impl;

import akka.Done;
import com.lightbend.lagom.javadsl.persistence.AggregateEventTag;
import com.lightbend.lagom.javadsl.persistence.Offset;

import java.util.concurrent.CompletionStage;

/**
 * Created by vikas on 03/03/17.
 */
public class DummyDBImpl implements MyDatabase {
    @Override
    public CompletionStage<Done> createTables() {
        System.out.println("Create Tables called");
        return (CompletionStage<Done>) Done.getInstance();
    }

    @Override
    public CompletionStage<Offset> loadOffset(AggregateEventTag<HelloEvent> tag) {
        System.out.println("Load offset called");
        return null;
    }

    @Override
    public CompletionStage<Done> handleEvent(HelloEvent event, Offset offset) {
        System.out.println("Handle event called");
        return null;
    }
}
