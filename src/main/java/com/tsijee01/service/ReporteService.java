package com.tsijee01.service;

import com.tsijee01.rest.dto.ReporteAdminDTO;
import com.tsijee01.rest.dto.ReporteSuperAdminDTO;
import com.tsijee01.rest.dto.ReporteUsuarioDTO;

public interface ReporteService {

	ReporteSuperAdminDTO obtenerReporteSuperAdmin();

	ReporteAdminDTO obtenerReporteAdmin(Long id);

	ReporteUsuarioDTO obtenerReporteUsuario(String mailUsuario);

}
