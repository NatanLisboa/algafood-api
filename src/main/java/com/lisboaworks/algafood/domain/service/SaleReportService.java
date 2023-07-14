package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.filter.DailySaleFilter;

public interface SaleReportService {

    byte[] issueDailySales(DailySaleFilter filter, String timeOffset);

}
