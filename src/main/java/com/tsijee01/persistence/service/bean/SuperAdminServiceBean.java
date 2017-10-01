package com.tsijee01.persistence.service.bean;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsijee01.persistence.model.SuperAdmin;
import com.tsijee01.persistence.repository.SuperAdminRepository;
import com.tsijee01.persistence.service.SuperAdminService;

@Service
public class SuperAdminServiceBean implements SuperAdminService {

	@Autowired
	private SuperAdminRepository superAdminRepository;

	public boolean login(String email, String password) {

		Optional<SuperAdmin> sa = superAdminRepository.findOneByEmail(email);
		if (sa.isPresent()) {
			if (sa.get().getPassowd().equals(password)) {
				return true;
			}
		}
		return false;
	}

}
