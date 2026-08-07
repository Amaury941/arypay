package com.arypay.user.dto;

import java.util.UUID;

public record ModelUserDTO(
    UUID id,
    String email
) implements UserDTO{}
