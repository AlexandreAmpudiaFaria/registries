package br.com.registries.form;

import br.com.registries.model.Certificate;

public class NewCertificateForm {

	private String name;

	public NewCertificateForm() {

	}

	public NewCertificateForm(Certificate certificate) {
		this.name = certificate.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Certificate convert() {
		//Certificate certificate = new Certificate();
		System.out.println(name);
		//certificate.setName(name);
		return new Certificate(name);
	}

}
