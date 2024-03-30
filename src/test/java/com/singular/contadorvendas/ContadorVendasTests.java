package com.singular.contadorvendas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.singular.contadorvendas.service.VendasService;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

@SpringBootTest
class ContadorVendasTests {
    
    // mvn test
    @Autowired
    private VendasService vService;

    @Test
    void assertTotalOfCompletedSales() {
        // mvn -Dtest=ContadorVendasTests#assertTotalOfCompletedSales test
        assertThat(vService.totalOfCompletedSales()).isEqualTo(new BigDecimal("62351.31"));
    }

    @Test
    void assertTotalOfCancelledSalesCancelled() {
        // mvn -Dtest=ContadorVendasTests#assertTotalOfCompletedSalesCancelled test                
        assertThat(vService.totalOfCanceledSales()).isEqualTo(new BigDecimal("1893.48"));
    }
}