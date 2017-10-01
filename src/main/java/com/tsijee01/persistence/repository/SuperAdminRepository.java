package com.tsijee01.persistence.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tsijee01.persistence.model.SuperAdmin;

@Repository
public interface SuperAdminRepository extends BaseRepository <SuperAdmin, Long>{

	Optional<SuperAdmin> findOneByEmail(String email);

	

}
