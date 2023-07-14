package com.lisboaworks.algafood.domain.model.statistics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class DailySale {

    private Date date;
    private Long totalSales;
    private BigDecimal totalAmountInvoiced;

}
