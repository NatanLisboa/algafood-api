package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.domain.filter.DailySaleFilter;
import com.lisboaworks.algafood.domain.model.statistics.DailySale;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.lisboaworks.algafood.api.v1.controller.StatisticsController.StatisticsModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Statistics")
public interface StatisticsControllerOpenApi {

    @Operation(hidden = true)
    StatisticsModel statistics();

    @Operation(summary = "Get statistics about restaurant daily sales", parameters = {
                @Parameter(
                        in = ParameterIn.QUERY,
                        name = "restaurantId",
                        description = "Restaurant id",
                        example = "1",
                        schema = @Schema(type = "integer")
                ),
                @Parameter(
                        in = ParameterIn.QUERY,
                        name = "startCreationDatetime",
                        description = "Initial creation datetime for search filter",
                        example = "2023-01-01T00:00:00Z",
                        schema = @Schema(type = "string", format = "date-time")
                ),
                @Parameter(
                        in = ParameterIn.QUERY,
                        name = "endCreationDatetime",
                        description = "Final creation datetime for search filter",
                        example = "2023-12-31T23:59:59Z",
                        schema = @Schema(type = "string", format = "date-time")
                ),
            }, responses = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(
                                    schema = @Schema(implementation = DailySale.class))
                            ),
                            @Content(mediaType = "application/pdf", schema = @Schema(type = "string", format = "binary"))
            }),
    })
    List<DailySale> getDailySales(@Parameter(hidden = true) DailySaleFilter filter,
                                  @Parameter(description = "Time offset starting from UTC",
                                          schema = @Schema(type = "string", defaultValue = "+00:00")) String timeOffset);

    @Operation(hidden = true)
    ResponseEntity<byte[]> getDailySalesPdf(DailySaleFilter filter, String timeOffset);

}
