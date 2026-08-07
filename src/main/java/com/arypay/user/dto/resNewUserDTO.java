package com.arypay.user.dto;

import java.util.UUID;

public record resNewUserDTO(
    UUID user,
    UUID wallet
) implements UserDTO {}
