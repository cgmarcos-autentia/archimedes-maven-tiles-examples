package io.archimedesfw.maven.tiles.applauncher.example.webclient;

import io.archimedesfw.maven.tiles.applauncher.example.delivery.rest.vo.UserResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;

import java.util.List;

import static io.micronaut.http.HttpHeaders.*;

@Client("user-api-client")
@Header(name = USER_AGENT, value = "Micronaut HTTP Client")
@Header(name = ACCEPT, value = "application/json")
@Header(name = AUTHORIZATION, value="Bearer ${test-data.invalid-token}")
public interface UserApiTestClientInvalidToken {
    @Get("/user")
    List<UserResponse> listUsers();
}
