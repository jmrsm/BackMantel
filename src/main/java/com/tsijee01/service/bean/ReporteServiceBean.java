package com.tsijee01.service.bean;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsijee01.persistence.model.Contenido;
import com.tsijee01.persistence.model.HistorialContenido;
import com.tsijee01.persistence.model.ProveedorContenido;
import com.tsijee01.persistence.model.Usuario;
import com.tsijee01.persistence.repository.ContenidoRepository;
import com.tsijee01.persistence.repository.HistorialContenidoRepository;
import com.tsijee01.persistence.repository.ProveedorContenidoRepository;
import com.tsijee01.persistence.repository.UsuarioRepository;
import com.tsijee01.rest.dto.HorasVistasPorUnidadDeTiempoDTO;
import com.tsijee01.rest.dto.PorveedorCantidadDTO;
import com.tsijee01.rest.dto.ProveedorCantidadHorasDTO;
import com.tsijee01.rest.dto.ReporteAdminDTO;
import com.tsijee01.rest.dto.ReporteSuperAdminDTO;
import com.tsijee01.service.ReporteService;

@Service
public class ReporteServiceBean implements ReporteService {

	@Autowired
	private HistorialContenidoRepository historialContenidoRepository;

	@Autowired
	private ProveedorContenidoRepository proveedorContenidoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ContenidoRepository contenidoRepository;

	@Override
	public ReporteSuperAdminDTO obtenerReporteSuperAdmin() {

		ReporteSuperAdminDTO ret = new ReporteSuperAdminDTO();

		Stream<HistorialContenido> historial = historialContenidoRepository.stream();

		ret.setCantuUsuarioTotales(usuarioRepository.stream().count());

		ret.setCantuUsuarioHabilitados(usuarioRepository.stream().filter(Usuario::isHabilitado).count());

		ret.setHorasTotalesVisualizadas(
				historial.filter(h -> h.isVisto()).mapToLong(HistorialContenido::getTiempoDeReproduccion).sum() / 360);

		// cantidad de contenido de cada empresa
		Map<ProveedorContenido, Long> proveedorCantContenido = contenidoRepository.stream()
				.collect(Collectors.groupingBy(Contenido::getProveedorContenido, Collectors.counting()));

		List<PorveedorCantidadDTO> proveedorCantidad = new ArrayList<PorveedorCantidadDTO>();
		proveedorCantContenido.keySet().forEach(pc -> {
			PorveedorCantidadDTO pCDTO = new PorveedorCantidadDTO();
			pCDTO.setNombreProceedor(pc.getNombre());
			pCDTO.setCantidadContenido(proveedorCantContenido.get(pc));
			proveedorCantidad.add(pCDTO);
		});
		ret.setProveedorCantidad(proveedorCantidad);

		List<ProveedorCantidadHorasDTO> proveedorCantidadHoras = new ArrayList<ProveedorCantidadHorasDTO>();
		List<ProveedorContenido> proveedores = proveedorContenidoRepository.findAll();
		for (ProveedorContenido proveedor : proveedores) {
			ProveedorCantidadHorasDTO pCHDTO = new ProveedorCantidadHorasDTO();
			pCHDTO.setNombreProveedor(proveedor.getNombre());
			pCHDTO.setCantidadHoras(
					historial.filter(h -> h.getContenido().getProveedorContenido().getId() == proveedor.getId())
							.mapToLong(h -> h.getTiempoDeReproduccion()).sum() / 360);
			proveedorCantidadHoras.add(pCHDTO);
		}

		final Map<String, TemporalAdjuster> ADJUSTERS = new HashMap<>();
		ADJUSTERS.put("day", TemporalAdjusters.ofDateAdjuster(d -> d));
		ADJUSTERS.put("week", TemporalAdjusters.previousOrSame(DayOfWeek.of(1)));
		ADJUSTERS.put("month", TemporalAdjusters.firstDayOfMonth());
		ADJUSTERS.put("year", TemporalAdjusters.firstDayOfYear());

		Map<LocalDate, List<HistorialContenido>> horasVistasPorDia = historial
				.collect(Collectors.groupingBy(h -> h.getFechaReproduccion().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate().with(ADJUSTERS.get(ADJUSTERS.get("day")))));

		List<HorasVistasPorUnidadDeTiempoDTO> horasVistasPorDiaList = new ArrayList<HorasVistasPorUnidadDeTiempoDTO>();
		horasVistasPorDia.keySet().forEach(x -> {
			HorasVistasPorUnidadDeTiempoDTO horasVistasPorUnidad = new HorasVistasPorUnidadDeTiempoDTO();
			horasVistasPorUnidad.setFecha(Date.from(x.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			horasVistasPorUnidad.setHorasVistas(
					horasVistasPorDia.get(x).stream().mapToLong(y -> y.getTiempoDeReproduccion()).sum() / 360);
			horasVistasPorDiaList.add(horasVistasPorUnidad);
		});
		ret.setHorasVistasPorDia(horasVistasPorDiaList);

		Map<LocalDate, List<HistorialContenido>> horasVistasPorSemana = historial
				.collect(Collectors.groupingBy(h -> h.getFechaReproduccion().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate().with(ADJUSTERS.get(ADJUSTERS.get("week")))));

		List<HorasVistasPorUnidadDeTiempoDTO> horasVistasPorSemanaList = new ArrayList<HorasVistasPorUnidadDeTiempoDTO>();
		horasVistasPorSemana.keySet().forEach(x -> {
			HorasVistasPorUnidadDeTiempoDTO horasVistasPorUnidad = new HorasVistasPorUnidadDeTiempoDTO();
			horasVistasPorUnidad.setFecha(Date.from(x.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			horasVistasPorUnidad.setHorasVistas(
					horasVistasPorDia.get(x).stream().mapToLong(y -> y.getTiempoDeReproduccion()).sum() / 360);
			horasVistasPorSemanaList.add(horasVistasPorUnidad);
		});
		ret.setHorasVistasPorMes(horasVistasPorSemanaList);

		Map<LocalDate, List<HistorialContenido>> horasVistasPorMes = historial
				.collect(Collectors.groupingBy(h -> h.getFechaReproduccion().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate().with(ADJUSTERS.get(ADJUSTERS.get("month")))));

		List<HorasVistasPorUnidadDeTiempoDTO> horasVistasPorMesList = new ArrayList<HorasVistasPorUnidadDeTiempoDTO>();
		horasVistasPorMes.keySet().forEach(x -> {
			HorasVistasPorUnidadDeTiempoDTO horasVistasPorUnidad = new HorasVistasPorUnidadDeTiempoDTO();
			horasVistasPorUnidad.setFecha(Date.from(x.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			horasVistasPorUnidad.setHorasVistas(
					horasVistasPorDia.get(x).stream().mapToLong(y -> y.getTiempoDeReproduccion()).sum() / 360);
			horasVistasPorMesList.add(horasVistasPorUnidad);
		});
		ret.setHorasVistasPorMes(horasVistasPorMesList);

		Map<LocalDate, List<HistorialContenido>> horasVistasPorAnio = historial
				.collect(Collectors.groupingBy(h -> h.getFechaReproduccion().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate().with(ADJUSTERS.get(ADJUSTERS.get("year")))));
		List<HorasVistasPorUnidadDeTiempoDTO> horasVistasPorAnioList = new ArrayList<HorasVistasPorUnidadDeTiempoDTO>();
		horasVistasPorAnio.keySet().forEach(x -> {
			HorasVistasPorUnidadDeTiempoDTO horasVistasPorUnidad = new HorasVistasPorUnidadDeTiempoDTO();
			horasVistasPorUnidad.setFecha(Date.from(x.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			horasVistasPorUnidad.setHorasVistas(
					horasVistasPorDia.get(x).stream().mapToLong(y -> y.getTiempoDeReproduccion()).sum() / 360);
			horasVistasPorAnioList.add(horasVistasPorUnidad);
		});
		ret.setHorasVistasPorAnio(horasVistasPorAnioList);
		return ret;

	}

	@Override
	public ReporteAdminDTO obtenerReporteAdmin(Long id) {

		ReporteAdminDTO ret = new ReporteAdminDTO();

		Stream<HistorialContenido> historial = historialContenidoRepository.stream();

		ret.setCantidadVisualizacionesTotales(historial.filter(h -> h.isVisto())
				.filter(h -> h.getContenido().getProveedorContenido().getId() == id).count());

		ret.setHorasTotalesVisualizadas(
				historial.filter(h -> h.isVisto()).filter(h -> h.getContenido().getProveedorContenido().getId() == id)
						.mapToLong(HistorialContenido::getTiempoDeReproduccion).sum() / 360);

		List<ProveedorCantidadHorasDTO> proveedorCantidadHoras = new ArrayList<ProveedorCantidadHorasDTO>();
		List<ProveedorContenido> proveedores = proveedorContenidoRepository.findAll();
		for (ProveedorContenido proveedor : proveedores) {
			ProveedorCantidadHorasDTO pCHDTO = new ProveedorCantidadHorasDTO();
			pCHDTO.setNombreProveedor(proveedor.getNombre());
			pCHDTO.setCantidadHoras(
					historial.filter(h -> h.getContenido().getProveedorContenido().getId() == proveedor.getId())
							.mapToLong(h -> h.getTiempoDeReproduccion()).sum() / 360);
			proveedorCantidadHoras.add(pCHDTO);
		}

		final Map<String, TemporalAdjuster> ADJUSTERS = new HashMap<>();
		ADJUSTERS.put("day", TemporalAdjusters.ofDateAdjuster(d -> d));
		ADJUSTERS.put("week", TemporalAdjusters.previousOrSame(DayOfWeek.of(1)));
		ADJUSTERS.put("month", TemporalAdjusters.firstDayOfMonth());
		ADJUSTERS.put("year", TemporalAdjusters.firstDayOfYear());

		Map<LocalDate, List<HistorialContenido>> horasVistasPorDia = historial
				.collect(Collectors.groupingBy(h -> h.getFechaReproduccion().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate().with(ADJUSTERS.get(ADJUSTERS.get("day")))));

		List<HorasVistasPorUnidadDeTiempoDTO> horasVistasPorDiaList = new ArrayList<HorasVistasPorUnidadDeTiempoDTO>();
		horasVistasPorDia.keySet().forEach(x -> {
			HorasVistasPorUnidadDeTiempoDTO horasVistasPorUnidad = new HorasVistasPorUnidadDeTiempoDTO();
			horasVistasPorUnidad.setFecha(Date.from(x.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			horasVistasPorUnidad.setHorasVistas(
					horasVistasPorDia.get(x).stream().mapToLong(y -> y.getTiempoDeReproduccion()).sum() / 360);
			horasVistasPorDiaList.add(horasVistasPorUnidad);
		});
		ret.setHorasVistasPorDia(horasVistasPorDiaList);

		Map<LocalDate, List<HistorialContenido>> horasVistasPorSemana = historial
				.collect(Collectors.groupingBy(h -> h.getFechaReproduccion().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate().with(ADJUSTERS.get(ADJUSTERS.get("week")))));

		List<HorasVistasPorUnidadDeTiempoDTO> horasVistasPorSemanaList = new ArrayList<HorasVistasPorUnidadDeTiempoDTO>();
		horasVistasPorSemana.keySet().forEach(x -> {
			HorasVistasPorUnidadDeTiempoDTO horasVistasPorUnidad = new HorasVistasPorUnidadDeTiempoDTO();
			horasVistasPorUnidad.setFecha(Date.from(x.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			horasVistasPorUnidad.setHorasVistas(
					horasVistasPorDia.get(x).stream().mapToLong(y -> y.getTiempoDeReproduccion()).sum() / 360);
			horasVistasPorSemanaList.add(horasVistasPorUnidad);
		});
		ret.setHorasVistasPorMes(horasVistasPorSemanaList);

		Map<LocalDate, List<HistorialContenido>> horasVistasPorMes = historial
				.collect(Collectors.groupingBy(h -> h.getFechaReproduccion().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate().with(ADJUSTERS.get(ADJUSTERS.get("month")))));

		List<HorasVistasPorUnidadDeTiempoDTO> horasVistasPorMesList = new ArrayList<HorasVistasPorUnidadDeTiempoDTO>();
		horasVistasPorMes.keySet().forEach(x -> {
			HorasVistasPorUnidadDeTiempoDTO horasVistasPorUnidad = new HorasVistasPorUnidadDeTiempoDTO();
			horasVistasPorUnidad.setFecha(Date.from(x.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			horasVistasPorUnidad.setHorasVistas(
					horasVistasPorDia.get(x).stream().mapToLong(y -> y.getTiempoDeReproduccion()).sum() / 360);
			horasVistasPorMesList.add(horasVistasPorUnidad);
		});
		ret.setHorasVistasPorMes(horasVistasPorMesList);

		Map<LocalDate, List<HistorialContenido>> horasVistasPorAnio = historial
				.collect(Collectors.groupingBy(h -> h.getFechaReproduccion().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate().with(ADJUSTERS.get(ADJUSTERS.get("year")))));
		List<HorasVistasPorUnidadDeTiempoDTO> horasVistasPorAnioList = new ArrayList<HorasVistasPorUnidadDeTiempoDTO>();
		horasVistasPorAnio.keySet().forEach(x -> {
			HorasVistasPorUnidadDeTiempoDTO horasVistasPorUnidad = new HorasVistasPorUnidadDeTiempoDTO();
			horasVistasPorUnidad.setFecha(Date.from(x.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			horasVistasPorUnidad.setHorasVistas(
					horasVistasPorDia.get(x).stream().mapToLong(y -> y.getTiempoDeReproduccion()).sum() / 360);
			horasVistasPorAnioList.add(horasVistasPorUnidad);
		});

		ret.setHorasVistasPorAnio(horasVistasPorAnioList);

		return ret;
	}

}
