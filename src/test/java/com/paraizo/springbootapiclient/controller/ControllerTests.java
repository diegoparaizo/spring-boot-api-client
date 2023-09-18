package com.paraizo.springbootapiclient.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.paraizo.springbootapiclient.model.Client;
import com.paraizo.springbootapiclient.service.impl.ClientServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ControllerTests {

	@Spy
	@InjectMocks
	static ClientController clientController;

	@Mock
	ClientServiceImpl clientServiceImpl;
	
	static Client client;

	@BeforeAll
	public static void before() {

		long clientId = 1L;
		client = new Client(clientId, "Mary", "mary@icloud.com", LocalDate.of(1990, 5, 4));
	}

	@Test
	public void testCreateClient() throws Exception {
		
		when(clientServiceImpl.createClient(client)).thenReturn(client);

		Client c = clientController.createClient(client);

		Assertions.assertNotNull(c);
		Assertions.assertEquals(client, c);

	}

	@Test
	public void testUpdateClient() throws Exception {

		long clientId = 1L;
		Client client2 = new Client(clientId, "Mary Jane", "maryjane@icloud.com", LocalDate.of(1990, 5, 13));

		ResponseEntity<Client> c = clientController.updateClientById(client.getId(), client2);

		Assertions.assertNotNull(c);

	}

	@Test
	public void testFindAllClients() throws Exception {
		List<Client> listClients = new ArrayList<Client>();
		listClients.add(new Client(1L, "John", "john@outlook.com", LocalDate.of(1992, 7, 12)));
		listClients.add(new Client(2L, "Alex", "alex@gmail.com", LocalDate.of(1991, 6, 8)));
		listClients.add(new Client(3L, "Mary", "mary@icloud.com", LocalDate.of(1990, 5, 4)));
		
		when(clientServiceImpl.searchAll()).thenReturn(listClients);

		List<Client> clientList = clientController.searchAll();

		assertEquals(3, clientList.size());

	}

	@Test
	public void testFindById() throws Exception {

		ResponseEntity<Client> c = clientController.searchById(client.getId());

		Assertions.assertNotNull(c);

	}

	@Test
	public void testDelete() throws Exception {
		
		doNothing().when(clientServiceImpl).deleteClient(client.getId());

		clientController.deleteById(client.getId());

		verify(clientController, times(1)).deleteById(client.getId());
	}

}
