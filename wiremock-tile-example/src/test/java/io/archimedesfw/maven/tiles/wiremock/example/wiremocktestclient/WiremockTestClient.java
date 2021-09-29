package io.archimedesfw.maven.tiles.wiremock.example.wiremocktestclient;

import io.archimedesfw.maven.tiles.wiremock.example.delivery.rest.vo.UserResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;

import java.util.List;

import static io.micronaut.http.HttpHeaders.ACCEPT;
import static io.micronaut.http.HttpHeaders.USER_AGENT;

@Client("wiremock-test-client")
@Header(name = USER_AGENT, value = "Micronaut HTTP Client")
@Header(name = ACCEPT, value = "application/json")
public interface WiremockTestClient {
    @Get("/user")
    List<UserResponse> listUsers();
}
