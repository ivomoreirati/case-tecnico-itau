package br.com.itau.renegociate.billing.repositories;

import br.com.itau.renegociate.billing.entities.Email;
import br.com.itau.renegociate.billing.entities.Sms;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public interface SmsRepository extends MongoRepository<Sms, String> {

    default Map<String, Sms> findAllByIdMap(Iterable<String> ids) {
        return StreamSupport.stream(findAllById(ids).spliterator(), true).collect(Collectors.toMap(
                Sms::getId, dto -> dto));
    }

    Optional<Sms> findById(String id);
}
