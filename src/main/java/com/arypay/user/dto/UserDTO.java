package com.arypay.user.dto;

public sealed interface UserDTO permits 
ModelUserDTO,
reqNewUserDTO, 
resNewUserDTO 
{}
