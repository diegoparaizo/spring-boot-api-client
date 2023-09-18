package com.paraizo.springbootapiclient.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.paraizo.springbootapiclient.exception.ResourceNotFoundException;
import com.paraizo.springbootapiclient.model.Client;
import com.paraizo.springbootapiclient.repository.ClientRepository;
import com.paraizo.springbootapiclient.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;

	public ClientServiceImpl(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}

	@Override
	public Client createClient(Client client) throws Exception {

		if (Objects.nonNull(clientRepository.findTop1ByEmail(client.getEmail()))) {
			throw new RuntimeException("O e-mail inserido ja pertence a outro usuário.");
		}

		return clientRepository.save(client);
	}

	@Override
	public List<Client> searchAll() throws Exception {
		return clientRepository.findAllNative();
		// return clientRepository.findAll();
	}

	@Override
	public Client searchById(Long idClient) throws Exception {
		Optional.ofNullable(idClient)// ArgumentNotValidException
				.orElseThrow(() -> new RuntimeException("Id não pode ser nulo"));

		return this.clientRepository.findByIdNative(idClient)
				.orElseThrow(() -> new RuntimeException("Cliente de id " + idClient + " não encontrado"));
	}

	@Override
	public void deleteClient(Long idClient) throws Exception {
		this.clientRepository.findByIdNative(idClient)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente de id " + idClient + " não encontrado"));

		this.clientRepository.deleteById(idClient);
	}

	@Override
	@Transactional
	public Client updateClientById(Long idClient, Client client) throws Exception {
		this.searchById(idClient);
		client.setId(idClient);
		return this.clientRepository.save(client);
	}

}
