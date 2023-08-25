package com.lisboaworks.algafood.api.openapi.controller;

import com.lisboaworks.algafood.domain.filter.DailySaleFilter;
import com.lisboaworks.algafood.domain.model.statistics.DailySale;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.List;

@Api(tags = "Statistics")
public interface StatisticsControllerOpenApi {

    @ApiOperation(value = "Get daily sales statistics", produces = "application/json, application/pdf")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "restaurantId", value = "Id from a restaurant",
                    example = "1", dataType = "int", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "startCreationDatetime", value = "Start creation datetime from order creation",
                    example = "2023-01-01T00:00:00Z", dataType = "date-time", dataTypeClass = OffsetDateTime.class),
            @ApiImplicitParam(name = "endCreationDatetime", value = "End creation datetime from order creation",
                    example = "2023-12-31T23:59:59Z", dataType = "date-time", dataTypeClass = OffsetDateTime.class)
    })
    List<DailySale> getDailySales(DailySaleFilter filter, String timeOffset);

    ResponseEntity<byte[]> getDailySalesPdf(DailySaleFilter filter, String timeOffset);

}
