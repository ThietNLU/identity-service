package dev.thirvo.identityservice.service;

import dev.thirvo.identityservice.dto.request.UserCreationRequest;
import dev.thirvo.identityservice.dto.request.UserUpdateRequest;
import dev.thirvo.identityservice.dto.response.UserResponse;
import dev.thirvo.identityservice.entity.User;
import dev.thirvo.identityservice.exception.AppException;
import dev.thirvo.identityservice.exception.ErrorCode;
import dev.thirvo.identityservice.mapper.UserMapper;
import dev.thirvo.identityservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public User createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);

        return userRepository.save(user);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public UserResponse getUserById(String id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserResponse updateUser(String id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public boolean deleteUserById(String id) {
        userRepository.deleteById(id);
        return true;
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public boolean addAllRequest(List<UserCreationRequest> requests) {
        for (UserCreationRequest request : requests) {
            try {
                createUser(request);
            } catch (RuntimeException e) {
                System.err.println("Error creating user: " + e.getMessage());
                return false;
            }
        }
        return true;
    }
}
