package com.desafioFinal.DesafioFinal.controllers;

import com.desafioFinal.DesafioFinal.dtos.TagsProfessorRequest;
import com.desafioFinal.DesafioFinal.dtos.TagsRequest;
import com.desafioFinal.DesafioFinal.dtos.TagsResponse;
import com.desafioFinal.DesafioFinal.services.TagsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tags")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @PostMapping
    public ResponseEntity<TagsResponse> criarTag(@RequestBody @Valid TagsRequest request) {

        TagsResponse response = tagsService.criarTag(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PostMapping("/vincular")
    public ResponseEntity<TagsResponse> vincularTagAoProfessor(@RequestBody TagsProfessorRequest request) {

        TagsResponse response = tagsService.vincularTagAoProfessor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<TagsResponse> atualizarTag(@PathVariable Long id, @RequestBody TagsRequest request) {

        TagsResponse response = tagsService.atualizarTag(id, request);
        return ResponseEntity.ok().body(response);

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TagsResponse> buscarTagPorId(@PathVariable Long id) {

        TagsResponse response = tagsService.buscarTagPorId(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);

    }

    @GetMapping
    public ResponseEntity<Page<TagsResponse>> listarTodasTags(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage,
                org.springframework.data.domain.Sort.Direction.valueOf(direction), orderBy);

        Page<TagsResponse> list = tagsService.listarTodasTags(pageRequest);

        return ResponseEntity.ok().body(list);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirTagPorId(@PathVariable Long id) {

        tagsService.excluirTag(id);
        return ResponseEntity.noContent().build();

    }

}
