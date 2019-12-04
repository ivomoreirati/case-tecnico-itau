package br.com.itau.renegociate.billing.controller;

import br.com.itau.renegociate.billing.entities.Email;
import br.com.itau.renegociate.billing.service.EmailService;
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
public class EmailController {

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private EmailService emailService;

    @ApiOperation(value = "Retorna uma email por id", notes = "Endpoint responsável por retornar uma email por id ")
    @GetMapping(value = "/v1/email/{id}")
    public Email getEmailByCode(@PathVariable String id) {
        Optional<Email> email = emailService.findById(id);

        return email.orElse(new Email());
    }

    @ApiOperation(value = "Retorna os emails", notes = "Endpoint responsável por retornar os emails")
    @GetMapping(value = "/v1/emails")
    public List<Email> getEmails() {
        return emailService.findAll();
    }

    @ApiOperation(value = "Salva os emails", notes = "Endpoint responsável por salvar os emails ")
    @PostMapping(value = "/v1/emails")
    public void saveEmails(List<Email> emails) {
        emailService.saveEmails(emails);
    }

    @ApiOperation(value = "Envia os emails", notes = "Endpoint responsável por enviar os emails ")
    @PostMapping(value = "/v1/sendEmail")
    public ResponseEntity<String> sendEmails(List<Email> emails) {
        emailService.sendEmails(emails);
        return ResponseEntity.status(HttpStatus.OK).body("Sended Emails");
    }
}
