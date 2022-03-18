package br.com.registries.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.registries.model.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Long>{

}
