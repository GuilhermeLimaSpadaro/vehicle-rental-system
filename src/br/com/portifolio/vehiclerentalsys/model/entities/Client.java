package br.com.portifolio.vehiclerentalsys.model.entities;

import java.util.Objects;

public class Client implements Comparable<Client> {
    private final Integer id;
    private final String name;
    private final String document;
    private final String phone;

    public Client(int id, String name, String document, String phone) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.phone = phone;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDocument() {
        return this.document;
    }

    public String getPhone() {
        return this.phone;
    }

    public String toString() {
        return "Cliente | ID: " + this.id + "| Nome: " + this.name + "| Documento: " + this.document + "| Telefone: " + this.phone + System.lineSeparator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(name, client.name) && Objects.equals(document, client.document);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, document);
    }

    public int compareTo(Client other) {
        return this.id.compareTo(other.id);
    }
}
