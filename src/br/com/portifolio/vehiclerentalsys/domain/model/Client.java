package br.com.portifolio.vehiclerentalsys.domain.model;


import br.com.portifolio.vehiclerentalsys.domain.exception.DomainException;

import java.util.Objects;

public class Client implements Comparable<Client> {
    private final Integer id;
    private final String name;
    private final String document;
    private final String phone;

    public Client(int id, String name, String document, String phone) throws DomainException {
        if (id <= 0){
            throw new DomainException("ID invalido. Deve ser maior que zero.");
        }
        this.id = id;
        if (name == null||name.isBlank()){
            throw new DomainException("Nome nao nao pode ser vazio. Tente novamente: ");
        }
        this.name = name;
        if (document == null||document.length() != 11){
            throw new DomainException("CPF invalido, tente novamente: ");
        }
        this.document = document;
        if (phone == null||phone.length() != 11){
            throw new DomainException("Telefone invalido, tente novamente: ");
        }
        this.phone = phone;
    }

    public Integer getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + " | Nome: " + this.name + " | Documento: " + this.document + " | Telefone: " + this.phone + System.lineSeparator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public int compareTo(Client otherClient) {
        return this.id.compareTo(otherClient.id);
    }
}
