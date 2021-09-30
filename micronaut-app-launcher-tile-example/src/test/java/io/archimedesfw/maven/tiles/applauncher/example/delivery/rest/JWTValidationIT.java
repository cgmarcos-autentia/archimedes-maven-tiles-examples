package io.archimedesfw.maven.tiles.applauncher.example.delivery.rest;

import io.archimedesfw.maven.tiles.applauncher.example.webclient.UserApiTestClient;
import io.archimedesfw.maven.tiles.applauncher.example.webclient.UserApiTestClientInvalidToken;
import io.archimedesfw.maven.tiles.applauncher.example.webclient.UserApiTestClientNoToken;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@MicronautTest
class JWTValidationIT {
    @Inject
    private UserApiTestClient userApiTestClientSUT;
    @Inject
    private UserApiTestClientNoToken userApiTestClientNoTokenSUT;
    @Inject
    private UserApiTestClientInvalidToken userApiTestClientInvalidTokenSUT;

    @Test
    void shouldListUsersWithValidToken() {

        var response = userApiTestClientSUT.listUsers();

        assertThat(response)
                .isNotEmpty()
                .hasSize(2);
    }

    @Test
    void shouldReturn403WhenTokenIsInvalid() {

        var thrownException = catchThrowable(() -> userApiTestClientInvalidTokenSUT.listUsers());

        Assertions.assertThat(thrownException)
                .isNotNull()
                .isExactlyInstanceOf(HttpClientResponseException.class)
                .hasMessage("Unauthorized");
    }

    @Test
    void shouldReturn403WhenNoTokenIsSended() {

        var thrownException = catchThrowable(() -> userApiTestClientNoTokenSUT.listUsers());

        Assertions.assertThat(thrownException)
                .isNotNull()
                .isExactlyInstanceOf(HttpClientResponseException.class)
                .hasMessage("Unauthorized");


    }

}
