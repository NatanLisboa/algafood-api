package com.lisboaworks.algafood.infrastructure.service.report;

import com.lisboaworks.algafood.domain.filter.DailySaleFilter;
import com.lisboaworks.algafood.domain.model.statistics.DailySale;
import com.lisboaworks.algafood.domain.service.SaleQueryService;
import com.lisboaworks.algafood.domain.service.SaleReportService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;

@Service
@AllArgsConstructor
public class PdfSaleReportService implements SaleReportService {

    private final SaleQueryService saleQueryService;

    @Override
    public byte[] issueDailySales(DailySaleFilter filter, String timeOffset) {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/algafood-reports/daily-sales.jasper");
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("REPORT_LOCALE", new Locale(Locale.getDefault().getLanguage(), Locale.getDefault().getCountry()));

            List<DailySale> dailySales = saleQueryService.getDailySales(filter, timeOffset);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dailySales);
            JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new RuntimeException("Unable to issue daily sales report", e);
        }
    }

}
