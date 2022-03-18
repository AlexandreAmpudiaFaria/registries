package br.com.registries.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.registries.dto.CertificateDto;
import br.com.registries.form.NewCertificateForm;
import br.com.registries.model.Certificate;
import br.com.registries.repository.CertificateRepository;

@RestController
@RequestMapping("/certificate")
public class CertificateController {
	
	@Autowired
	CertificateRepository certificateRepository;

	
	@GetMapping("/all")
	public List<CertificateDto> getAllCertificates() {
		List<Certificate> certificates = certificateRepository.findAll();
		return CertificateDto.converter(certificates);
	}	
	
	@PostMapping("/new")
	public ResponseEntity<CertificateDto> NewCertificate(@RequestBody NewCertificateForm data, UriComponentsBuilder uriBuilder) {	
		Certificate certificate = data.convert();
		certificateRepository.save(certificate);
		URI uri = uriBuilder.path("/certificate/{id}").buildAndExpand(certificate.getId()).toUri();
		return ResponseEntity.created(uri).body(new CertificateDto(certificate));
	}
}
