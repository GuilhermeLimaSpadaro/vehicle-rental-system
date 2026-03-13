package br.com.portifolio.vehiclerentalsys.utils;

import br.com.portifolio.vehiclerentalsys.domain.exception.DomainException;

import java.util.Set;

public class ListUtils {

    public static <T> void list(Set<T> list) throws DomainException {
        if (list.isEmpty()){
            System.out.println("Nenhum registro encontrado.");
        }
        for (T t : list) {
            System.out.println(t);
        }
    }
}
