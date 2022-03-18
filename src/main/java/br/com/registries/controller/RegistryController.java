package br.com.registries.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.registries.dto.RegistryDto;
import br.com.registries.form.NewRegistryForm;
import br.com.registries.form.UpdateRegistryForm;
import br.com.registries.model.Registry;
import br.com.registries.repository.RegistryRepository;

//@RestController
@Controller
@RequestMapping("/registry")
public class RegistryController {

	@Autowired
	RegistryRepository registryRepository;
	
	@GetMapping("/home")
	public String Home() {
		return "home";
	}
	
	@GetMapping("/listar")
	public String getRegistriesActives(Model model) {
		List<Registry> result = registryRepository.findActives();
		List<RegistryDto> registries = RegistryDto.converter(result);		
		model.addAttribute("registries", registries);
		return "listar";
	}
	
	@GetMapping("/formulario")
	public String getForm() {
		return "novo";
	}

	@GetMapping("/all")
	public List<RegistryDto> getAllRegistries() {
		List<Registry> registries = registryRepository.findAll();
		return RegistryDto.converter(registries);
	}	

	@GetMapping("/{id}")
	public RegistryDto getRegistriesById(@PathVariable Long id) {
		Registry result = registryRepository.findById(id).get();
		RegistryDto registry = new RegistryDto(result);
		return registry;
	}
	
	@PostMapping("/novo")
	public String NewRegistry(NewRegistryForm data) {	
		Registry registry = data.convert();
		registryRepository.save(registry);
		return "home";
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
