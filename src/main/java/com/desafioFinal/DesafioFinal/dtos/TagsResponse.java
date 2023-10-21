package com.desafioFinal.DesafioFinal.dtos;

import com.desafioFinal.DesafioFinal.models.enums.Nivel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagsResponse {

    private Long id;
    private String descricao;
    private Nivel nivel;
}
