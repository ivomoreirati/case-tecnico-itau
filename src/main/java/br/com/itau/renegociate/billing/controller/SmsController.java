package br.com.itau.renegociate.billing.controller;

import br.com.itau.renegociate.billing.entities.Sms;
import br.com.itau.renegociate.billing.service.SmsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

@RestController
public class SmsController {

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private SmsService smsService;

    @ApiOperation(value = "Retorna uma sms por id", notes = "Endpoint responsável por retornar uma sms por id ")
    @GetMapping(value = "/v1/sms/{id}")
    public Sms getEmailByCode(@PathVariable String id) {
        Optional<Sms> sms = smsService.findById(id);

        return sms.orElse(new Sms());
    }

    @ApiOperation(value = "Retorna os sms", notes = "Endpoint responsável por retornar os sms")
    @GetMapping(value = "/v1/smss")
    public List<Sms> getSmss() {
        return smsService.findAll();
    }

    @ApiOperation(value = "Salva os sms", notes = "Endpoint responsável por salvar os sms ")
    @PostMapping(value = "/v1/smss")
    public void saveSmss(List<Sms> smss) {
        smsService.saveSmss(smss);
    }

    @ApiOperation(value = "Envia os sms", notes = "Endpoint responsável por enviar os sms ")
    @PostMapping(value = "/v1/sendSms")
    public ResponseEntity<String> sendEmails(List<Sms> smss) {
        smsService.sendSmss(smss);
        return ResponseEntity.status(HttpStatus.OK).body("Sended smss");
    }
}
