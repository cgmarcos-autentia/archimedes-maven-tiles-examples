package io.archimedesfw.maven.tiles.sqlserver.example.domain.persistence;

import io.archimedesfw.maven.tiles.sqlserver.example.domain.User;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Repository
@JdbcRepository(dialect = Dialect.SQL_SERVER)
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findById(@NotNull Long id);

    @NotNull
    List<User> findAll();

    @NotNull
    User save(@NotNull User user);

    @NotNull
    User update(@NotNull User user);

    @NotNull
    void deleteById(@NotNull Long id);
}
