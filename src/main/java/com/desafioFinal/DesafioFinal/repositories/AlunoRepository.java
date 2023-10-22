package com.desafioFinal.DesafioFinal.repositories;

import com.desafioFinal.DesafioFinal.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
