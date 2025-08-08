package dev.thirvo.identityservice.controller;

import dev.thirvo.identityservice.dto.request.ApiResponse;
import dev.thirvo.identityservice.dto.request.UserCreationRequest;
import dev.thirvo.identityservice.dto.request.UserUpdateRequest;
import dev.thirvo.identityservice.entity.User;
import dev.thirvo.identityservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));

        return apiResponse;
    }

    @PostMapping("/addAll")
    public List<ApiResponse<User>> createUser(@RequestBody @Valid List<UserCreationRequest> request) {
        List<ApiResponse<User>> apiResponses = request.stream()
                .map(userCreationRequest -> {
                    ApiResponse<User> apiResponse = new ApiResponse<>();
                    apiResponse.setResult(userService.createUser(userCreationRequest));
                    return apiResponse;
                })
                .toList();
        return apiResponses;
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
    public User updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUserById(@PathVariable String id) {
        return userService.deleteUserById(id);
    }

    @DeleteMapping("/delAll")
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

}
