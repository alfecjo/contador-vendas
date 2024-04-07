package com.singular.contadorvendas.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {        
        if (isApplicationHealthy()) {
            return ResponseEntity.ok("Healthy");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unhealthy");
        }
    }

    @GetMapping("/new-health-endpoint")
    public ResponseEntity<String> newHealthCheck() {        
        return healthCheck();
    }

    private boolean isApplicationHealthy() {        
        return true;
    }
}
