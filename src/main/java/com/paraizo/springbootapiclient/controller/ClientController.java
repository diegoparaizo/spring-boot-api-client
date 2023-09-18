package com.paraizo.springbootapiclient.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paraizo.springbootapiclient.model.Client;
import com.paraizo.springbootapiclient.service.ClientService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping(path = "",produces = "application/json")
	public ResponseEntity<Client> createClient(@Valid @RequestBody Client client) throws Exception {
        try {
        	clientService.createClient(client);
            return ResponseEntity.ok(client);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
	}

    @GetMapping(path = "",produces = "application/json")
    public List<Client> searchAll() throws Exception {    	
    	return clientService.searchAll();
    }
	
	@GetMapping(path = "/{idClient}",produces = "application/json")
    public ResponseEntity<Client> searchById(@PathVariable Long idClient) throws Exception {
		try {
			Client client = clientService.searchById(idClient);
			return ResponseEntity.ok(client);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
    }

    @DeleteMapping(path = "/{idClient}",produces = "application/json")
    public ResponseEntity<Client> deleteById(@PathVariable("idClient") Long idClient) throws Exception {
        try {
        	clientService.deleteClient(idClient);
            return ResponseEntity.ok(null);
        }catch (Exception e) {
        	return ResponseEntity.badRequest().body(null);
        }
    }
    
    
    @PutMapping(path = "/{idClient}",produces = "application/json")
    public ResponseEntity<Client> updateClientById(@PathVariable Long idClient, @RequestBody Client client) {    	
        try {
        	clientService.updateClientById(idClient, client);
            return ResponseEntity.ok(client);
        }catch (Exception e) {
        	return ResponseEntity.badRequest().body(null);
        }
    }

}
