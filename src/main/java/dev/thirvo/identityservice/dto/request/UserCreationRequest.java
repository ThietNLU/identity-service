package dev.thirvo.identityservice.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserCreationRequest {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;


}
