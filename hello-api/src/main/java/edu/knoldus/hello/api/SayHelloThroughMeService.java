package edu.knoldus.hello.api;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;

import akka.Done;
import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

public interface SayHelloThroughMeService extends Service {

    ServiceCall<NotUsed, String> sayHelloThroughMe(String id);


    @Override
    default Descriptor descriptor() {
        // @formatter:off
        return named("hello2").withCalls(
          pathCall("/api/sayHelloThroughMeTo/:id",  this::sayHelloThroughMe)
        ).withAutoAcl(true);
        // @formatter:on
    }
}
