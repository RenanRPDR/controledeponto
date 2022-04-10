package br.com.controledeponto.batidas;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controledeponto.exceptions.CampoNaoInformadoException;
import br.com.controledeponto.exceptions.FinalDeSemanaException;

@Service
public class BatidaService {

	@Autowired
	private BatidaRepository batidaRepository;

	public Batida salvar(BatidaDTO batidaDTO) {

		if (batidaDTO.getDataHora() == null) {
			throw new CampoNaoInformadoException();
		}

		Batida batida = converterHora(batidaDTO);
		LocalDateTime batidaDataHora = batida.getDataHora();
		DayOfWeek diaDaSemana = batidaDataHora.getDayOfWeek();
		
		if ((diaDaSemana == DayOfWeek.SATURDAY) || (diaDaSemana == DayOfWeek.SUNDAY)) {
			throw new FinalDeSemanaException();
		}

		
//		if (batidaDataHora == ) {
//		throw new BatidaRepetidaException("Horários já registrado.");
//	}
		
//		if (batidaDataHora >= 60) {
//			
//		}
		return batidaRepository.save(batida);
	}

	public List<Batida> listar() {
		
		return batidaRepository.findAll();
	}

	private Batida converterHora(BatidaDTO batidaDTO) {
		LocalDateTime dataHoraConvertida = LocalDateTime.parse(batidaDTO.getDataHora(),
				DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		Batida batida = new Batida(dataHoraConvertida);
		return batida;
	}
	
//	private Batida consultarUltimaBatida(BatidaDTO batidaDTO) {
//		
//	}
}
