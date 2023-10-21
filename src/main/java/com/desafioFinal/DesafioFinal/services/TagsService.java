package com.desafioFinal.DesafioFinal.services;

import com.desafioFinal.DesafioFinal.dtos.TagsProfessorRequest;
import com.desafioFinal.DesafioFinal.dtos.TagsRequest;
import com.desafioFinal.DesafioFinal.dtos.TagsResponse;
import com.desafioFinal.DesafioFinal.exceptions.ResourceNotFoundException;
import com.desafioFinal.DesafioFinal.models.Professor;
import com.desafioFinal.DesafioFinal.models.Tags;
import com.desafioFinal.DesafioFinal.repositories.ProfessorRepository;
import com.desafioFinal.DesafioFinal.repositories.TagsRepository;
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
public class TagsService {

    @Autowired
    private TagsRepository tagsRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ModelMapper mapper;

    public TagsResponse criarTag(TagsRequest request) {

        Tags tag = new Tags();

        BeanUtils.copyProperties(request, tag);

        return mapper.map(tagsRepository.save(tag), TagsResponse.class);


    }

    public TagsResponse vincularTagAoProfessor(TagsProfessorRequest request) {

        Long id_tag = request.getId_tag();
        Long id_professor = request.getId_professor();

        Tags tag = tagsRepository.findById(id_tag).orElseThrow(() -> idNotFound(id_tag));
        Professor prof = professorRepository.findById(id_professor).orElseThrow(() -> idNotFound(id_professor));
        tag.setProfessor(prof);
        return mapper.map(tagsRepository.save(tag), TagsResponse.class);

    }

    public TagsResponse atualizarTag(Long id, TagsRequest request) {

        Tags tag = tagsRepository.findById(id).orElseThrow(() -> idNotFound(id));
        BeanUtils.copyProperties(tag, request, "id");
        return mapper.map(tagsRepository.save(tag), TagsResponse.class);

    }

    public TagsResponse buscarTagPorId(Long id) {
        Tags tag = tagsRepository.findById(id).orElseThrow(() -> idNotFound(id));
        return mapper.map(tag, TagsResponse.class);
    }

    public Page<TagsResponse> listarTodasTags(PageRequest pageRequest) {
        Page<Tags> page = tagsRepository.findAll(pageRequest);

        List<TagsResponse> list = page.getContent().stream().map(Tags -> mapper.map(Tags, TagsResponse.class))
                .collect(Collectors.toList());
        return new PageImpl<>(list);
    }

    public void excluirTag(Long id) {

        Tags tag = tagsRepository.findById(id).orElseThrow(() -> idNotFound(id));
        tagsRepository.delete(tag);

    }

    public RuntimeException idNotFound(Long id) {
        throw new ResourceNotFoundException("Id not found " + id);
    }
}
