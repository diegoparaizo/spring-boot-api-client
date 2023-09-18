package com.paraizo.springbootapiclient.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paraizo.springbootapiclient.model.Client;

@Service
public interface ClientService {
	
	public Client createClient(Client client) throws Exception;
	
	public List<Client> searchAll() throws Exception;
	
	public Client searchById(Long idClient) throws Exception;
	
    public void deleteClient(Long idClient) throws Exception;

    public Client updateClientById(Long idClient, Client client) throws Exception;    

}
