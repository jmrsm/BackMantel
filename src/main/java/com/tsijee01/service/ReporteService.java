package com.tsijee01.service;

import com.tsijee01.rest.dto.ReporteAdminDTO;
import com.tsijee01.rest.dto.ReporteSuperAdminDTO;

public interface ReporteService {

	ReporteSuperAdminDTO obtenerReporteSuperAdmin();

	ReporteAdminDTO obtenerReporteAdmin(Long id);

}
