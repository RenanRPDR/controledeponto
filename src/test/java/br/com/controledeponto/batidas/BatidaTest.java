package br.com.controledeponto.batidas;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.controledeponto.ControledepontoApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ControledepontoApplication.class)
public class BatidaTest {

	@Mock
	private BatidaRepository batidaRepository;
	
	@InjectMocks
	private BatidaService batidaService;
	
	@Test
	public void deveSalvarData() throws Exception {
		BatidaDTO batidaDTO = new BatidaDTO();
		batidaDTO.setId(1L);
		batidaDTO.setDataHora("2022-04-08T08:00:00");
		
		when(batidaRepository.save(any(Batida.class))).thenReturn(new Batida());
		Batida batidaRegistrada = batidaService.salvar(batidaDTO);
		
		assertNotNull(batidaRegistrada);
	}
}
