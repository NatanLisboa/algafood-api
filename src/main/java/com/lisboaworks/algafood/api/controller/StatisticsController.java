package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.domain.filter.DailySaleFilter;
import com.lisboaworks.algafood.domain.model.statistics.DailySale;
import com.lisboaworks.algafood.domain.service.SaleQueryService;
import com.lisboaworks.algafood.domain.service.SaleReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@AllArgsConstructor
public class StatisticsController {

    private static final String UTC_OFFSET = "+00:00";
    private final SaleQueryService saleQueryService;
    private final SaleReportService saleReportService;

    @GetMapping(path = "/daily-sales", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DailySale> getDailySales(DailySaleFilter filter, @RequestParam(required = false, defaultValue = UTC_OFFSET) String timeOffset) {
        return saleQueryService.getDailySales(filter, timeOffset);
    }

    @GetMapping(path = "/daily-sales", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getDailySalesPdf(DailySaleFilter filter, @RequestParam(required = false, defaultValue = UTC_OFFSET) String timeOffset) {
        byte[] bytesPdf = saleReportService.issueDailySales(filter, timeOffset);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=daily-sales.pdf");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(bytesPdf);
    }

}
