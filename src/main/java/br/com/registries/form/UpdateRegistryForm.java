package br.com.registries.form;

public class UpdateRegistryForm {

	private String name;
	private String address;

	public UpdateRegistryForm() {

	}

	public UpdateRegistryForm(String name, String address) {
		this.name = name;
		this.address = address;
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

}
