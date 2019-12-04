package br.com.itau.renegociate.billing.service;

import br.com.itau.renegociate.billing.client.CartaClient;
import br.com.itau.renegociate.billing.entities.Carta;
import br.com.itau.renegociate.billing.repositories.CartaRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class CartaServiceTest extends BaseParseServiceTest<Carta> {

    @SpyBean
    private CartaService cartaService;

    @MockBean
    private CartaClient cartaClient;

    @MockBean
    private CartaRepository cartaRepository;

    @Test
    public void doSendTestSuccess() throws IOException {

        cartaService.sendCartas(getResponseObjectsMock());

        verify(cartaClient).sendAll(argThat(carta
                -> carta.size() == 2));
    }


    @Override
    TypeReference<List<Carta>> getResponseType() {
        return new TypeReference<List<Carta>>() {
        };
    }
}
