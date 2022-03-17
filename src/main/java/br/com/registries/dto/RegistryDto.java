package br.com.registries.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.registries.model.Registry;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RegistryDto {

	private Long id;
	private String name;
	private String address;
	private List<CertificateDto> certificates;

	public RegistryDto() {

	}

	public RegistryDto(Registry registry) {
		this.id = registry.getId();
		this.name = registry.getName();
		this.address = registry.getAddress();
		this.certificates = new ArrayList<>();
		this.certificates
				.addAll(registry.getCertificates().stream().map(CertificateDto::new).collect(Collectors.toList()));

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<CertificateDto> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<CertificateDto> certificates) {
		this.certificates = certificates;
	}

	public static List<RegistryDto> converter(List<Registry> registries) {
		return registries.stream().map(RegistryDto::new).collect(Collectors.toList());
	}

	public static RegistryDto individualConverter(Registry registries) {
		
		Registry registry = new Registry();
	    registry.setId(registry.getId());
	    registry.setName(registry.getName());
	    
		return null;
	}

}
