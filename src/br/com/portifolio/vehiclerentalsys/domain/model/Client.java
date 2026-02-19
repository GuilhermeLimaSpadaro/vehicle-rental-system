package br.com.portifolio.vehiclerentalsys.domain.model;


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

    public String toString() {
        return "ID: " + this.id + " | Nome: " + this.name + " | Documento: " + this.document + " | Telefone: " + this.phone + System.lineSeparator();
    }

    public int compareTo(Client otherClient) {
        return this.id.compareTo(otherClient.id);
    }
}
