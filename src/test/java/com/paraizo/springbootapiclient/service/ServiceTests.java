package com.paraizo.springbootapiclient.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paraizo.springbootapiclient.model.Client;
import com.paraizo.springbootapiclient.repository.ClientRepository;
import com.paraizo.springbootapiclient.service.impl.ClientServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ServiceTests {

	@Spy
	private ClientService clienteService;

	@Mock
	private ClientRepository clientRepository;

	static Client client;

	@BeforeEach
	public void before() {

		long clientId = 1L;
		client = new Client(clientId, "Mary", "mary@icloud.com", LocalDate.of(1990, 5, 4));

		clienteService = spy(new ClientServiceImpl(clientRepository));
	}

	@Test
	void testCreateClient() throws Exception {
		
		when(clientRepository.save(client)).thenReturn(client);

		Client cli = clienteService.createClient(client);
		
		Assertions.assertNotNull(cli);		
		Assertions.assertEquals(client, cli);

	}

	@Test
	void testUpdateClient() throws Exception {

		long clientId = 1L;
		Client client2 = new Client(clientId, "Mary Jane", "maryjane@icloud.com", LocalDate.of(1990, 5, 13));

		when(clientRepository.findByIdNative(Mockito.anyLong())).thenReturn(Optional.ofNullable(client));

		when(clientRepository.save(client2)).thenReturn(client2);

		Client c = clienteService.updateClientById(client.getId(), client2);

		Assertions.assertNotNull(c);
		Assertions.assertNotEquals(client.getNome(), c.getNome());

	}

	@Test
	void testFindAllClients() throws Exception {
		List<Client> listClients = new ArrayList<Client>();
		listClients.add(new Client(1L, "John", "john@outlook.com", LocalDate.of(1992, 7, 12)));
		listClients.add(new Client(2L, "Alex", "alex@gmail.com", LocalDate.of(1991, 6, 8)));
		listClients.add(new Client(3L, "Mary", "mary@icloud.com", LocalDate.of(1990, 5, 4)));

		when(clientRepository.findAllNative()).thenReturn(listClients);

		List<Client> clientList = clienteService.searchAll();

		verify(clienteService, times(1)).searchAll();

		assertEquals(3, clientList.size());

	}

	@Test
	void testFindById() throws Exception {
		
		when(clientRepository.findByIdNative(Mockito.anyLong())).thenReturn(Optional.ofNullable(client));

		Client c = clienteService.searchById(client.getId());
		
		verify(clienteService, times(1)).searchById(client.getId());

		Assertions.assertNotNull(c);
		
	}

	@Test
	void testDelete() throws Exception {
		
		when(clientRepository.findByIdNative(Mockito.anyLong())).thenReturn(Optional.ofNullable(client));
				
		clienteService.deleteClient(client.getId());

		verify(clienteService, times(1)).deleteClient(client.getId());
	}

}
