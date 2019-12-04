package br.com.itau.renegociate.billing.client;

import br.com.itau.renegociate.billing.entities.Email;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "email", url = "${itau.properties.email-url}")
public interface EmailClient extends BaseClient<Email> {

    @RequestMapping(method = RequestMethod.POST,
            headers = {HttpHeaders.AUTHORIZATION + "=${itau.properties.auth-header}"},
            value = "/email/send", consumes = "application/json")
    ResponseEntity<String> sendAll(List<Email> emails);
}
