package com.singular.contadorvendas.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.singular.contadorvendas.entity.VendasEntity;
import com.singular.contadorvendas.repository.*;


import jakarta.annotation.PostConstruct;

@Service
public class VendasService {

    @Autowired
    private VendasRepository vendasRepository;

    private List<VendasEntity> vendas;

    @PostConstruct
    public void init() {
        vendas = vendasRepository.findAll();
    }

    public BigDecimal totalOfCompletedSales() {        
        return vendas.stream()
                .filter(vendas -> vendas.getStatus().equals("Concluída"))
                .map(VendasEntity::getValor_venda)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal totalOfCanceledSales() {        
        return vendas.stream()
                .filter(vendas -> vendas.getStatus().equals("Cancelada")) // Filtra as vendas com status "Concluída"
                .map(VendasEntity::getValor_venda)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Optional<VendasEntity> mostRecentCompletedSale() {
        return vendas.stream()
                .filter(venda -> "Concluída".equals(venda.getStatus())) // Filtra as vendas com status "Concluída"
                .max(Comparator.comparing(VendasEntity::getData_venda));
    } 
    
    public long daysBetweenFirstAndLastCancelledSale() {
        Optional<VendasEntity> firstCancelledSale = vendas.stream()
                .filter(venda -> "Cancelada".equals(venda.getStatus())) // Filtra as vendas com status "Cancelada"
                .min(Comparator.comparing(VendasEntity::getData_venda));

        Optional<VendasEntity> lastCancelledSale = vendas.stream()
                .filter(venda -> "Cancelada".equals(venda.getStatus())) // Filtra as vendas com status "Cancelada"
                .max(Comparator.comparing(VendasEntity::getData_venda));

        if (firstCancelledSale.isPresent() && lastCancelledSale.isPresent()) {
            System.out.println("Primeira venda cancelada: " + firstCancelledSale.get());
            System.out.println("Última venda cancelada: " + lastCancelledSale.get());

            LocalDate dataPrimeiraVenda = firstCancelledSale.get().getData_venda();
            LocalDate dataUltimaVenda = lastCancelledSale.get().getData_venda();

            return ChronoUnit.DAYS.between(dataPrimeiraVenda, dataUltimaVenda);
        } else {
            System.out.println("Nenhuma venda cancelada encontrada.");
            return 0L;
        }
    }
}
