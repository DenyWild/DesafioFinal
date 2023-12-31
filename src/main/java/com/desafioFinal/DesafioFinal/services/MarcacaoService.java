package com.desafioFinal.DesafioFinal.services;

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

    public MarcacaoResponse vincularMarcacaoAoProfessor(Long id_marcacao, Long id_professor) {

        Professor prof = professorRepository.findById(id_professor).orElseThrow(() -> idNotFound(id_professor));
        Marcacao marca = marcacaoRepository.findById(id_marcacao).orElseThrow(() -> idNotFound(id_marcacao));

        marca.setProfessor(prof);

        return mapper.map(marcacaoRepository.save(marca), MarcacaoResponse.class);

    }

    public MarcacaoResponse atualizarMarcacao(Long id, MarcacaoRequest request) {

        Marcacao marc = marcacaoRepository.findById(id).orElseThrow(() -> idNotFound(id));
        BeanUtils.copyProperties(request, marc, "id");
        return mapper.map(marcacaoRepository.save(marc), MarcacaoResponse.class);

    }

    public MarcacaoResponse buscarMarcacaoPorId(Long id) {
        Marcacao marc = marcacaoRepository.findById(id).orElseThrow(() -> idNotFound(id));
        return mapper.map(marc, MarcacaoResponse.class);
    }

    public List<MarcacaoResponse> listarTodasMarcacoes() {
        List<Marcacao> lista = marcacaoRepository.findAll();

        List<MarcacaoResponse> list = lista.stream().map(Marcacao -> mapper.map(Marcacao, MarcacaoResponse.class))
                .collect(Collectors.toList());
        return list;
    }

    public void excluirMarcacao(Long id) {

        Marcacao marc = marcacaoRepository.findById(id).orElseThrow(() -> idNotFound(id));
        marcacaoRepository.delete(marc);

    }

    public RuntimeException idNotFound(Long id) {
        throw new ResourceNotFoundException("Id not found " + id);
    }

}
