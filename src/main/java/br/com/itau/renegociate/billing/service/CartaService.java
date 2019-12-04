package br.com.itau.renegociate.billing.service;

import br.com.itau.renegociate.billing.client.CartaClient;
import br.com.itau.renegociate.billing.entities.Carta;
import br.com.itau.renegociate.billing.repositories.CartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CartaService {

    @Autowired
    private CartaRepository cartaRepository;

    @Autowired
    private CartaClient cartaClient;

    public List<Carta> findAll() {
        return cartaRepository.findAll();
    }

    public Optional<Carta> findById(String code) {
        return cartaRepository.findById(code);
    }

    /**
     * Save cartas in Mongo DB
     *
     * @param cartas list of cartas to be saved
     */
    public void saveCartas(Iterable<Carta> cartas) {
        cartaRepository.saveAll(cartas);
    }

    /**
     * Get existing cartas by ids from Mongo DB
     *
     * @param ids list of carta ids
     * @return Map of id and carta dto.
     */
    Map<String, Carta> getExistingCartasByIds(List<String> ids) {
        return cartaRepository.findAllByIdMap(ids);
    }

    public void sendCartas(List<Carta> cartas) {
        cartaClient.sendAll(cartas);
        cartas.stream().forEach(carta -> carta.setSended(true));
        saveCartas(cartas);
    }
}
