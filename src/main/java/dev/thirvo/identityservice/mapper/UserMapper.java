package dev.thirvo.identityservice.mapper;

import dev.thirvo.identityservice.dto.request.UserCreationRequest;
import dev.thirvo.identityservice.dto.request.UserUpdateRequest;
import dev.thirvo.identityservice.dto.response.UserResponse;
import dev.thirvo.identityservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public User toUser(UserCreationRequest request);

    public UserResponse toUserResponse(User user);

    public void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
