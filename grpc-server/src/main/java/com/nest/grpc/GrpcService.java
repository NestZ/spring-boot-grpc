package com.nest.grpc;

import com.nest.proto.generated.hello.HelloServiceGrpc;
import com.nest.proto.generated.hello.HelloWorld;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class GrpcService extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void hello(HelloWorld.Hello req, StreamObserver<HelloWorld.Greeting> responseObserver) {
        String greeting = String.format("Hello, %s %s.", req.getFirstName(), req.getLastName());
        HelloWorld.Greeting res = HelloWorld.Greeting.newBuilder().setGreeting(greeting).build();
        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }
}
