package com.arypay.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arypay.dto.GenericResponseDTO;
import com.arypay.user.dto.NewUserDTO;
import com.arypay.user.dto.UserDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("users")

public class UserController {
    private final UserRepository repository;
    private final UserService service;

    public UserController(UserRepository repository, UserService service) {
        this.repository = repository;
        this.service = service;
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
    
    @PostMapping("/new")
    public ResponseEntity<GenericResponseDTO>handleNew(@Valid @RequestBody NewUserDTO dto ) {
        GenericResponseDTO response = service.create(dto);
        return ResponseEntity.ok(response);
    }

}
