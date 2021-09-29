package io.archimedesfw.maven.tiles.postgresql.example.domain.persistence;

import io.archimedesfw.maven.tiles.postgresql.example.domain.User;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
class UserRepositoryIT {

    private final static User USER_NEW = new User(3L, "ffernandez", "Federico", "Fernandez");
    private final static User USER_TEST1 = new User(1L, "jdoe", "John", "Doe");
    private final static User USER_TEST2 = new User(2L, "asmith", "Addam", "Smith");

    @Inject
    UserRepository userRepositorySUT;

    @Test
    void shouldSaveUser() {

        var result = userRepositorySUT.save(USER_NEW);

        assertThat(result).isEqualTo(USER_NEW);
    }

    @Test
    void shouldFindUserById() {
        var result = userRepositorySUT.findById(1L);

        assertThat(result)
                .isPresent()
                .get().isEqualTo(USER_TEST1);
    }

    @Test
    void shouldFindAllUsers() {
        var result = userRepositorySUT.findAll();

        assertThat(result)
                .isNotEmpty()
                .contains(USER_TEST1, USER_TEST2);
    }

    @Test
    void shouldUpdateExistingUser() {
        User userTest01Updated = new User(1L, "jdoe", "Jossua", "Doe");
        userRepositorySUT.update(userTest01Updated);

        var result = userRepositorySUT.findById(1L);

        assertThat(result)
                .isPresent()
                .get().isEqualTo(userTest01Updated);
    }

    @Test
    void shouldDeleteUserById() {
        userRepositorySUT.deleteById(1L);

        var result = userRepositorySUT.findById(1L);
        assertThat(result).isNotPresent();
    }

}