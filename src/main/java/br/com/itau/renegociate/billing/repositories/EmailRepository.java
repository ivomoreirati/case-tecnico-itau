package br.com.itau.renegociate.billing.repositories;

import br.com.itau.renegociate.billing.entities.Email;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public interface EmailRepository extends MongoRepository<Email, String> {

    default Map<String, Email> findAllByIdMap(Iterable<String> ids) {
        return StreamSupport.stream(findAllById(ids).spliterator(), true).collect(Collectors.toMap(
                Email::getId, dto -> dto));
    }

    Optional<Email> findById(String id);
}
