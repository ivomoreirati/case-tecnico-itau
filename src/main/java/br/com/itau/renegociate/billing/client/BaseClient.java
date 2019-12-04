package br.com.itau.renegociate.billing.client;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BaseClient<T> {

    ResponseEntity<String> sendAll(List<T> list);
}
