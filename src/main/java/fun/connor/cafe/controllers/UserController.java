package fun.connor.cafe.controllers;


import com.google.inject.Inject;
import fun.connor.cafe.domain.User;
import fun.connor.cafe.persistance.UserRepository;
import fun.connor.lighter.declarative.*;
import fun.connor.lighter.handler.Response;

@ResourceController("/user")
public class UserController {

    @Inject
    private UserRepository userRepository;

    @Get("/{username}")
    public Response<User> getUserByUsername(String username) {

        return null;
    }

    @Post
    public Response<User> createUser(@Body User user) {

        return null;
    }

    @Put("/{username}/updateUsername/{username}")
    public Response<User> updateUsername(String username) {
        return null;
    }

    @Delete("/{username}")
    public Response<Void> deleteUser() {
        return null;
    }
}
