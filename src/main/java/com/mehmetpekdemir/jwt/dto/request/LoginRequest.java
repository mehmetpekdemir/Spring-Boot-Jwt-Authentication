package com.mehmetpekdemir.jwt.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author MEHMET PEKDEMIR
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Login Request")
public final class LoginRequest {

    @NotBlank(message = "{backend.constraints.username.NotBlank.message}")
    @Size(min = 3, max = 50, message = "{backend.constraints.username.Size.message}")
    @ApiModelProperty(value = "User name", required = true)
    private String username;

    @NotBlank(message = "{backend.constraints.password.NotBlank.message}")
    @Size(min = 3, max = 32, message = "{backend.constraints.password.Size.message}")
    @ApiModelProperty(value = "Password", required = true)
    private String password;

}