package com.arypay.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arypay.config.exceptions.UserDiscrepancyException;
import com.arypay.dto.GenericResponseDTO;
import com.arypay.user.dto.reqNewUserDTO;
import com.arypay.user.dto.ModelUserDTO;

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
    public List<ModelUserDTO> listAll() {
        return repository.findAll().stream().map(
            target -> new ModelUserDTO(target.getId(),target.getEmail()))
        .toList();
    }

    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        GenericResponseDTO response = new GenericResponseDTO("Hello!");
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/new")
    public ResponseEntity<?>handleNew(@Valid @RequestBody reqNewUserDTO dto ) {
        try {
            return ResponseEntity.ok().body(service.create(dto));
        } catch (UserDiscrepancyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }    
    }

}
