package io.archimedesfw.maven.tiles.applauncher.example.webclient;

import io.archimedesfw.maven.tiles.applauncher.example.delivery.rest.vo.UserResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;

import java.util.List;

import static io.micronaut.http.HttpHeaders.ACCEPT;
import static io.micronaut.http.HttpHeaders.USER_AGENT;

@Client("user-api-client")
@Header(name = USER_AGENT, value = "Micronaut HTTP Client")
@Header(name = ACCEPT, value = "application/json")
public interface UserApiTestClient {
    @Get("/user")
    List<UserResponse> listUsers();
}
