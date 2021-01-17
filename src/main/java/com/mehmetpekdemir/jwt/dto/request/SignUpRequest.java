package com.mehmetpekdemir.jwt.dto.request;

import com.mehmetpekdemir.jwt.common.validator.UniqueUsername;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
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
@ApiModel(value = "Sign Up Request")
public final class SignUpRequest {

    @NotBlank(message = "{backend.constraints.username.NotBlank.message}")
    @Size(min = 3, max = 50, message = "{backend.constraints.username.Size.message}")
    @UniqueUsername
    @ApiModelProperty(value = "User name", required = true)
    private String username;

    @NotBlank(message = "{backend.constraints.password.NotBlank.message}")
    @Size(min = 3, max = 32, message = "{backend.constraints.password.Size.message}")
    @ApiModelProperty(value = "Password", required = true)
    private String password;

    @NotBlank(message = "{backend.constraints.role.Size.message}")
    @Size(min = 2, max = 32, message = "{backend.constraints.role.Size.message}")
    @ApiModelProperty(value = "Role Name", required = true)
    private String role;

}