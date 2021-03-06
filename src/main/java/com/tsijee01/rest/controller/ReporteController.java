package com.tsijee01.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsijee01.persistence.model.AdminTenant;
import com.tsijee01.rest.dto.ReporteAdminDTO;
import com.tsijee01.rest.dto.ReporteSuperAdminDTO;
import com.tsijee01.rest.dto.ReporteUsuarioDTO;
import com.tsijee01.service.AdminTenantService;
import com.tsijee01.service.ReporteService;

@RestController
public class ReporteController {

	@Autowired
	ReporteService reporteService;
	
	@Autowired
	AdminTenantService adminTenantService;

	// reporte super admin
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/superAdmin/obtenerReportes", method = RequestMethod.GET)
	public ResponseEntity<?> obtenerReporteSuperAdmin(HttpServletRequest request) {

//		String mailSuperAdmin = (String) request.getSession().getAttribute("SUPER_ADMIN");
//		if (mailSuperAdmin == null) {
//			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
//		}

		ReporteSuperAdminDTO reporte = reporteService.obtenerReporteSuperAdmin();
		return new ResponseEntity<ReporteSuperAdminDTO>(reporte, HttpStatus.OK);
	}

	
	// reporte admin
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/admin/obtenerReportes", method = RequestMethod.GET)
	public ResponseEntity<?> obtenerReporteAdmin(HttpServletRequest request,
			@RequestParam(name = "email", required = true) String email) {

//		String mailAdmin = (String) request.getSession().getAttribute("TENANT_ADMIN");
//		if (mailAdmin  == null) {
//			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
//		}

		AdminTenant at = adminTenantService.ObtenerAdminTenat(email);
		ReporteAdminDTO reporte = reporteService.obtenerReporteAdmin(at.getProveedorContenido().getId());
		return new ResponseEntity<ReporteAdminDTO>(reporte, HttpStatus.OK);
	}
	
	// reporte usuario
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "api/usuario/obtenerReportes", method = RequestMethod.GET)
	public ResponseEntity<?> obtenerReporteUsuario(HttpServletRequest request,
			@RequestParam(name = "email", required = true) String email) {

//		String mailUsuario= (String) request.getSession().getAttribute("USUARIO");
//		if (mailUsuario == null) {
//			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
//		}

		ReporteUsuarioDTO reporte = reporteService.obtenerReporteUsuario(email);
		return new ResponseEntity<ReporteUsuarioDTO>(reporte, HttpStatus.OK);
	}

}
