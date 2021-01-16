package com.mehmetpekdemir.jwt.controller;

import com.mehmetpekdemir.jwt.common.annotation.ApiController;
import com.mehmetpekdemir.jwt.common.constant.SwaggerMessages;
import com.mehmetpekdemir.jwt.dto.request.LoginRequest;
import com.mehmetpekdemir.jwt.dto.response.LoginResponse;
import com.mehmetpekdemir.jwt.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Swagger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author MEHMET PEKDEMIR
 * @since 1.0
 */
@ApiController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "Login")
    @ApiResponses(value = { //
            @ApiResponse(code = 200, message = SwaggerMessages.SUCCESSFUL_LOGIN),
            @ApiResponse(code = 400, message = SwaggerMessages.UNSUCCESSFUL_LOGIN),
            @ApiResponse(code = 403, message = SwaggerMessages.FORBIDDEN),
    })
    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@ApiParam(value = "Login Request", required = true)
                                               @RequestBody LoginRequest loginRequest) {
        final LoginResponse loginResponse = userService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

}
