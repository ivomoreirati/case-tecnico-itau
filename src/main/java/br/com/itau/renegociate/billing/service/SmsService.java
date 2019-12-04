package br.com.itau.renegociate.billing.service;

import br.com.itau.renegociate.billing.client.SmsClient;
import br.com.itau.renegociate.billing.entities.Sms;
import br.com.itau.renegociate.billing.repositories.SmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SmsService {

    @Autowired
    private SmsRepository smsRepository;

    @Autowired
    private SmsClient smsClient;

    public List<Sms> findAll() {
        return smsRepository.findAll();
    }

    public Optional<Sms> findById(String code) {
        return smsRepository.findById(code);
    }

    /**
     * Save smss in Mongo DB
     *
     * @param smss list of emails to be saved
     */
    public void saveSmss(Iterable<Sms> smss) {
        smsRepository.saveAll(smss);
    }

    /**
     * Get existing emails by ids from Mongo DB
     *
     * @param ids list of email ids
     * @return Map of id and email dto.
     */
    Map<String, Sms> getExistingSmssByIds(List<String> ids) {
        return smsRepository.findAllByIdMap(ids);
    }

    public void sendSmss(List<Sms> smss) {
        smsClient.sendAll(smss);
        smss.stream().forEach(carta -> carta.setSended(true));
        saveSmss(smss);
    }
}
