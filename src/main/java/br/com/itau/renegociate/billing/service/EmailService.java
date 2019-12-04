package br.com.itau.renegociate.billing.service;

import br.com.itau.renegociate.billing.client.EmailClient;
import br.com.itau.renegociate.billing.entities.Email;
import br.com.itau.renegociate.billing.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private EmailClient emailClient;

    public List<Email> findAll() {
        return emailRepository.findAll();
    }

    public Optional<Email> findById(String code) {
        return emailRepository.findById(code);
    }

    /**
     * Save emails in Mongo DB
     *
     * @param emails list of emails to be saved
     */
    public void saveEmails(Iterable<Email> emails) {
        emailRepository.saveAll(emails);
    }

    /**
     * Get existing emails by ids from Mongo DB
     *
     * @param ids list of email ids
     * @return Map of id and email dto.
     */
    Map<String, Email> getExistingEmailsByIds(List<String> ids) {
        return emailRepository.findAllByIdMap(ids);
    }

    public void sendEmails(List<Email> emails) {
        emailClient.sendAll(emails);
        emails.stream().forEach(carta -> carta.setSended(true));
        saveEmails(emails);
    }
}
