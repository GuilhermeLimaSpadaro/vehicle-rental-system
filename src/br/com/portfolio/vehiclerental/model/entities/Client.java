package br.com.portfolio.vehiclerental.model.entities;

import java.util.Objects;

public class Client implements Comparable<Client> {

	private Integer id;
	private String name;
	private String document;
	private String phone;

	public Client(int id, String name, String document, String phone) {
		this.id = id;
		this.name = name;
		this.document = document;
		this.phone = phone;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDocument() {
		return document;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public String toString() {
		return "Cliente | ID: " + id + "| Nome: " + name + "| Documento: " + document + "| Telefone: " + phone + "\n";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return id == other.id && Objects.equals(name, other.name);
	}

	@Override
	public int compareTo(Client other) {
		return this.id.compareTo(other.id);
	}

}
