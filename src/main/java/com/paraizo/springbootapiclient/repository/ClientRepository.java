package com.paraizo.springbootapiclient.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.paraizo.springbootapiclient.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	@Query(value = "SELECT * FROM CLIENTS", nativeQuery = true)
	List<Client> findAllNative();
	
	@Query(value = "SELECT * FROM CLIENTS WHERE ID = :id", nativeQuery = true)
	Optional<Client> findByIdNative(@Param ("id") Long id);
	
	public Client findTop1ByEmail(String email);

}
