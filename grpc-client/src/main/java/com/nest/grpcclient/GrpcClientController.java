package com.nest.grpcclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class GrpcClientController {

    @Autowired
    private final GrpcClientService grpcClientService;

    public GrpcClientController(GrpcClientService grpcClientService) {
        this.grpcClientService = grpcClientService;
    }

    @GetMapping
    public String sendHelloToServer(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
        return this.grpcClientService.sendHelloToServer(firstName, lastName);
    }
}
