package com.tsijee01.util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class Password {
	
	// Defino el BCrypt workload para generar los hashes entre 10-31
	// a mas grande mas seguridad y mas lento a mas chico menos seguridad y más rápido
	private static int workload = 12;

	// para guardar el password hasheado en la base de datos 
	public String hasherPassword(String passwordPlano) {
		String salt = BCrypt.gensalt(workload);
		String passwordHash = BCrypt.hashpw(passwordPlano, salt);
		return (passwordHash);
	}


	// para comparar el password hasheado en la base de datos contra el texto plano
	public boolean checkearPassword(String passwordPlano, String passwordHash) {
		boolean password_verified = false;

		if (null == passwordHash || !passwordHash.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

		password_verified = BCrypt.checkpw(passwordPlano, passwordHash);

		return (password_verified);
	}
}
