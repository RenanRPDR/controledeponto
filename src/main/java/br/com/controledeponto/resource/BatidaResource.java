package br.com.controledeponto.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batidas")
public class BatidaResource {

	//@RequestMapping(method = RequestMethod.GET)
	
	@GetMapping("/")
	public String helloworld() {
		return "Hello world";
	}

}
