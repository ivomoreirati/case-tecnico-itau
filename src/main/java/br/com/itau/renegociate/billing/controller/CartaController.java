package br.com.itau.renegociate.billing.controller;

import br.com.itau.renegociate.billing.entities.Carta;
import br.com.itau.renegociate.billing.service.CartaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

@RestController
public class CartaController {

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private CartaService cartaService;

    @ApiOperation(value = "Retorna uma carta por id", notes = "Endpoint responsável por retornar uma carta por id ")
    @GetMapping(value = "/v1/carta/{id}")
    public Carta getCartaById(@PathVariable String id) {
        Optional<Carta> carta = cartaService.findById(id);

        return carta.orElse(new Carta());
    }

    @ApiOperation(value = "Retorna as cartas", notes = "Endpoint responsável por retornar as cartas ")
    @GetMapping(value = "/v1/cartas")
    public List<Carta> getCartas() {
        return cartaService.findAll();
    }

    @ApiOperation(value = "Salva as cartas", notes = "Endpoint responsável por salvar as cartas ")
    @PostMapping(value = "/v1/cartas")
    public void saveCartas(List<Carta> cartas) {
        cartaService.saveCartas(cartas);
    }

    @ApiOperation(value = "Envia as cartas", notes = "Endpoint responsável por enviar as cartas ")
    @PostMapping(value = "/v1/sendCarta")
    public ResponseEntity<String> sendCartas(List<Carta> cartas) {
        cartaService.sendCartas(cartas);
        return ResponseEntity.status(HttpStatus.OK).body("Sended Cartas");
    }
}
