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
import br.com.controledeponto.batidas.exceptions.CampoNaoInformadoException;
import br.com.controledeponto.batidas.exceptions.FinalDeSemanaException;
import br.com.controledeponto.batidas.exceptions.FormatoDeDataInvalidoException;

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
	
//	@Test(expected = TempoMinimoDeAlmocoException.class)
//	public void deveLancarExcecao_IntervaloAlmocoInvalido() throws Exception {
//		BatidaDTO batidaDTO = new BatidaDTO();
//		batidaDTO.setId(1L);
//		batidaDTO.setDataHora("2022-04-08T08:00:00");
//		batidaService.salvar(batidaDTO);
//		
//		BatidaDTO batidaDoisDTO = new BatidaDTO();
//		batidaDoisDTO.setId(2L);
//		batidaDoisDTO.setDataHora("2022-04-08T12:00:00");
//		batidaService.salvar(batidaDoisDTO);
//		
//		BatidaDTO batidaTresDTO = new BatidaDTO();
//		batidaTresDTO.setId(3L);
//		batidaTresDTO.setDataHora("2022-04-08T12:37:00");
//		batidaService.salvar(batidaTresDTO);
//	}
}
