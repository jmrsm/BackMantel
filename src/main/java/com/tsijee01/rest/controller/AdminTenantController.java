package com.tsijee01.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsijee01.rest.dto.ActorDTO;
import com.tsijee01.service.AdminTenantService;

@RestController
public class AdminTenantController {
	@Autowired
	AdminTenantService adminService;
	//obtener id admin
	@RequestMapping(path = "api/admin/getID", method = RequestMethod.GET)
	public ResponseEntity<?> listarActores(HttpServletRequest request ,@RequestParam(name = "email", required = true) String email) {
		return new ResponseEntity<Object>(adminService.idUser(email), HttpStatus.OK);
	}
}
