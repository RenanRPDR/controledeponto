package br.com.controledeponto.batidas;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controledeponto.batidas.exceptions.BatidaRepetidaException;
import br.com.controledeponto.batidas.exceptions.BatidasExcedidasException;
import br.com.controledeponto.batidas.exceptions.CampoNaoInformadoException;
import br.com.controledeponto.batidas.exceptions.FinalDeSemanaException;
import br.com.controledeponto.batidas.exceptions.FormatoDeDataInvalidoException;
import br.com.controledeponto.batidas.exceptions.TempoMinimoDeAlmocoException;

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

		List<Batida> batidasRegistradas = listar();

		for (Batida batidaRegistrada : batidasRegistradas) {

			if (batidaRegistrada.getDataHora().compareTo(batidaDataHora) == 0) {
				throw new BatidaRepetidaException();
			}
		}

		validarIntervaloAlmoco(batidaDataHora, batidasRegistradas);

		return batidaRepository.save(batida);
	}

	public List<Batida> listar() {

		return batidaRepository.findAll();
	}

	private Batida converterHora(BatidaDTO batidaDTO) {

		try {
			LocalDateTime dataHoraConvertida = LocalDateTime.parse(batidaDTO.getDataHora(),
					DateTimeFormatter.ISO_LOCAL_DATE_TIME);
			Batida batida = new Batida(dataHoraConvertida);
			return batida;
		} catch (DateTimeException e) {
			throw new FormatoDeDataInvalidoException();
		}
	}

	private void validarIntervaloAlmoco(LocalDateTime batidaDataHora, List<Batida> batidasRegistradas) {

		for (Batida batidaRegistrada : batidasRegistradas) {

			LocalDate diaBatidaAnterior = batidaRegistrada.getDataHora().toLocalDate();
			LocalDate diaBatidaAtual = batidaDataHora.toLocalDate();

			int horaBatidaAnterior = batidaRegistrada.getDataHora().toLocalTime().getHour();
			int horaBatidaAtual = batidaDataHora.toLocalTime().getHour();

			if ((diaBatidaAnterior.compareTo(diaBatidaAtual) == 0)) {
				if ((batidasRegistradas.size() == 2) && (horaBatidaAtual - horaBatidaAnterior < 1)) {
					throw new TempoMinimoDeAlmocoException();
				}
				if (batidasRegistradas.size() >= 4) {
					throw new BatidasExcedidasException();
				}
			}

		}
	}
}
