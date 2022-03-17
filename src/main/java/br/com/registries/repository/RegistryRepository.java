package br.com.registries.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.registries.model.Registry;

public interface RegistryRepository extends JpaRepository<Registry, Long> {

	//@Query(value = "UPDATE registry SET deleted=true where id = :id", nativeQuery = true)
	//Registry disable(@Param("id") Long id);

	@Transactional
	@Modifying
	@Query(value = "UPDATE registry SET deleted=true where id = :id", nativeQuery = true)
	void alterar(@Param("id") Long id);

	@Query(value = "SELECT * FROM registry WHERE deleted = false", nativeQuery = true)
	List<Registry> findActives();

}
