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

    private boolean isApplicationHealthy() {        
        String[] dbUrls = {
            "jdbc:postgresql://postgres-service.default.svc.cluster.local:5432/master",
            "jdbc:postgresql://postgres:5432/master",
            "jdbc:postgresql://localhost:5432/master"
        };
    
        String username = "postgres";
        String password = "acfa";
    
        for (String dbUrl : dbUrls) {
            try {
                Connection connection = DriverManager.getConnection(dbUrl, username, password);
                connection.close(); // Fecha a conexão
                return true; // Retorna true se a conexão for bem-sucedida
            } catch (SQLException e) {
                continue;
            }
        }
    
        return false;
    }
}
