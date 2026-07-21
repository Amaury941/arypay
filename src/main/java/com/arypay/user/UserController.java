package com.arypay.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arypay.user.dto.GenericResponseDTO;

@RestController
@RequestMapping("users")

public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }
    
    @PostMapping("/hello")
    public ResponseEntity<GenericResponseDTO> hello() {
        GenericResponseDTO response = new GenericResponseDTO("Hello!");
        return ResponseEntity.ok(response);
    }


}
