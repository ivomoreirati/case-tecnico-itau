package br.com.itau.renegociate.billing.client;

import br.com.itau.renegociate.billing.entities.Sms;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "sms", url = "${itau.properties.sms-url}")
public interface SmsClient extends BaseClient<Sms> {

    @RequestMapping(method = RequestMethod.POST,
            headers = {HttpHeaders.AUTHORIZATION + "=${itau.properties.auth-header}"},
            value = "/sms/send", consumes = "application/json")
    ResponseEntity<String> sendAll(List<Sms> smss);
}
