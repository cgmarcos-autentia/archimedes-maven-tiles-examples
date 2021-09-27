package io.archimedesfw.maven.tiles.sqlserver.example.domain;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@MappedEntity("users")
public class User {

    @Id
    Long id;

    String username;
    String name;
    String surename;

}
