package br.com.registries.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.registries.model.Certificate;

public class CertificateDto {

	private Long id;
	private String name;

	public CertificateDto() {

	}

	public CertificateDto(Certificate certificate) {
		this.id = certificate.getId();
		this.name = certificate.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static List<CertificateDto> converter(List<Certificate> certificates) {
		return certificates.stream().map(CertificateDto::new).collect(Collectors.toList());
	}

}
