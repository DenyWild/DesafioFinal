package com.desafioFinal.DesafioFinal.dtos;

import com.desafioFinal.DesafioFinal.models.Marcacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcacaoProfessorRequest {

    private Long id_professor;
    private Long id_marcacao;

}
