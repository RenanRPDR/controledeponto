package br.com.controledeponto.batidas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatidaService {

	@Autowired
	private BatidaRepository batidaRepository;

	public Batida salvar(BatidaDTO batidaDTO) {

		LocalDateTime dataHoraConvertida = LocalDateTime.parse(batidaDTO.getDataHora(),
				DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		Batida batida = new Batida(dataHoraConvertida);

		batidaRepository.save(batida);
		return batida;
	}

	public void listar() {
		batidaRepository.findAll();

	}
}
