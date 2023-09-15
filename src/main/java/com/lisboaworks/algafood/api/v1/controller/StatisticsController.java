package com.lisboaworks.algafood.api.v1.controller;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.openapi.controller.StatisticsControllerOpenApi;
import com.lisboaworks.algafood.domain.filter.DailySaleFilter;
import com.lisboaworks.algafood.domain.model.statistics.DailySale;
import com.lisboaworks.algafood.domain.service.SaleQueryService;
import com.lisboaworks.algafood.domain.service.SaleReportService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/statistics")
@AllArgsConstructor
public class StatisticsController implements StatisticsControllerOpenApi {

    private static final String UTC_OFFSET = "+00:00";
    private final SaleQueryService saleQueryService;
    private final SaleReportService saleReportService;
    private final AlgaLinks algaLinks;

    @GetMapping
    public StatisticsModel statistics() {
        StatisticsModel statisticsModel = new StatisticsModel();

        statisticsModel.add(algaLinks.linkToDailySalesStatistics("daily-sales"));

        return statisticsModel;
    }

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

    public static class StatisticsModel extends RepresentationModel<StatisticsModel> {

    }

}
