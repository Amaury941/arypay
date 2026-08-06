package com.arypay.user.dto;

import java.util.UUID;

public record UserDTO(
    UUID id,
    String email
) {}
