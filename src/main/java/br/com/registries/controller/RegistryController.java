package br.com.registries.controller;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.registries.dto.RegistryDto;
import br.com.registries.form.UpdateRegistryForm;
import br.com.registries.model.Registry;
import br.com.registries.repository.RegistryRepository;

//@RestController
@Controller
@RequestMapping("/registry")
public class RegistryController {

	@Autowired
	RegistryRepository registryRepository;

	@GetMapping("/all")
	public List<RegistryDto> getAllRegistries() {
		List<Registry> registries = registryRepository.findAll();
		return RegistryDto.converter(registries);
	}
	
	/*@GetMapping("/actives")
	public List<RegistryDto> getRegistriesActives() {
		List<Registry> registries = registryRepository.findActives();
		return RegistryDto.converter(registries);
	}*/
	
	@GetMapping("/home")
	public String getRegistriesActives(Model model) {
		List<Registry> result = registryRepository.findActives();
		//return RegistryDto.converter(registries);
		List<RegistryDto> registries = RegistryDto.converter(result);
		//Registry registro = new Registry();
		//registro.setName(registries.get(0).getName());		
		//List<Registry> testes = Arrays.asList(registro);
		
		model.addAttribute("registries", registries);
		return "home";
	}

	@GetMapping("/{id}")
	public RegistryDto getRegistriesById(@PathVariable Long id) {
		Registry result = registryRepository.findById(id).get();
		RegistryDto registry = new RegistryDto(result);
		return registry;
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<RegistryDto> updateRegistryById(@PathVariable Long id, @RequestBody UpdateRegistryForm data) {
		Registry registry = registryRepository.findById(id).get();

		if (!data.getName().isEmpty()) {
			registry.setName(data.getName());
		}
		if (!data.getAddress().isEmpty()) {
			registry.setAddress(data.getAddress());
		}
		// registry.setCertificates(data.getCertificates());
		registryRepository.save(registry);
		return ResponseEntity.ok(new RegistryDto(registry));

	}
	
	@PatchMapping("/{id}")
	@Transactional
	public ResponseEntity<?> disable(@PathVariable Long id) {
		registryRepository.alterar(id);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> exclude(@PathVariable Long id) {
		registryRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
