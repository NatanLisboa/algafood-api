package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.filter.DailySaleFilter;
import com.lisboaworks.algafood.domain.model.statistics.DailySale;

import java.util.List;

public interface SaleQueryService {

    List<DailySale> getDailySales(DailySaleFilter filter, String timeOffset);

}
