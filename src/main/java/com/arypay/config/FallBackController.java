package com.arypay.config;

import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arypay.user.dto.GenericResponseDTO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class FallBackController implements ErrorController{
    @RequestMapping("/error")
    public ResponseEntity<GenericResponseDTO> handleError(HttpServletRequest request) {
        GenericResponseDTO response = new GenericResponseDTO(
            String.format( "ERRO %s: %s",
                request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE),
                request.getAttribute(RequestDispatcher.ERROR_MESSAGE)
            )    
        );
        return ResponseEntity.ok(response);
    }
}
