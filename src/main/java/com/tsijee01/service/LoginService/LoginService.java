package com.tsijee01.service.LoginService;

import java.util.Optional;

import com.tsijee01.persistence.model.TipoUsuarioEnum;

public interface LoginService {

	Optional<TipoUsuarioEnum> login (String email, String password);

	Long obtenerId(String email);
}
