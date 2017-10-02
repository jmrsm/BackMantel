package com.tsijee01.persistence.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.tsijee01.persistence.model.AdminTenant;


@Repository
public interface AdminTenantRepository extends BaseRepository <AdminTenant, Long>{

	Optional<AdminTenant> findByEmail(String email);
	

}
