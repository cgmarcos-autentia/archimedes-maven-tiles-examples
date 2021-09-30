package io.archimedesfw.maven.tiles.applauncher.example.delivery.rest;

import io.archimedesfw.maven.tiles.applauncher.example.delivery.rest.vo.UserResponse;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.util.List;

@Controller("/user")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class UserController {

    private static final UserResponse USER_001 = new UserResponse(1L, "jdoe", "John", "Doe");
    private static final UserResponse USER_002 = new UserResponse(2L, "ffernandez", "Federico", "Fernandez");

    @Get
    public HttpResponse<List<UserResponse>> findAllUsers(){
        List<UserResponse> result = List.of(USER_001, USER_002);

        return HttpResponse.ok(result);
    }

}
