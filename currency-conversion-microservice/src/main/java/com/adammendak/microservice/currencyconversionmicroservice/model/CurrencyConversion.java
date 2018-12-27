package com.adammendak.microservice.currencyconversionmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CurrencyConversion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "from_currency")
    private String from;

    @Column(name = "to_currency")
    private String to;

    @Column(name = "conversion_multiple")
    private BigDecimal conversionMultiple;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "total_calculated_amount")
    private BigDecimal totalCalculatedAmount;

    @Column(name = "port")
    private int port;
}
