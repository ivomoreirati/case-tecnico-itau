package br.com.itau.renegociate.billing.service;

import br.com.itau.renegociate.billing.client.SmsClient;
import br.com.itau.renegociate.billing.entities.Sms;
import br.com.itau.renegociate.billing.repositories.CartaRepository;
import br.com.itau.renegociate.billing.repositories.SmsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.verify;

public class SmsServiceTest extends BaseParseServiceTest<Sms> {

    @SpyBean
    private SmsService smsService;

    @MockBean
    private SmsClient smsClient;

    @MockBean
    private SmsRepository smsRepository;

    @Test
    public void doSendTestSuccess() throws IOException {

        smsService.sendSmss(getResponseObjectsMock());

        verify(smsClient).sendAll(argThat(sms
                -> sms.size() == 2));
    }


    @Override
    TypeReference<List<Sms>> getResponseType() {
        return new TypeReference<List<Sms>>() {
        };
    }
}
