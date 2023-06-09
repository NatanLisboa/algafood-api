package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.domain.filter.DailySaleFilter;
import com.lisboaworks.algafood.domain.model.statistics.DailySale;
import com.lisboaworks.algafood.domain.service.SaleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@AllArgsConstructor
public class StatisticsController {

    private final SaleQueryService saleQueryService;

    @GetMapping("/daily-sales")
    public List<DailySale> getDailySales(DailySaleFilter filter) {
        return saleQueryService.getDailySales(filter);
    }

}
