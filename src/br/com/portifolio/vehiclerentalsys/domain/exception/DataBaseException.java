package br.com.portifolio.vehiclerentalsys.domain.exception;

public class DataBaseException extends RuntimeException {
    public DataBaseException(String message) {
        super(message);
    }
}
