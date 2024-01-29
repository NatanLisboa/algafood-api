package com.lisboaworks.algafood.domain.model.statistics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class DailySale {

    @Schema(example = "2023-07-09", type = "string", format = "date")
    private Date date;

    @Schema(example = "1")
    private Long totalSales;

    @Schema(example = "73.7")
    private BigDecimal totalAmountInvoiced;

}
