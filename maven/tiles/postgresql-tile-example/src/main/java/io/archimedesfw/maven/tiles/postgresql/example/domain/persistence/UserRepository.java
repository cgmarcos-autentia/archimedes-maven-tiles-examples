package io.archimedesfw.maven.tiles.postgresql.example.domain.persistence;

import java.util.Optional;

import javax.validation.Valid;

import org.jetbrains.annotations.NotNull;

import io.archimedesfw.maven.tiles.postgresql.example.domain.User;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@Repository
@JdbcRepository(dialect = Dialect.POSTGRES)
public interface UserRepository extends CrudRepository<User, Long>{
    @NotNull
    @Override
    <S extends User> S save(@NotNull @Valid @javax.validation.constraints.NotNull S entity);

    @NotNull
    @Override
    <S extends User> S update(@NotNull @Valid @javax.validation.constraints.NotNull S entity);

    @NotNull
    @Override
    Optional<User> findById(@NotNull @javax.validation.constraints.NotNull Long aLong);

    @NotNull
    @Override
    Iterable<User> findAll();

    @Override
    void delete(@NotNull @javax.validation.constraints.NotNull User entity);

}
