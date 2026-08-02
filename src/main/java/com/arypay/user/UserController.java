package com.arypay.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arypay.dto.GenericResponseDTO;
import com.arypay.user.dto.UserDTO;

@RestController
@RequestMapping("users")

public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<UserDTO> listAll() {
        return repository.findAll().stream().map(
            target -> new UserDTO(target.getId(),target.getEmail()))
        .toList();
    }

    @GetMapping("/hello")
    public ResponseEntity<GenericResponseDTO> hello() {
        GenericResponseDTO response = new GenericResponseDTO("Hello!");
        return ResponseEntity.ok(response);
    }


}
