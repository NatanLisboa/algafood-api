package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.domain.filter.DailySaleFilter;
import com.lisboaworks.algafood.domain.model.statistics.DailySale;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.lisboaworks.algafood.api.v1.controller.StatisticsController.StatisticsModel;

@SecurityRequirement(name = "security_auth")
public interface StatisticsControllerOpenApi {

    StatisticsModel statistics();

    List<DailySale> getDailySales(DailySaleFilter filter, String timeOffset);

    ResponseEntity<byte[]> getDailySalesPdf(DailySaleFilter filter, String timeOffset);

}
