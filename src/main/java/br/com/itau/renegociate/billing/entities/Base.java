package br.com.itau.renegociate.billing.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Base {

    @NotNull
    @NotBlank
    private BigDecimal idCliente;

    @NotNull
    @NotBlank
    private String id;

    @NotNull
    @NotBlank
    private String subject;

    @NotNull
    @NotBlank
    private String body;

    private Boolean sended = false;

}
