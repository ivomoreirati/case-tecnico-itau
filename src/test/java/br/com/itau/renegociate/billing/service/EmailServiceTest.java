package br.com.itau.renegociate.billing.service;

import br.com.itau.renegociate.billing.client.EmailClient;
import br.com.itau.renegociate.billing.entities.Email;
import br.com.itau.renegociate.billing.repositories.CartaRepository;
import br.com.itau.renegociate.billing.repositories.EmailRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.verify;

public class EmailServiceTest extends BaseParseServiceTest<Email> {

    @SpyBean
    private EmailService emailService;

    @MockBean
    private EmailClient emailClient;

    @MockBean
    private EmailRepository emailRepository;

    @Test
    public void doSendTestSuccess() throws IOException {

        emailService.sendEmails(getResponseObjectsMock());

        verify(emailClient).sendAll(argThat(email
                -> email.size() == 2));
    }


    @Override
    TypeReference<List<Email>> getResponseType() {
        return new TypeReference<List<Email>>() {
        };
    }
}
