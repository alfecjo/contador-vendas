package com.singular.contadorvendas.controller;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.singular.contadorvendas.entity.VendasEntity;
import com.singular.contadorvendas.service.VendasService;

import org.slf4j.Logger;

@RestController
@RequestMapping("/vendas")
public class VendasController {
    private static final Logger logger = LoggerFactory.getLogger(VendasController.class);

    @Autowired
    private VendasService vendasService;

    @GetMapping("/total-completed-sales")
    public ResponseEntity<String> getTotalOfCompletedSales() {        
            BigDecimal total = vendasService.totalOfCompletedSales();
            logger.info("tst...");
            return ResponseEntity.ok("Total de vendas completadas: "+(toCurrency(total)));            
    }

    @GetMapping("/total-cancelled-sales")
    public ResponseEntity<String> getTotalOfCancelledSales() {        
            BigDecimal total = vendasService.totalOfCanceledSales();
            return ResponseEntity.ok("Total de vendas canceladas: "+(toCurrency(total)));            
    }

    @GetMapping("/most-recent-completed-sale")
    public ResponseEntity<String> mostRecentCompletedSale() {
        Optional<VendasEntity> mostRecentSale = vendasService.mostRecentCompletedSale();

        if (mostRecentSale.isPresent()) {            
            VendasEntity venda = mostRecentSale.get();
            LocalDate dataVenda = venda.getData_venda();
            String vendaInfo = "Data da venda: " + dataVenda.toString() + ", Status: " + venda.getStatus() + ", Valor: " + venda.getValor_venda();
            return ResponseEntity.ok("Detalhes da venda mais recente: " + vendaInfo);
        } else {            
            return ResponseEntity.ok("Nenhuma venda concluída encontrada.");
        }
    }

    @GetMapping("/days-between-first-and-last-cancelled-sale")
    public ResponseEntity<String> daysBetweenFirstAndLastCancelledSale() {
        long days = vendasService.daysBetweenFirstAndLastCancelledSale();
        String message = String.format("Se passaram %d dias entre a primeira e a última venda cancelada", days);
        return ResponseEntity.ok(message);
    }

    private String toCurrency(BigDecimal value) {
        return NumberFormat.getCurrencyInstance().format(value);
    }
}
