package cat.itacademy.s4.t1.userapi.controller;

import cat.itacademy.s4.t1.userapi.model.User;
import cat.itacademy.s4.t1.userapi.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public List<User> getUsers(@RequestParam(required = false) String name) {
        if (name == null || name.isBlank()) {
            return userService.getUsers();
        }
        return userService.getUsersByName(name);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody CreateUserRequest userRequest) {
        User user = new User(null, userRequest.name(), userRequest.email());
        return userService.createUser(user);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }
}
