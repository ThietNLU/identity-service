package dev.thirvo.identityservice.controller;

import com.nimbusds.jose.JOSEException;
import dev.thirvo.identityservice.dto.request.ApiResponse;
import dev.thirvo.identityservice.dto.request.AuthenticationRequest;
import dev.thirvo.identityservice.dto.request.InstrospectRequest;
import dev.thirvo.identityservice.dto.response.AuthenticationResponse;
import dev.thirvo.identityservice.dto.response.InstrospectResponse;
import dev.thirvo.identityservice.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping(value = "/token")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping(value = "/instrospect")
    public ApiResponse<InstrospectResponse> authenticate(@RequestBody InstrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.instrospect(request);
        return ApiResponse.<InstrospectResponse>builder()
                .result(result)
                .build();
    }
}
