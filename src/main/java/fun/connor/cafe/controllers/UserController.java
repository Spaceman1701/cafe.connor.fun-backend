package fun.connor.cafe.controllers;


import fun.connor.cafe.domain.User;
import fun.connor.lighter.declarative.*;
import fun.connor.lighter.handler.Response;

@ResourceController("/user")
public class UserController {

    @Get("/{username}")
    public Response<User> getUserByUsername(String username) {

        return null;
    }

    @Post
    public Response<User> createUser(@Body User user) {

        return null;
    }

    @Put
    public Response<User> updateUsername() {
        return null;
    }
}
