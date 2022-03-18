package br.com.registries.form;

import java.util.List;

import br.com.registries.model.Certificate;
import br.com.registries.model.Registry;

public class NewRegistryForm {

	private String name;
	private String address;
	private List<Certificate> certificates;

	public NewRegistryForm() {

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

	public List<Certificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<Certificate> certificates) {
		this.certificates = certificates;
	}

	public Registry convert() {
		Registry registry = new Registry();
		registry.setName(name);
		registry.setAddress(address);
		registry.setCertificates(certificates);
		return registry;
	}

}
