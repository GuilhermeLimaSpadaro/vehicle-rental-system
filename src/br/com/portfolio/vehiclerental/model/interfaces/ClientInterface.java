package br.com.portfolio.vehiclerental.model.interfaces;

import br.com.portfolio.vehiclerental.model.entities.Client;
import br.com.portfolio.vehiclerental.model.exceptions.ClientException;

public interface ClientInterface {
	
	public void add(Client client) throws ClientException;
	
	public void removeClient(String name) throws ClientException;
	
	public Client findClientByName(String name) throws ClientException;
	
	public String listClients() throws ClientException;
}
