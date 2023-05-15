package cn.geekcity.xiot.graalvm.example.helloworld;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class HelloWorldVerticle extends AbstractVerticle {

    public static void main(String[] args) {
        System.out.println("main");


        Vertx.vertx().deployVerticle(new HelloWorldVerticle())
                .onSuccess(x -> {
                    System.out.println("deploy HelloWorldVerticle onSuccess!");
                })
                .onFailure(e -> {
                    System.out.println("deploy HelloWorldVerticle onFailure: " + e.getMessage());
                });
    }

    @Override
    public void start() {
        System.out.println("start");
        vertx.createHttpServer().requestHandler(req -> {
            req.response().putHeader("content-type", "text/plain").end("Hello from Vert.x!");
        }).listen(8080, listen -> {
            if (listen.succeeded()) {
                System.out.println("Server listening on http://localhost:8080/");
            } else {
                listen.cause().printStackTrace();
                System.exit(1);
            }
        });
    }
}
