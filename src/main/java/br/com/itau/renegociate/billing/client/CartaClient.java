package br.com.itau.renegociate.billing.client;

import br.com.itau.renegociate.billing.entities.Carta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "carta", url = "${itau.properties.carta-url}")
public interface CartaClient extends BaseClient<Carta> {

    @RequestMapping(method = RequestMethod.POST,
            headers = {HttpHeaders.AUTHORIZATION + "=${itau.properties.auth-header}"},
            value = "/carta/send", consumes = "application/json")
    ResponseEntity<String> sendAll(List<Carta> cartas);
}
