package io.archimedesfw.maven.tiles.wiremock.example.delivery.rest.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    Long id;
    String username;
    String name;
    String surename;
}
