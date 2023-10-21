package com.desafioFinal.DesafioFinal.services;

import com.desafioFinal.DesafioFinal.dtos.MarcacaoProfessorRequest;
import com.desafioFinal.DesafioFinal.dtos.MarcacaoRequest;
import com.desafioFinal.DesafioFinal.dtos.MarcacaoResponse;
import com.desafioFinal.DesafioFinal.exceptions.ResourceNotFoundException;
import com.desafioFinal.DesafioFinal.models.Marcacao;
import com.desafioFinal.DesafioFinal.models.Professor;
import com.desafioFinal.DesafioFinal.repositories.MarcacaoRepository;
import com.desafioFinal.DesafioFinal.repositories.ProfessorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarcacaoService {

    @Autowired
    private MarcacaoRepository marcacaoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ModelMapper mapper;

    public MarcacaoResponse criarMarcacao(MarcacaoRequest request) {

        Marcacao marc = new Marcacao();

        BeanUtils.copyProperties(request, marc);

        return mapper.map(marcacaoRepository.save(marc), MarcacaoResponse.class);


    }

    public MarcacaoResponse vincularMarcacaoAoProfessor(MarcacaoProfessorRequest request) {

        Long id_marcacao = request.getId_marcacao();
        Long id_professor = request.getId_professor();

        Marcacao marc = marcacaoRepository.findById(id_marcacao).orElseThrow(() -> idNotFound(id_marcacao));
        Professor prof = professorRepository.findById(id_professor).orElseThrow(() -> idNotFound(id_professor));
        marc.setProfessor(prof);
        return mapper.map(marcacaoRepository.save(marc), MarcacaoResponse.class);

    }

    public MarcacaoResponse atualizarMarcacao(Long id, MarcacaoRequest request) {

        Marcacao marc = marcacaoRepository.findById(id).orElseThrow(() -> idNotFound(id));
        BeanUtils.copyProperties(marc, request, "id");
        return mapper.map(marcacaoRepository.save(marc), MarcacaoResponse.class);

    }

    public MarcacaoResponse buscarMarcacaoPorId(Long id) {
        Marcacao marc = marcacaoRepository.findById(id).orElseThrow(() -> idNotFound(id));
        return mapper.map(marc, MarcacaoResponse.class);
    }

    public Page<MarcacaoResponse> listarTodasMarcacoes(PageRequest pageRequest) {
        Page<Marcacao> page = marcacaoRepository.findAll(pageRequest);

        List<MarcacaoResponse> list = page.getContent().stream().map(Marcacao -> mapper.map(Marcacao, MarcacaoResponse.class))
                .collect(Collectors.toList());
        return new PageImpl<>(list);
    }

    public void excluirMarcacao(Long id) {

        Marcacao marc = marcacaoRepository.findById(id).orElseThrow(() -> idNotFound(id));
        marcacaoRepository.delete(marc);

    }

    public RuntimeException idNotFound(Long id) {
        throw new ResourceNotFoundException("Id not found " + id);
    }

}
