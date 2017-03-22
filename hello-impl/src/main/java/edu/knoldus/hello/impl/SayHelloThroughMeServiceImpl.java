/*
 * Copyright (C) 2016-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package edu.knoldus.hello.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import edu.knoldus.hello.api.HelloService;
import edu.knoldus.hello.api.SayHelloThroughMeService;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

/**
 * Implementation of the HelloService.
 */
public class SayHelloThroughMeServiceImpl implements SayHelloThroughMeService {

    private final HelloService helloService;

    @Inject
    public SayHelloThroughMeServiceImpl(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public ServiceCall<NotUsed, String> sayHelloThroughMe(String id) {
        return msg -> {

                CompletionStage<String> response = helloService.hello(id).invoke(NotUsed.getInstance());

                return response.thenApply(answer -> "Hello service said: " + answer);
            };
    }

}
