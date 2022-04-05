package br.com.controledeponto.batidas;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batidas")
public class BatidaResource {

	@Autowired
	private BatidasRepository batidasRepository; 
	
	// @RequestMapping(method = RequestMethod.GET)

	@GetMapping("/")
	public List<Batidas> listar() {
		return batidasRepository.findAll();
	}

//	@RequestMapping(method = RequestMethod.POST)
//	public void salvar(@RequestBody Batidas batida) {
//		booksRepository.save(book);
//	}
}
