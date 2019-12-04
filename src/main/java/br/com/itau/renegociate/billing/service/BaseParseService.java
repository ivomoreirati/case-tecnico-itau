package br.com.itau.renegociate.billing.service;

import br.com.itau.renegociate.billing.client.BaseClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public abstract class BaseParseService<T> {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected Validator validator;

    public ParameterizedTypeReference<List<T>> getResponseType() {
        return null; // not needed for Feign clients
    }

    public String getUrl() {
        return null; // not needed for Feign clients
    }

    abstract BaseClient<T> getClient();

    boolean isValid(Object obj) {
        Set<ConstraintViolation<Object>> violations = validator.validate(obj);

        violations.forEach(violation -> log.warn("dto entry is invalid: "
                + String.join(" ", violation.getPropertyPath().toString(), violation.getMessage())));

        return violations.isEmpty();
    }
}
