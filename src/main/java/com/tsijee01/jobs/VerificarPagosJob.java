package com.tsijee01.jobs;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tsijee01.persistence.model.TipoPagoEnum;
import com.tsijee01.persistence.model.Usuario;
import com.tsijee01.persistence.repository.UsuarioRepository;
import com.tsijee01.service.UsuarioService;

@Component
public class VerificarPagosJob {

//	@Autowired
//	private UsuarioService usuarioService;
//
//	@Autowired
//	private UsuarioRepository usuarioRepository;
//
//	// si fija si están al día con sus pagos y sino los deshabilita para que no
//	// puedan seguir viendo contenido
//	// @PostConstruct la forma más fácil de probarlo
//	@Scheduled(cron = "${verificarPagos.cron}")
//	public void verificarPagos() {
//
//		List<Usuario> usuariosHabilitados = usuarioService.findHabilitadosConUltimoPago();
//		usuariosHabilitados.stream().forEach(u -> {
//
//			if (u.getUltimoPago() == null) {
//				// Esto no debería pasar
//				u.setHabilitado(false);
//				usuarioRepository.save(u);
//			} else {
//				LocalDate fechaUltimoPago = u.getUltimoPago().getFechaPago().toInstant().atZone(ZoneId.systemDefault())
//						.toLocalDate();
//				TipoPagoEnum tipoPago = u.getUltimoPago().getTipoPago();
//				LocalDate ahora = LocalDate.now();
//
//				if (tipoPago == TipoPagoEnum.PAGO_SEMANAL) {
//					ahora.minusWeeks(1);
//
//				} else if (tipoPago == TipoPagoEnum.PAGO_MENSUAL) {
//					ahora.minusMonths(1);
//				} else if (tipoPago == TipoPagoEnum.PAGO_ANUAL) {
//					ahora.minusYears(1);
//				}
//				if (ahora.isBefore(fechaUltimoPago)) {
//					u.setHabilitado(false);
//					usuarioRepository.save(u);
//				}
//
//			}
//		});
//
//	}

	// Faltaría uno parecido que haga los pagos por parte del usuario en caso de
	// que tenga pagos automatizados
}
