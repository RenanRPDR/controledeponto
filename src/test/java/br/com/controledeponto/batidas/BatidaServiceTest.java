package br.com.controledeponto.batidas;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.controledeponto.ControledepontoApplication;
import br.com.controledeponto.batidas.exceptions.BatidaRepetidaException;
import br.com.controledeponto.batidas.exceptions.BatidasExcedidasException;
import br.com.controledeponto.batidas.exceptions.CampoNaoInformadoException;
import br.com.controledeponto.batidas.exceptions.FinalDeSemanaException;
import br.com.controledeponto.batidas.exceptions.FormatoDeDataInvalidoException;
import br.com.controledeponto.batidas.exceptions.TempoMinimoDeAlmocoException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ControledepontoApplication.class)
public class BatidaServiceTest {

	@Mock
	private BatidaRepository batidaRepository;

	@InjectMocks
	private BatidaService batidaService;

	@Test
	public void deveSalvarBatida_Sucesso() throws Exception {
		BatidaDTO batidaDTO = new BatidaDTO();
		batidaDTO.setId(1L);
		batidaDTO.setDataHora("2022-04-08T08:00:00");

		when(batidaRepository.save(any(Batida.class))).thenReturn(new Batida());
		Batida batidaRegistrada = batidaService.salvar(batidaDTO);

		assertNotNull(batidaRegistrada);
	}

	@Test(expected = CampoNaoInformadoException.class)
	public void deveLancarExcecao_DataHoraNulo() throws Exception {
		BatidaDTO batidaDTO = new BatidaDTO();
		batidaDTO.setId(1L);
		batidaDTO.setDataHora(null);
		batidaService.salvar(batidaDTO);
	}
	
	@Test(expected = FormatoDeDataInvalidoException.class)
	public void deveLancarExcecao_FormatoDataHoraInvalido() throws Exception {
		BatidaDTO batidaDTO = new BatidaDTO();
		batidaDTO.setId(1L);
		batidaDTO.setDataHora("2018-08-22");
		batidaService.salvar(batidaDTO);
	}
	
	@Test(expected = FinalDeSemanaException.class)
	public void deveLancarExcecao_FinalDeSemana() throws Exception {
		BatidaDTO batidaDTO = new BatidaDTO();
		batidaDTO.setId(1L);
		batidaDTO.setDataHora("2022-04-10T08:00:00");
		batidaService.salvar(batidaDTO);
	}
	
	@Test(expected = BatidaRepetidaException.class)
	public void deveLancarExcecao_BatidaRepetida() throws Exception {
		Batida batida = new Batida();
		batida.setId(1L);
		batida.setDataHora(LocalDateTime.parse("2022-04-08T08:00:00"));		
		
		List<Batida> batidas = new ArrayList<>();
		batidas.add(batida);
		
		when(batidaService.listar()).thenReturn(batidas);
		
		BatidaDTO batidaDTO = new BatidaDTO();
		batidaDTO.setId(2L);
		batidaDTO.setDataHora("2022-04-08T08:00:00");
		batidaService.salvar(batidaDTO);
	}
	
	@Test(expected = TempoMinimoDeAlmocoException.class)
	public void deveLancarExcecao_IntervaloAlmocoInvalido() throws Exception {
		Batida batida = new Batida();
		batida.setId(1L);
		batida.setDataHora(LocalDateTime.parse("2022-04-08T08:00:00"));		
		
		Batida batidaDois = new Batida();
		batidaDois.setId(1L);
		batidaDois.setDataHora(LocalDateTime.parse("2022-04-08T12:00:00"));	
		
		List<Batida> batidas = new ArrayList<>();
		batidas.add(batida);
		batidas.add(batidaDois);
		
		when(batidaService.listar()).thenReturn(batidas);
		
		BatidaDTO batidaTres = new BatidaDTO();
		batidaTres.setId(2L);
		batidaTres.setDataHora("2022-04-08T12:40:00");
		batidaService.salvar(batidaTres);
	}
	
	@Test(expected = BatidasExcedidasException.class)
	public void deveLancarExcecao_BatidasExcedentes() throws Exception {
		Batida batida = new Batida();
		batida.setId(1L);
		batida.setDataHora(LocalDateTime.parse("2022-04-08T08:00:00"));		
		
		Batida batidaDois = new Batida();
		batidaDois.setId(1L);
		batidaDois.setDataHora(LocalDateTime.parse("2022-04-08T12:00:00"));	
		
		Batida batidaTres = new Batida();
		batidaTres.setId(1L);
		batidaTres.setDataHora(LocalDateTime.parse("2022-04-08T13:30:00"));		
		
		Batida batidaQuatro = new Batida();
		batidaQuatro.setId(1L);
		batidaQuatro.setDataHora(LocalDateTime.parse("2022-04-08T17:30:00"));	
		
		List<Batida> batidas = new ArrayList<>();
		batidas.add(batida);
		batidas.add(batidaDois);
		batidas.add(batidaTres);
		batidas.add(batidaQuatro);
		
		when(batidaService.listar()).thenReturn(batidas);
		
		BatidaDTO batidaExcedente = new BatidaDTO();
		batidaExcedente.setId(1L);
		batidaExcedente.setDataHora("2022-04-08T18:30:00");
		batidaService.salvar(batidaExcedente);
	}
}
