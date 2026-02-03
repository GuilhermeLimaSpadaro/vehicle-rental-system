package br.com.portfolio.vehiclerental.repository;

import java.util.Set;
import java.util.TreeSet;

import br.com.portfolio.vehiclerental.model.entities.Client;
import br.com.portfolio.vehiclerental.model.exceptions.ClientException;
import br.com.portfolio.vehiclerental.model.interfaces.ClientInterface;

public class ClientRepository implements ClientInterface {

	private Set<Client> clientList = new TreeSet<Client>();

	public ClientRepository() {
	}

	public ClientRepository(Set<Client> clientList) {
		this.clientList = clientList;
	}

	public Set<Client> getClientList() {
		return clientList;
	}

	/**   **/

	@Override
	public void add(Client client) throws ClientException {
		if (clientList.contains(client)) {
			throw new ClientException("Cliente ja cadastrado.");
		}
		clientList.add(client);
	}

	@Override
	public void removeClient(String name) throws ClientException {
		Client find = findClientByName(name);
		clientList.remove(find);
	}

	@Override
	public Client findClientByName(String name) throws ClientException {
		if (clientList.isEmpty()) {
			throw new ClientException("Lista nao pode ser vazia!");
		}
		for (Client clientObj : clientList) {
			if (name.trim().equalsIgnoreCase(clientObj.getName())) {
				return clientObj;
			}
		}
		throw new ClientException("Cadastro nao encontrado: " + name);
	}

	@Override
	public String listClients() throws ClientException {
		if (clientList.isEmpty()) {
			throw new ClientException("Lista nao pode ser vazia!");
		}
		StringBuilder sb = new StringBuilder();
		for (Client client : clientList) {
			sb.append(client.toString());
		}
		return sb.toString();
	}
}
