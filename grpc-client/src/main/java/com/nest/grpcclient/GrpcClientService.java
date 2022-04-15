package com.nest.grpcclient;

import com.nest.proto.generated.hello.HelloServiceGrpc;
import com.nest.proto.generated.hello.HelloWorld;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class GrpcClientService {
    public String sendHelloToServer(String firstName, String lastName) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 28080)
                .usePlaintext()
                .build();
        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);
        HelloWorld.Greeting helloResponse = stub.hello(HelloWorld.Hello.newBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .build());
        try {
            channel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return helloResponse.getGreeting();
    }
}
