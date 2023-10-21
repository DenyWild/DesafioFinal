package com.desafioFinal.DesafioFinal.controllers;

import com.desafioFinal.DesafioFinal.dtos.MarcacaoRequest;
import com.desafioFinal.DesafioFinal.dtos.MarcacaoResponse;
import com.desafioFinal.DesafioFinal.services.MarcacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FOUND;

@RestController
@RequestMapping("/marcacao")
public class MarcacaoController {

    @Autowired
    private MarcacaoService marcacaoService;

    @PostMapping
    public ResponseEntity<MarcacaoResponse> criarMarcacao(@RequestBody @Valid MarcacaoRequest request) {

        MarcacaoResponse response = marcacaoService.criarMarcacao(request);
        return ResponseEntity.status(CREATED).body(response);

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<MarcacaoResponse> atualizarMarcacao(@PathVariable Long id, @RequestBody MarcacaoRequest request) {

        MarcacaoResponse response = marcacaoService.atualizarMarcacao(id, request);
        return ResponseEntity.ok().body(response);

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MarcacaoResponse> buscarMarcacaoPorId(@PathVariable Long id) {

        MarcacaoResponse response = marcacaoService.buscarMarcacaoPorId(id);
        return ResponseEntity.status(FOUND).body(response);

    }

    @GetMapping
    public ResponseEntity<Page<MarcacaoResponse>> listarTodasMarcacoes(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {


        PageRequest pageRequest = PageRequest.of(page, linesPerPage,
                org.springframework.data.domain.Sort.Direction.valueOf(direction), orderBy);

        Page<MarcacaoResponse> list = marcacaoService.listarTodasMarcacoes(pageRequest);

        return ResponseEntity.ok().body(list);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> excluirMarcacaoPorId(@PathVariable Long id) {

        marcacaoService.excluirMarcacao(id);
        return ResponseEntity.noContent().build();

    }

}