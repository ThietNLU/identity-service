package dev.thirvo.identityservice.controller;

import dev.thirvo.identityservice.dto.request.UserCreationRequest;
import dev.thirvo.identityservice.dto.request.UserUpdateRequest;
import dev.thirvo.identityservice.entity.User;
import dev.thirvo.identityservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody UserCreationRequest request) {
        return userService.createUser(request);
    }

    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request){
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUserById(@PathVariable String id) {
        return userService.deleteUserById(id);
    }

}
