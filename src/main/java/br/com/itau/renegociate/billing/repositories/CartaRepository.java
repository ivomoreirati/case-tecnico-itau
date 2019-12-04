package br.com.itau.renegociate.billing.repositories;

import br.com.itau.renegociate.billing.entities.Carta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public interface CartaRepository extends MongoRepository<Carta, String> {

    default Map<String, Carta> findAllByIdMap(Iterable<String> ids) {
        return StreamSupport.stream(findAllById(ids).spliterator(), true).collect(Collectors.toMap(
                Carta::getId, dto -> dto));
    }

    Optional<Carta> findById(String id);
}
