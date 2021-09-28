package io.archimedesfw.maven.tiles.applauncher.example.delivery.rest;

import io.archimedesfw.maven.tiles.applauncher.example.delivery.rest.vo.UserResponse;
import io.archimedesfw.maven.tiles.applauncher.example.webclient.UserApiTestClient;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


@MicronautTest
class UserControllerIT {

    private static final UserResponse USER_001 = new UserResponse(1L, "jdoe", "John", "Doe");
    private static final UserResponse USER_002 = new UserResponse(2L, "ffernandez", "Federico", "Fernandez");

    @Inject
    private UserApiTestClient userApiTestClientSUT;

    @Test
    void shouldListUsers() {

        var response = userApiTestClientSUT.listUsers();

        assertThat(response)
                .isNotEmpty()
                .containsExactly(USER_001, USER_002);

    }


}