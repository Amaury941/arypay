package com.arypay.user.dto;

import com.arypay.user.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record reqNewUserDTO (
    @NotBlank 
    @Email (
        message = "Email inválido"
    )
    String email,

    @NotBlank 
    @Size(
        min = 8, 
        message = "Senha deve ter no mínimo 8 caracteres"
    )
    String password,

    Role role
) implements UserDTO {
    public reqNewUserDTO (String email,String password) {
        this(email,password,Role.COMMON);
    }
}
